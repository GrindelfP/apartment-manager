package to.grindelf.apartmentmanager.application.desktop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import static to.grindelf.apartmentmanager.utils.ConstantValues.*;

public class AdminController {
    @FXML
    public Text text;
    @FXML
    public Button usersButton;
    @FXML
    public Button bookingsButton;
    @FXML
    public Button logOut;
    @FXML
    public Button roomsButton;

    public void handleUsers(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) usersButton.getScene().getWindow();
        stage.close();

        URL fxmlSignUpLocation = getClass().getResource(
                USERS_ADMIN_VIEW_PATH
        );
        if (fxmlSignUpLocation == null) {
            System.err.println(FXML_NO_FOUND_ERROR_MESSAGE); return;
        }
        FXMLLoader loader = new FXMLLoader(fxmlSignUpLocation);

        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.setTitle(USERS_ADMIN_WINDOW_TITLE);
        newStage.setScene(new Scene(root));
        newStage.setWidth(WINDOW_WIDTH);
        newStage.setHeight(WINDOW_HEIGHT);
        newStage.show();
    }

    public void handleBookings(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) bookingsButton.getScene().getWindow();
        stage.close();

        URL fxmlSignUpLocation = getClass().getResource(
                BOOKINGS_ADMIN_VIEW_PATH
        );
        if (fxmlSignUpLocation == null) {
            System.err.println(FXML_NO_FOUND_ERROR_MESSAGE); return;
        }
        FXMLLoader loader = new FXMLLoader(fxmlSignUpLocation);

        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.setTitle(BOOKINGS_ADMIN_WINDOW_TITLE);
        newStage.setScene(new Scene(root));
        newStage.setWidth(WINDOW_WIDTH);
        newStage.setHeight(WINDOW_HEIGHT);
        newStage.show();
    }

    public void handleLogOut(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) logOut.getScene().getWindow();
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

        newStage.setTitle(USERS_ADMIN_WINDOW_TITLE);
        newStage.setScene(new Scene(root));
        newStage.setWidth(WINDOW_WIDTH);
        newStage.setHeight(WINDOW_HEIGHT);
        newStage.show();
    }

    public void handleRooms(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) roomsButton.getScene().getWindow();
        stage.close();

        URL fxmlLogInLocation = getClass().getResource(
                ROOMS_ADMIN_VIEW_PATH
        );
        if (fxmlLogInLocation == null) {
            System.err.println(FXML_NO_FOUND_ERROR_MESSAGE);
            return;
        }
        FXMLLoader loader = new FXMLLoader(fxmlLogInLocation);

        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.setTitle(ROOMS_ADMIN_WINDOW_TITLE);
        newStage.setScene(new Scene(root));
        newStage.setWidth(WINDOW_WIDTH);
        newStage.setHeight(WINDOW_HEIGHT);
        newStage.show();
    }
}
