package to.grindelf.apartmentmanager.exceptions;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException() {
        System.err.println("User already exists");
    }

}
