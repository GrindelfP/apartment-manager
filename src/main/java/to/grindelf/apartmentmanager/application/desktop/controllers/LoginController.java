package to.grindelf.apartmentmanager.application.desktop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import to.grindelf.apartmentmanager.auth.UserDaoJson;
import to.grindelf.apartmentmanager.domain.User;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorMessage;
    @FXML
    private Hyperlink switchToRegister;

    private final UserDaoJson userDao = new UserDaoJson();

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            User user = userDao.getUser(username);
            if (user.getPassword().equals(password)) {
                // Успешный вход
                errorMessage.setText("Login successful!");
            } else {
                errorMessage.setText("Invalid credentials");
            }
        } catch (Exception e) {
            errorMessage.setText("User does not exist");
        }
    }

    @FXML
    private void switchToRegister() {
        // Логика переключения на окно регистрации
        Stage stage = (Stage) switchToRegister.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "to/grindelf/apartmentmanager/application/desktop/signup-view.fxml"
        ));
        try {
            stage.getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
