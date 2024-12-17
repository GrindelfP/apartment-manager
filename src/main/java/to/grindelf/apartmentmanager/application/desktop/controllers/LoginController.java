package to.grindelf.apartmentmanager.application.desktop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import to.grindelf.apartmentmanager.auth.UserDaoImpl;
import to.grindelf.apartmentmanager.domain.User;
import to.grindelf.apartmentmanager.exceptions.NoSuchUserException;
import to.grindelf.apartmentmanager.utils.database.SQLOperator;

import java.io.IOException;
import java.net.URL;

import static to.grindelf.apartmentmanager.utils.ConstantValues.*;

public class LoginController {

    public Button loginButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorMessage;
    @FXML
    private Hyperlink signUpSwitch;

    @FXML
    private void handleLogin() {

        String userName = usernameField.getText();
        String password = passwordField.getText();
        User loginUser = new User(userName, password);
        SQLOperator<User, String> operator = new SQLOperator<>();
        UserDaoImpl userDao = new UserDaoImpl(operator);

        try {
            User storedUser = userDao.getUserByName(userName);

            if (storedUser.equals(loginUser)) {
                openGeneralView();
            }

        } catch (NoSuchUserException e) {
            errorMessage.setText(LOGIN_ERROR_MESSAGE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void openGeneralView() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();

        URL fxmlSignUpLocation = getClass().getResource(
                GENERAL_VIEW_PATH
        );
        if (fxmlSignUpLocation == null) {
            System.err.println(FXML_NO_FOUND_ERROR_MESSAGE); return;
        }
        FXMLLoader loader = new FXMLLoader(fxmlSignUpLocation);

        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.setTitle(GENERAL_WINDOW_TITLE);
        newStage.setScene(new Scene(root));
        newStage.setWidth(WINDOW_WIDTH);
        newStage.setHeight(WINDOW_HEIGHT);
        newStage.show();
    }

    @FXML
    private void switchToSignup() throws IOException {

        Stage stage = (Stage) signUpSwitch.getScene().getWindow();
        stage.close();

        URL fxmlSignUpLocation = getClass().getResource(
                SIGNUP_VIEW_PATH
        );
        if (fxmlSignUpLocation == null) {
            System.err.println(FXML_NO_FOUND_ERROR_MESSAGE); return;
        }
        FXMLLoader loader = new FXMLLoader(fxmlSignUpLocation);

        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.setTitle(SIGNUP_WINDOW_TITLE);
        newStage.setScene(new Scene(root));
        newStage.setWidth(WINDOW_WIDTH);
        newStage.setHeight(WINDOW_HEIGHT);
        newStage.show();
    }
}
