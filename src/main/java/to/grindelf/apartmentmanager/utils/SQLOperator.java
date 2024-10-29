package to.grindelf.apartmentmanager.utils;

import org.jetbrains.annotations.NotNull;
import to.grindelf.apartmentmanager.annonations.JSONPurposed;
import to.grindelf.apartmentmanager.annonations.SQLPurposed;

public class SQLOperator<T> implements DataOperator<T> {


    public SQLOperator() {
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
    public T getAll(@NotNull String filePath) {
        return null;
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
