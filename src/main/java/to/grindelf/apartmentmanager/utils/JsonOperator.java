package to.grindelf.apartmentmanager.utils;

public class JsonOperator<T> implements FileOperator<T> {

    private final String filePath;

    public JsonOperator(String filePath) {
        this.filePath = filePath;
    }

    public T readFile() {
        return null;
    }

    public boolean writeToFile() {
        return false;
    }

}
