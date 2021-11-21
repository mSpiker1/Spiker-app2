package inv.inventorymanagementapplication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    //initialize all FXML components

    //initialize method to run when scene is loaded
    @Override
    public void initialize(URL location, ResourceBundle resources){
        //set what columns should inherit which item properties

        //assign the main list from InvManager to the table
    }

    //add button is clicked
    @FXML
    protected void onAddClick(){
        //create a new stage with the NewItem fxml scene
    }

    //edit button is clicked
    @FXML
    protected void onEditClick(){
        //grab the currently selected item from the table view

        //if item is null, set error label and exit method

        //set the item to be the current item in AppManager (used by EditController)

        //create a new stage with the EditMenu fxml scene
    }

    //remove button is clicked
    @FXML
    protected void onRemoveClick(){
        //set confirm to 2 for remove item (used by ConfirmController)

        //grab the current item from the table view

        //if item is null, set error label and exit method

        //set the item to be the current item in AppManager (used by ConfirmController)

        //open the confirmation popUp window
    }

    //clear all button is clicked
    @FXML
    protected void onClearClick(){
        //set confirm to 1 for clear list (used by ConfirmController)

        //open the confirmation popUp window
    }

    //refresh button is clicked
    @FXML
    protected void onRefreshClick(){
        //update item count

        //clear the search bar text

        //reset search dropdown
    }

    //when serial is selected from search dropdown
    @FXML
    protected void serialSearchSelect(){
        //set search mode integer in AppManager to 2

        //change the text on the search bar to "Serial Number"

        //close dropdown

        //call searchKeyTyped method to update the list if search bar was already typed in
    }

    //when name is selected from search dropdown
    @FXML
    protected void nameSearchSelect(){
        //set search mode integer to 1

        //change the text on the search bar to "Name"

        //close dropdown

        //call searchKeyTyped method to update the list if search bar was already typed in
    }

    //when search bar is typed in
    @FXML
    protected void searchKeyTyped(){
        //if the search bar is empty, exit the method

        //if statement to determine which search function to call
            //if searchMode is 1, call searchByName method in InvManager

            //if searchMode is 2, call searchBySerial method in InvManager
    }

    //load option is clicked
    @FXML
    protected void selectLoad(){
        //create a new stage

        //create a fileChooser object and a File object to store the chosen file

        //set starting directory of filechooser

        //launch fileChooser

        //if newFile is null, exit the method as the filechooser has been closed

        //clear the main list before loading in a new one

        //grab the extension to a string

        //call to invFromFile to read the file to the main list, set boolean to catch an error if it is false

        //set error label and exit method if an error is caught

        //call serialLoadTest to test if any duplicate serial numbers are in the list
            //if a match is found, clear the list and set the error label, exit method

        //if the whole method has been successful so far, change the title of the main menu to reflect the current file
    }

    //save option is clicked
    @FXML
    protected void selectSave() {
        //check if the list is empty
            //exit method if list is empty

        //create a new stage to host a fileChooser

        //create a fileChooser

        //set the filechooser to default to json, tsv(.txt), or html file

        //set the default filechooser starting directory

        //open the stage and create a file object where the user wants

        //if newFile is null, exit the method as the filechooser has been closed

        //grab the file's extension and store it in a string "ext" to determine which method to call

        //call the invToFile method with the parameters ext and saveFile
    }
}