package to.grindelf.apartmentmanager.utils.database;

import org.jetbrains.annotations.NotNull;
import to.grindelf.apartmentmanager.annonations.SQLPurposed;
import to.grindelf.apartmentmanager.exceptions.IrregularAccessException;
import to.grindelf.apartmentmanager.exceptions.JSONException;
import to.grindelf.apartmentmanager.utils.DataOperator;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that handles SQL operations.
 * @param <T> type of the object to operate on.
 * @param <K> type of the object to use as a key to get other objects.
 */
public class SQLOperator<T, K> implements DataOperator<T, K> {

    public SQLOperator(Class<T> dataType) {  }

    // ===================================================================== \\
    //                 HIGH-LEVEL SQL OPERATIONS WITH DATA                   \\
    // These are the high-level operations that are used by the application. \\
    // ===================================================================== \\

    @Override
    @SQLPurposed
    public T getByKey(
            @NotNull K key,
            @NotNull String filePath,
            @NotNull DatabaseTableNames tableName
    ) throws SQLException, IrregularAccessException {
        return null;
    }

    /**
     * Returns all data from the table in the database file.
     *
     * @param filePath  path to the database file
     * @param mapper    mapper to convert ResultSet to object
     * @param tableName name of the table to get data from
     * @return list of objects of type T
     */
    @Override
    @SQLPurposed
    public @NotNull List<T> getAll(
            @NotNull String filePath,
            @NotNull RowMapper<T> mapper,
            @NotNull String tableName
    ) throws SQLException, IrregularAccessException {

        List<T> result = new ArrayList<>();
        String url = "jdbc:sqlite:" + filePath;

        try (Connection conn = DriverManager.getConnection(url)) {
            String query = "SELECT * FROM " + tableName;

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    result.add(mapper.mapRow(rs));
                }
            }
        }

        return result;
    }

    /**
     * Validates and, if valid, inserts data into the database file.
     *
     * @param data      object to insert
     * @param filePath  path to the database file
     * @param tableName name of the table to insert data into
     */
    @Override
    @SQLPurposed
    public void post(
            @NotNull T data,
            @NotNull String filePath,
            @NotNull DatabaseTableNames tableName
    ) throws SQLException, IrregularAccessException {
        String url = "jdbc:sqlite:" + filePath;

        try (Connection conn = DriverManager.getConnection(url)) {
            validateDataAgainstTableStructure(data, conn, tableName.toString());
            insertQuery(data, conn, tableName.toString());
        }
    }

    /**
     * Updates the data in the database file.
     * @param key           key to update the object by
     * @param filePath      path to the database file
     * @param tableName     name of the table to update data in
     * @throws SQLException if an error occurs during the operation
     */
    @Override
    @SQLPurposed
    public void update(
            @NotNull K key,
            @NotNull String filePath,
            @NotNull DatabaseTableNames tableName
    ) throws SQLException, IrregularAccessException { }

    /**
     * Deletes the data from the database file.
     * @param key       key to delete the object by
     * @param filePath  path to the database file
     * @param tableName name of the table to delete data from
     * @throws SQLException if an error occurs during the operation
     */
    @Override
    @SQLPurposed
    public void delete(
            @NotNull K key,
            @NotNull String filePath,
            @NotNull DatabaseTableNames tableName
    ) throws SQLException, IrregularAccessException { }

    // =================================================================== \\
    //                 LOW-LEVEL SQL OPERATIONS WITH DATA                  \\
    // These are the helper-functions for the high-level operations above. \\
    // =================================================================== \\
    /**
     * Validates the data against the table structure.
     *
     * @param data      object to validate
     * @param conn      database connection
     * @param tableName name of the table to validate against
     * @throws SQLException if an error occurs during the validation process
     */
    private static <T> void validateDataAgainstTableStructure(
            @NotNull T data,
            @NotNull Connection conn,
            @NotNull String tableName
    ) throws SQLException {
        // Retrieve database table structure
        DatabaseMetaData metaData = conn.getMetaData();
        Map<String, String> tableColumns = new HashMap<>();

        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String columnType = columns.getString("TYPE_NAME");
                tableColumns.put(columnName, columnType);
            }
        }

        // Retrieve object's field structure
        Field[] fields = data.getClass().getDeclaredFields();

        for (Field field : fields) {
            String fieldName = field.getName();
            field.setAccessible(true);

            if (!tableColumns.containsKey(fieldName)) {
                throw new IllegalArgumentException("Field '" + fieldName + "' is not present in table '" + tableName + "'.");
            }
        }
    }


    /**
     * Inserts data into the database table
     *
     * @param data      object to insert
     * @param conn      database connection
     * @param tableName name of the table to insert data into
     * @throws SQLException if an error occurs during the insert operation
     */
    private static <T> void insertQuery(
            @NotNull T data,
            @NotNull Connection conn,
            @NotNull String tableName
    ) throws SQLException {
        Field[] fields = data.getClass().getDeclaredFields();
        StringBuilder columns = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();

        for (Field field : fields) {
            columns.append(field.getName()).append(",");
            placeholders.append("?,");
        }

        columns.setLength(columns.length() - 1);
        placeholders.setLength(placeholders.length() - 1);

        String query = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + placeholders + ")";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            int index = 1;

            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(data);

                    // Handle Enums (e.g., UserStatus) by mapping to their toString() value
                    if (value instanceof Enum) {
                        value = value.toString();
                    }

                    stmt.setObject(index++, value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to access field value: " + field.getName(), e);
                }
            }

            stmt.executeUpdate();
        }
    }

    // =================================================================================== \\
    // THE CODE BELLOW IS PURPOSED FOR JSON OPERATOR AND HERE ONLY TO AN INHERITANCE NEEDS \\
    // =================================================================================== \\
    /**
     * Returns the content of a file.
     *
     * @param filePath path to destination file
     * @return returns the content of a file.
     * @throws JSONException if an error occurs during the operation
     */
    @Override
    public T readFile(@NotNull String filePath) throws JSONException, IrregularAccessException {
        throw new IrregularAccessException(this.getClass().getName());
    }

    /**
     * Overwrites content of the provided file with new content
     *
     * @param filePath path to destination file
     * @param data     what to write in destination file
     * @throws JSONException if an error occurs during the operation
     */
    @Override
    public void writeToFile(@NotNull String filePath, @NotNull T data) throws JSONException, IrregularAccessException {
        throw new IrregularAccessException(this.getClass().getName());
    }
}
