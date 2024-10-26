package to.grindelf.apartmentmanager.utils;

import org.jetbrains.annotations.NotNull;

public interface DataOperator<T> {

    /**
     * Returns the content of a file.
     *
     * @param filePath path to destination file
     * @return returns the content of a file.
     */
    T readFile(@NotNull String filePath);

    /**
     * Overwrites content of the provided file with new content
     *
     * @param filePath path to destination file
     * @param data     what to write in destination file
     * @return returns true if process succeeded false otherwise
     */
    boolean writeToFile(@NotNull String filePath, T data);
}
