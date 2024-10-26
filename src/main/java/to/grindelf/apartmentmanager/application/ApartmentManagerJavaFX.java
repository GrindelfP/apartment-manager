package to.grindelf.apartmentmanager.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;

public class ApartmentManagerJavaFX extends Application {

    // TODO: Make transition from Login view to Signup view

    @Override
    public void start(javafx.stage.@NotNull Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/to/grindelf/apartmentmanager/application/desktop/login-view.fxml"
        )
        );
        primaryStage.setTitle("Apartment Manager");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();

    }

    public static void startJavaFxApplication() {
        launch();
    }
}
