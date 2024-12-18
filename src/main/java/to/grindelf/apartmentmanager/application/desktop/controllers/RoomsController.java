package to.grindelf.apartmentmanager.application.desktop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import static to.grindelf.apartmentmanager.utils.ConstantValues.*;

public class RoomsController {
    public Button goBackButton;
    public Text text;

    public void goBack(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();

        URL fxmlSignUpLocation = getClass().getResource(ADMIN_VIEW_PATH);
        if (fxmlSignUpLocation == null) {
            System.err.println(FXML_NO_FOUND_ERROR_MESSAGE);
            return;
        }

        FXMLLoader loader = new FXMLLoader(fxmlSignUpLocation);
        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.setTitle(ADMIN_WINDOW_TITLE);
        newStage.setScene(new Scene(root));
        newStage.setWidth(WINDOW_WIDTH);
        newStage.setHeight(WINDOW_HEIGHT);
        newStage.show();
    }
}
