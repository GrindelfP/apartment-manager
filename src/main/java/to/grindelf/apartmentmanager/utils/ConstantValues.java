package to.grindelf.apartmentmanager.utils;

import java.util.List;

/**
 * Class for storing constant values for the project.
 */
public class ConstantValues {

    // DESKTOP APP PARAMS
    public final static int WINDOW_HEIGHT = 500;
    public final static int WINDOW_WIDTH = 600;

    public final static String LOGIN_WINDOW_TITLE = "Log in";
    public final static String SIGNUP_WINDOW_TITLE = "Sign up";
    public final static String GENERAL_WINDOW_TITLE = "Apartment manager";
    public final static String SUCCESS_ALERT_TITLE = "Success!";

    public final static String SIGNUP_VIEW_PATH = "/to/grindelf/apartmentmanager/application/desktop/signup-view.fxml";
    public final static String LOGIN_VIEW_PATH = "/to/grindelf/apartmentmanager/application/desktop/login-view.fxml";
    public final static String GENERAL_VIEW_PATH = "/to/grindelf/apartmentmanager/application/desktop/general-view.fxml";

    public final static String FXML_NO_FOUND_ERROR_MESSAGE = "FXML file not found!";
    public final static String LOGIN_ERROR_MESSAGE = "Either your login or password is wrong. Try again!";
    public final static String SIGNUP_PASSWD_CONF_ERROR_MESSAGE = "Your passwords in both fields should match!!";
    public final static String SUCCESS_ALERT_MESSAGE = "You've been successfully signed up!";

    public final static String SUCCESS_ALERT_HEADER = "Now you will be redirected to login view and required to perform log in.";

    // DAO PARAMS
    public final static String USER_JSON_FILE_PATH = "src/main/resources/users.json";
    public final static String USER_DB_FILE_PATH = "src/main/resources/users.db";

    // SQL PARAMS
    public enum DatabaseTableNames {

        USERS_TABLE {
            @Override
            public String toString() {
                return "users";
            }
        },

        APARTMENTS_TABLE {
            @Override
            public String toString() {
                return "apartments";
            }
        }
    }
    public final static List<String> USERS_COLUMNS_NAMES = List.of(
            "name",
            "password",
            "status"
    );
    public final static String USERS_COLUMN_KEY_COLUMN_NAME = USERS_COLUMNS_NAMES.getFirst();

}
