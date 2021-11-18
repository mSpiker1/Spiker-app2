package inv.inventorymanagementapplication;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class InventoryManagementApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        //send the primary stage to AppManager to handle the stages and scenes
        AppManager.setMainStage(primaryStage);

        //load the main stage up with the correct scene
        AppManager.reloadMainStage();
    }

    public static void main(String[] args) {
        launch();
    }
}

