package to.grindelf.apartmentmanager.utils;

import org.jetbrains.annotations.NotNull;
import to.grindelf.apartmentmanager.annonations.JSONPurposed;
import to.grindelf.apartmentmanager.annonations.SQLPurposed;
import to.grindelf.apartmentmanager.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLOperator<T> implements DataOperator<T> {


    private final Class<T> dataType;

    public SQLOperator(Class<T> dataType) {
        this.dataType = dataType;
    }

    /**
     * Returns the content of a database file.
     *
     * @param filePath path to destination file
     * @return returns the content of a database file.
     */
    @Override
    @JSONPurposed
    public T readFile(@NotNull String filePath) {
        return null;
    }

    /**
     * @param t
     * @param filePath
     * @return
     */
    @Override
    @SQLPurposed
    public T get(@NotNull T t, @NotNull String filePath) {

        return null;
    }

    /**
     * @param filePath
     * @return
     */
    @Override
    @SQLPurposed
    public @NotNull List<T> getAll(@NotNull String filePath) {

        List<T> result = new ArrayList<>();

        if (this.dataType == User.class) {

            List<User> users = processUsersListQuery(filePath);

            result = ((List<T>) users); // wtf?
        }

        return result;
    }

    private @NotNull List<User> processUsersListQuery(@NotNull String filePath) {

        List<User> users = new ArrayList<>();

        String url = "jdbc:sqlite:" + filePath;
        String getAllQuery = "SELECT name, password, status FROM users";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement(getAllQuery);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                users.add(new User(
                                resultSet.getString("name"),
                                resultSet.getString("password"),
                                resultSet.getString("status")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    /**
     * @param t
     * @param filePath
     * @return
     */
    @Override
    @SQLPurposed
    public T post(@NotNull T t, @NotNull String filePath) {
        return null;
    }

    /**
     * @param t
     * @param filePath
     * @return
     */
    @Override
    @SQLPurposed
    public T update(@NotNull T t, @NotNull String filePath) {
        return null;
    }

    /**
     * @param t
     * @param filePath
     * @return
     */
    @Override
    @SQLPurposed
    public T delete(@NotNull T t, @NotNull String filePath) {
        return null;
    }

    /**
     * Overwrites content of the provided database file with new content
     *
     * @param filePath path to destination database file
     * @param data     what to write in destination database file
     * @return returns true if process succeeded false otherwise
     */
    @Override
    @JSONPurposed
    public boolean writeToFile(@NotNull String filePath, T data) {
        return false;
    }
}
