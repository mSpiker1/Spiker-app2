package inv.inventorymanagementapplication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AppManager {
    //create the static stage object private to the class, and a static scene object to be the main scene
    private static Stage mainStage = new Stage();

    //String to store messages across controllers (mostly for confirmation popups)
    private static String storeString;

    //boolean, false by default, switch to true when clicking confirm and false after performing a confirmed method
    private static boolean confirm;

    ////////////////////////////////////////////
    ////         Utility Methods            ////
    ////////////////////////////////////////////

    //method to load the main stage and scene, and reload it after initial use
    public static void reloadMainStage() throws IOException {
        //create a new main scene
        Scene mainScene;

        //load the main menu fxml file
        FXMLLoader mainScreenFxml = new FXMLLoader(InventoryManagementApplication.class.getResource("MainMenu.fxml"));

        //load the main scene
        mainScene = new Scene(mainScreenFxml.load(), 1366, 720);

        //set the stage up with a scene and title, then show it
        mainStage.setScene(mainScene);
        mainStage.setTitle("Inventory Manager");
        mainStage.show();
    }

    //reload all scenes and load one based on method input parameter, and sets title based on input string
    public static void popUp(int sceneNum, String title) throws IOException {
        //create an arraylist to fill with scenes
        ArrayList<Scene> popUpStages = new ArrayList<>();

        //create a new stage
        Stage popUp = new Stage();

        //load all pop up fxml files through fxmlloaders
        FXMLLoader editMenuFxml = new FXMLLoader(InventoryManagementApplication.class.getResource("EditMenu.fxml"));

        //save the fxmlloader as a scene in the popUpStages arraylist
        popUpStages.add(new Scene(editMenuFxml.load(), 350, 150));

        //set scene, title, and show popUp stage
        popUp.setScene(popUpStages.get(sceneNum));
        popUp.setTitle(title);
        popUp.show();
    }

    ////////////////////////////////////////////
    ////          SETTER METHODS            ////
    ////////////////////////////////////////////

    //stage setter method
    public static void setMainStage(Stage stage){
        //load the main stage
        mainStage = stage;

        //set it to be maximized by default
        mainStage.setResizable(false);
    }

    //storeString setter
    public static void setStoreString(String newString){
        storeString = newString;
    }

    //confirm setter
    public static void setConfirm(boolean newConfirm){
        confirm = newConfirm;
    }

    ////////////////////////////////////////////
    ////          GETTER METHODS            ////
    ////////////////////////////////////////////

    //storeString getter
    public String getStoreString(){
        return storeString;
    }

    //confirm getter
    public boolean getConfirm(){
        return confirm;
    }
}
