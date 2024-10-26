package to.grindelf.apartmentmanager.utils;

import org.jetbrains.annotations.NotNull;

public class JsonOperator<T> implements DataOperator<T> {

    public JsonOperator() {
    }

    /**
     * Returns the content of a JSON file.
     *
     * @param filePath path to destination file
     * @return returns the content of a JSON file.
     */
    public T readFile(@NotNull String filePath) {
        return null;
    }

    /**
     * Overwrites content of the provided JSON file with new content
     *
     * @param filePath path to destination JSON file
     * @param data     what to write in destination JSON file
     * @return returns true if process succeeded false otherwise
     */
    public boolean writeToFile(@NotNull String filePath, T data) {
        return false;
    }

}
