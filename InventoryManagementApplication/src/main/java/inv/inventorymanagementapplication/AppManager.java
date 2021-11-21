package inv.inventorymanagementapplication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class AppManager {
    //create the static stage object private to the class, and a static scene object to be the main scene
    private static Stage mainStage = new Stage();

    //static int to manage confirmation types across controllers, set in main menu, used in confirmation menu

    //static int to manage search mode (between name and serial search mode)

    //static Item object to represent a selected item, null by default, set in main menu, used in remove and edit

    //static stage separate from main stage, used for pop up windows

    //static fxml object to store main menu fxml, so main menu controller can be accessed in other controller classes
    private static FXMLLoader mainFxml = new FXMLLoader();

    ////////////////////////////////////////////
    ////         Utility Methods            ////
    ////////////////////////////////////////////

    //method to verify price as a number that is greater than or equal to 0
    //true indicates an error is found, false indicates no error is found
    public static boolean verifyPrice(String num){
        //if num is null, return true

        //try to parse the string, if an error is caught return true

        //if the number parsed to a double is less than 0, return true, otherwise return false
        return false;
    }

    //method to verify the name string is between 2-256 characters
    public static boolean verifyName(String name){
        //if the name is less than 2 or greater than 256 in length, return true

        //return false
        return false;
    }

    //method to verify a serial number is the correct format
    public static boolean verifySerial(String serial){
        //if the length of the string is incorrect, return true

        //if the string is not alphanumeric, return true

        //if the first character is not a letter, return true

        //return false
        return false;
    }

    //see if a serial number matches an existing serial number
    public boolean serialMatch(String serial){
        //run a for loop through every item in the main list, test if it's serial number matches the string
            //if serial number of item i matches serial string, return true

        //return false if the for loop completes without issue
        return false;
    }

    //test if any serial numbers match after loading a file
    public boolean serialLoadTest(){
        //create a copy of the current main list in a new observable list

        //use nested for loops to test if any item's serial number matches another
            //return false if two serial numbers match

        //return true
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
    public static void popUp(int sceneNum, String title){
        //create an arraylist to fill with scenes

        //create a new stage called popUp

        //load all pop up fxml files through fxmlloaders

        //save each fxmlloader as a scene in the popUpStages arraylist

        //set scene, title, and show popUp stage
    }

    //close pop up stage
    public static void closePopUp(){
        //close the popUp stage
    }

    //test if pop up stage is open
    public static boolean popUpIsOpen(){
        return false;
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
        //confirm = newConfirm;
    }

    //search setter
    public static void setSearchMode(int search){
        //searchMode = search;
    }

    //currentItem setter
    public static void setCurrentItem(Item newItem){
        //currentItem = newItem;
    }

    //main stage title setter
    public static void setMainStageTitle(String newTitle){
        //change the title to the new string
    }

    ////////////////////////////////////////////
    ////          GETTER METHODS            ////
    ////////////////////////////////////////////

    //confirm getter
    public static int getConfirm(){
        return 0;
    }

    //search getter
    public static int getSearchMode(){
        return 0;
    }

    //currentItem getter
    public static Item getCurrentItem(){return null;}

    //main menu fxml getter
    public static FXMLLoader getMainMenuFxml(){return null;}
}
