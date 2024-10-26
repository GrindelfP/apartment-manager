package to.grindelf.apartmentmanager.utils;

import org.jetbrains.annotations.NotNull;

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
    public T readFile(@NotNull String filePath) {
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
    public boolean writeToFile(@NotNull String filePath, T data) {
        return false;
    }
}
