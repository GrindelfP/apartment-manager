package to.grindelf.apartmentmanager.exceptions;

public class IllegalExecutionModeException extends Throwable {

    public IllegalExecutionModeException() {
        System.err.println("This execution mode is not supported!");
    }
}
