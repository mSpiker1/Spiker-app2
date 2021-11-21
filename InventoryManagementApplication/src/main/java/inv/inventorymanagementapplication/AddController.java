package inv.inventorymanagementapplication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    //initialize all fxml objects first

    //initialize method to run when scene is loaded
    @Override
    public void initialize(URL location, ResourceBundle resources){
        //set max chars in name text area to 256

        //set max chars in serial1 text field to 1

        //set max chars in serial2 text field to 3

        //set max chars in serial3 text field to 3

        //set max chars in serial4 text field to 3
    }

    //when a key is typed in name text area
    @FXML
    protected void onNameTyped(){
        //update the char count label
    }

    @FXML
    protected void onSaveClick(){
        //convert all text fields to their respective string values

        //verify that all fields are entered correctly using verify methods in AppManager class

        //verify the serial number does not match an existing serial number

        //create a new item with the strings filling the appropriate values

        //add the new item to the main list in InvManager

        //clear all fields and reset character count label
    }
}
