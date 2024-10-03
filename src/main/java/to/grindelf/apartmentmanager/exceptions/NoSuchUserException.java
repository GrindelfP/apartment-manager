package to.grindelf.apartmentmanager.exceptions;

public class NoSuchUserException extends Exception {

    public NoSuchUserException() {
        System.err.println("No such authorized user!");
    }
}
