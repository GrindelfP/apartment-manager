package to.grindelf.apartmentmanager.exceptions;

/**
 * Exception for JSON parsing errors.
 */
public class JSONException extends RuntimeException {
    public JSONException(String message) {
        super(message);
    }
}
