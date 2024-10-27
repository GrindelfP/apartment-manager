package to.grindelf.apartmentmanager.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class JsonOperator<T> implements DataOperator<T> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final TypeReference<T> typeReference;

    public JsonOperator(TypeReference<T> typeReference) {
        this.typeReference = typeReference;
    }

    /**
     * Returns the content of a JSON file.
     *
     * @param filePath path to destination file
     * @return returns the content of a JSON file.
     */
    public T readFile(@NotNull String filePath) {
        try {
            return objectMapper.readValue(new File(filePath), typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Overwrites content of the provided JSON file with new content
     *
     * @param filePath path to destination JSON file
     * @param data     what to write in destination JSON file
     * @return returns true if process succeeded, false otherwise
     */
    public boolean writeToFile(@NotNull String filePath, T data) {
        try {
            objectMapper.writeValue(new File(filePath), data);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
