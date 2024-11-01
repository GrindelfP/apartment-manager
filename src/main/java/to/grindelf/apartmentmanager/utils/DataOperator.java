package to.grindelf.apartmentmanager.utils;

import org.jetbrains.annotations.NotNull;
import to.grindelf.apartmentmanager.annonations.JSONPurposed;
import to.grindelf.apartmentmanager.annonations.SQLPurposed;

import java.util.List;

public interface DataOperator<T> {

    /**
     * Returns the content of a file.
     *
     * @param filePath path to destination file
     * @return returns the content of a file.
     */
    @JSONPurposed
    T readFile(@NotNull String filePath);

    @SQLPurposed
    T get(@NotNull T t, @NotNull String filePath);

    @SQLPurposed
    @NotNull
    List<T> getAll(@NotNull String filePath);

    @SQLPurposed
    T post(@NotNull T t, @NotNull String filePath);

    @SQLPurposed
    T update(@NotNull T t, @NotNull String filePath);

    @SQLPurposed
    T delete(@NotNull T t, @NotNull String filePath);

    /**
     * Overwrites content of the provided file with new content
     *
     * @param filePath path to destination file
     * @param data     what to write in destination file
     * @return returns true if process succeeded false otherwise
     */
    @JSONPurposed
    boolean writeToFile(@NotNull String filePath, T data);
}
