package to.grindelf.apartmentmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;

import static to.grindelf.apartmentmanager.utils.ConstantValues.*;

public class ApartmentManagerJavaFX extends Application {

    @Override
    public void start(javafx.stage.@NotNull Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                LOGIN_VIEW_PATH
        )
        );
        primaryStage.setTitle(LOGIN_WINDOW_TITLE);
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setWidth(WINDOW_WIDTH);
        primaryStage.setHeight(WINDOW_HEIGHT);
        primaryStage.show();

    }

    public static void startJavaFxApplication() {
        launch();
    }
}
