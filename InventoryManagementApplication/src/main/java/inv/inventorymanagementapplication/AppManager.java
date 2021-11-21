/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Matthew Spiker
 */

package inv.inventorymanagementapplication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class AppManager {

    private AppManager() {
        throw new IllegalStateException("JavaFX Application/Stage Manager Utility Class");
    }

    //create the static stage object private to the class, and a static scene object to be the main scene
    private static Stage mainStage = new Stage();

    //create a new stage to use for pop up windows called popUp
    private static final Stage popUp = new Stage();

    //FXMLLoader object to hold main menu FXMLLoader, used to get the main controller and set error messages from
    //other controllers
    private static FXMLLoader mainFxml = new FXMLLoader();

    //method to load the main stage and scene, and reload it after initial use
    public static void loadMainStage() throws IOException {
        //create a new main scene
        Scene mainScene;

        //load the main menu fxml file
        mainFxml = new FXMLLoader(InventoryManagementApplication.class.getResource("MainMenu.fxml"));

        //load the main scene
        mainScene = new Scene(mainFxml.load(), 1366, 720);

        //set the stage up with a scene and title, then show it
        mainStage.setScene(mainScene);
        mainStage.setTitle("Inventory Manager");
        mainStage.show();
    }

    //reload all scenes and load one based on method input parameter, and sets title based on input string
    public static void popUp(int sceneNum, String title) throws IOException {
        //create an arraylist to fill with scenes
        ArrayList<Scene> popUpStages = new ArrayList<>();

        //load all pop up fxml files through fxmlloaders
        FXMLLoader editMenuFxml = new FXMLLoader(InventoryManagementApplication.class.getResource("EditMenu.fxml"));
        FXMLLoader confirmMenuFxml = new FXMLLoader(InventoryManagementApplication.class.getResource("ConfirmMenu.fxml"));
        FXMLLoader nameEditFxml = new FXMLLoader(InventoryManagementApplication.class.getResource("NameEdit.fxml"));
        FXMLLoader serialEditFxml = new FXMLLoader(InventoryManagementApplication.class.getResource("SerialEdit.fxml"));
        FXMLLoader priceEditFxml = new FXMLLoader(InventoryManagementApplication.class.getResource("PriceEdit.fxml"));
        FXMLLoader newItemFxml = new FXMLLoader(InventoryManagementApplication.class.getResource("NewItem.fxml"));

        //save the fxmlloader as a scene in the popUpStages arraylist
        popUpStages.add(new Scene(editMenuFxml.load(), 350, 150));
        popUpStages.add(new Scene(confirmMenuFxml.load(), 300, 150));
        popUpStages.add(new Scene(nameEditFxml.load(), 350, 150));
        popUpStages.add(new Scene(serialEditFxml.load(), 350, 150));
        popUpStages.add(new Scene(priceEditFxml.load(), 350, 150));
        popUpStages.add(new Scene(newItemFxml.load(), 450, 300));

        //set scene, title, and show popUp stage
        popUp.setScene(popUpStages.get(sceneNum));
        popUp.setTitle(title);
        popUp.setResizable(false);
        popUp.show();

        //reset current item to null if the window is closed by the user (not the controller, red x is clicked)
        //this is to prevent any weird stuff happened when editing or removing items if the window is prematurely closed
        popUp.setOnCloseRequest(event -> AppUtilSettings.setCurrentItem(null));
    }

    //close pop up stage
    public static void closePopUp(){
        //close the popUp stage
        popUp.close();
    }

    //test if pop up stage is open
    public static boolean popUpIsOpen(){
        return popUp.isShowing();
    }

    //stage setter method
    public static void setMainStage(Stage stage){
        //load the main stage
        mainStage = stage;

        //set it to be maximized by default
        mainStage.setResizable(false);
    }

    //main stage title setter
    public static void setMainStageTitle(String newTitle){
        //change the title to the new string
        mainStage.setTitle(newTitle);
    }

    //main menu fxml getter
    public static FXMLLoader getMainMenuFxml(){return mainFxml;}
}

