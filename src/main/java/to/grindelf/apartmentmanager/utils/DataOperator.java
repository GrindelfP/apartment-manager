package to.grindelf.apartmentmanager.utils;

import org.jetbrains.annotations.NotNull;
import to.grindelf.apartmentmanager.annonations.JSONPurposed;
import to.grindelf.apartmentmanager.annonations.SQLPurposed;
import to.grindelf.apartmentmanager.exceptions.IrregularAccessException;
import to.grindelf.apartmentmanager.exceptions.JSONException;
import to.grindelf.apartmentmanager.utils.database.DatabaseTableNames;
import to.grindelf.apartmentmanager.utils.database.RowMapper;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface that defines operations on data with different data sources.
 * <p>Is divided into two parts: JSON and SQL operations.</p>
 *
 * @param <T> type of the object to operate on.
 * @param <K> type of the object to use as a key to get other objects.
 */
public interface DataOperator<T, K> {

    // ============================= \\
    //        JSON OPERATIONS        \\
    // ============================= \\
    /**
     * Returns the content of a file.
     *
     * @param filePath path to destination file
     * @return returns the content of a file.
     * @throws JSONException if an error occurs during the operation
     */
    @JSONPurposed
    T readFile(@NotNull String filePath) throws JSONException, IrregularAccessException;

    /**
     * Overwrites content of the provided file with new content
     *
     * @param filePath path to destination file
     * @param data     what to write in destination file
     * @throws JSONException if an error occurs during the operation
     */
    @JSONPurposed
    void writeToFile(
            @NotNull String filePath,
            @NotNull T data
    ) throws JSONException, IrregularAccessException;

    // ============================= \\
    //        SQL OPERATIONS         \\
    // ============================= \\
    /**
     * Returns the object by the provided key from the database file.
     * @param key key to get the object by
     * @param filePath path to the database file
     * @param tableName name of the table to get data from
     * @return returns the object by the provided key from the database file
     * @throws SQLException if an error occurs during the operation
     */
    @SQLPurposed
    T getByKey(
            @NotNull K key,
            @NotNull String filePath,
            @NotNull DatabaseTableNames tableName
    ) throws SQLException, IrregularAccessException;

    /**
     * Returns all data from the table in the database file.
     *
     * @param filePath  path to the database file
     * @param mapper    mapper to convert ResultSet to object
     * @param tableName name of the table to get data from
     * @return list of objects of type T
     */
    @SQLPurposed
    @NotNull
    List<T> getAll(
            @NotNull String filePath,
            @NotNull RowMapper<T> mapper,
            @NotNull String tableName
    ) throws SQLException, IrregularAccessException;

    /**
     * Validates and, if valid, inserts data into the database file.
     *
     * @param data      object to insert
     * @param filePath  path to the database file
     * @param tableName name of the table to insert data into
     */
    @SQLPurposed
    void post(
            @NotNull T data,
            @NotNull String filePath,
            @NotNull DatabaseTableNames tableName
    ) throws SQLException, IrregularAccessException;

    /**
     * Updates the data in the database file.
     *
     * @param key       key to update the object by
     * @param filePath  path to the database file
     * @param tableName name of the table to update data in
     * @throws SQLException if an error occurs during the operation,
     * @throws IrregularAccessException if the function is called from an implementation that is not supposed to call it
     */
    @SQLPurposed
    void update(
            @NotNull K key,
            @NotNull String keyColumnName,
            @NotNull T data,
            @NotNull String filePath,
            @NotNull DatabaseTableNames tableName
    ) throws SQLException, IrregularAccessException;

    /**
     * Deletes the data from the database file.
     *
     * @param key           key to delete the object by
     * @param keyColumnName
     * @param filePath      path to the database file
     * @param tableName     name of the table to delete data from
     * @throws SQLException if an error occurs during the operation
     */
    @SQLPurposed
    void delete(
            @NotNull K key,
            @NotNull String keyColumnName, @NotNull String filePath,
            @NotNull DatabaseTableNames tableName
    ) throws SQLException, IrregularAccessException;
}
