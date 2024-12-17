package to.grindelf.apartmentmanager.application.desktop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import to.grindelf.apartmentmanager.auth.UserDaoImpl;
import to.grindelf.apartmentmanager.domain.User;
import to.grindelf.apartmentmanager.exceptions.UserAlreadyExistsException;
import to.grindelf.apartmentmanager.utils.database.SQLOperator;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static to.grindelf.apartmentmanager.utils.ConstantValues.*;

public class SignupController {

    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public PasswordField confirmPasswordField;
    @FXML
    public Button registerButton;
    @FXML
    public Hyperlink loginSwitch;
    @FXML
    public Label errorMessage;

    @FXML
    public void handleSignup(ActionEvent actionEvent) {
        String userName = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!Objects.equals(password, confirmPassword)) {
            errorMessage.setText(SIGNUP_PASSWD_CONF_ERROR_MESSAGE);
        } else {
            User signUpUser = new User(userName, password);
            SQLOperator<User, String> operator = new SQLOperator<>();
            UserDaoImpl userDao = new UserDaoImpl(operator);

            try {
                userDao.save(signUpUser);
                showInfo();
            } catch (UserAlreadyExistsException e) {
                errorMessage.setText(e.getMessage());
            }
        }
    }

    private void showInfo() {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle(SUCCESS_ALERT_TITLE);
        infoAlert.setHeaderText(SUCCESS_ALERT_HEADER);
        infoAlert.setContentText(SUCCESS_ALERT_MESSAGE);

        infoAlert.setOnCloseRequest(dialogEvent -> {
            try {
                switchToLogin();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        infoAlert.show();
    }

    @FXML
    public void switchToLogin() throws IOException {
        Stage stage = (Stage) loginSwitch.getScene().getWindow();
        stage.close();

        URL fxmlLogInLocation = getClass().getResource(
                LOGIN_VIEW_PATH
        );
        if (fxmlLogInLocation == null) {
            System.err.println(FXML_NO_FOUND_ERROR_MESSAGE);
            return;
        }
        FXMLLoader loader = new FXMLLoader(fxmlLogInLocation);

        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.setTitle(LOGIN_WINDOW_TITLE);
        newStage.setScene(new Scene(root));
        newStage.setWidth(WINDOW_WIDTH);
        newStage.setHeight(WINDOW_HEIGHT);
        newStage.show();
    }
}
