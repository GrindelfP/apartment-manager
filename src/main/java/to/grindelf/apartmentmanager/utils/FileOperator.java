package to.grindelf.apartmentmanager.utils;

public interface FileOperator<T> {
    T readFile();

    boolean writeToFile();
}
