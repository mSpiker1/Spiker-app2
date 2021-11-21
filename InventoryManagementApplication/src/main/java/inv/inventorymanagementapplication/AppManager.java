/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Matthew Spiker
 */

package inv.inventorymanagementapplication;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class AppManager {
    //create the static stage object private to the class, and a static scene object to be the main scene
    private static Stage mainStage = new Stage();

    //int to manage confirmation window, main menu sets and confirmation gets to determine what to confirm
    //0 for nothing, 1 for clear list, 2 for removing an item
    private static int confirm = 0;

    //int to manage search criteria, search dropdown sets and search bar gets in order to know what parameters to search
    //0 for nothing, 1 for nameMode, 2 for serialMode
    private static int searchMode = 0;

    //Item object to represent a selected item, used by remove and edit item buttons
    private static Item currentItem;

    //create a new stage to use for pop up windows called popUp
    private static final Stage popUp = new Stage();

    //FXMLLoader object to hold main menu FXMLLoader, used to get the main controller and set error messages from
    //other controllers
    private static FXMLLoader mainFxml = new FXMLLoader();

    ////////////////////////////////////////////
    ////         Utility Methods            ////
    ////////////////////////////////////////////

    //method to verify price as a number that is greater than or equal to 0
    public static boolean verifyPrice(String num) {
        //if the string is null, return true
        if (num == null) {
            return true;
        }

        //try/catch to return true if parsing the string as a double gives an error
        try {
            Double.parseDouble(num);
        } catch (NumberFormatException nfe) {
            return true;
        }

        //return true if numeric value is less than 0, false otherwise as all verification tests have passed
        return Double.parseDouble(num) < 0;
    }

    //method to verify the name string is between 2-256 characters
    public static boolean verifyName(String name){
        //verify that the length of the name is between 2 and 256 characters
        return name.length() < 2 || name.length() > 256;
    }

    //method to verify a serial number is the correct format
    public static boolean verifySerial(String serial){
        //remove dashes from serial string
        String serialTest = serial.replace("-", "");

        //verify the length of serial string is 10, it is alphanumeric, and the first character is not a number
        return serialTest.length() != 10 || !serialTest.matches("^[a-zA-Z0-9]*$") ||
                !verifyPrice(String.valueOf(serialTest.charAt(0)));
    }

    //see if a serial number matches an existing serial number
    public boolean serialMatch(String serial){
        //run a for loop through every item in the main list, test if it's serial number matches the string
        for(int i=0; i<InvManager.getList().size(); i++){
            //if serial number of item i matches serial string, return true
            if(InvManager.getList().get(i).getSerial().equals(serial)){return true;}
        }

        //return false if the for loop completes without issue
        return false;
    }

    //test if any serial numbers match after loading a file
    public boolean serialLoadTest(){
        //initialize a new observable list and copy the main list to it
        ObservableList<Item> tempList = InvManager.getList();

        //use nested for loops to test if any item's serial number matches another
        for(int i=0; i<InvManager.getList().size(); i++){
            for(int j=0; j<tempList.size(); j++){
                if(InvManager.getList().get(i).getSerial().equals(tempList.get(j).getSerial()) && i != j){
                    //return false if two serial numbers match
                    return false;
                }
            }
        }
        //return true if the method completes without issue
        return true;
    }

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
        popUp.setOnCloseRequest(event -> AppManager.setCurrentItem(null));
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

    //confirm setter
    public static void setConfirm(int newConfirm){
        confirm = newConfirm;
    }

    //search setter
    public static void setSearchMode(int search){
        searchMode = search;
    }

    //currentItem setter
    public static void setCurrentItem(Item newItem){
        currentItem = newItem;
    }

    //main stage title setter
    public static void setMainStageTitle(String newTitle){
        //change the title to the new string
        mainStage.setTitle(newTitle);
    }

    ////////////////////////////////////////////
    ////          GETTER METHODS            ////
    ////////////////////////////////////////////

    //confirm getter
    public static int getConfirm(){
        return confirm;
    }

    //search getter
    public static int getSearchMode(){
        return searchMode;
    }

    //currentItem getter
    public static Item getCurrentItem(){return currentItem;}

    //main menu fxml getter
    public static FXMLLoader getMainMenuFxml(){return mainFxml;}
}
