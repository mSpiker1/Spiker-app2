package inv.inventorymanagementapplication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SerialController implements Initializable {
    //initialize all fxml objects

    //initialize method to run when scene is loaded, sets maximum text field lengths for each text field
    @Override
    public void initialize(URL location, ResourceBundle resources){
        //set max characters in serial1 text field to 1

        //set max characters in serial2 text field to 3

        //set max characters in serial3 text field to 3

        //set max characters in serial4 text field to 3

        //grab the current item's serial number and split it into the four parts without hyphens

        //set the TextFields to have the current serial number already displayed for the user
    }

    //when back button is clicked
    @FXML
    protected void onBackClick() throws IOException {
        //return to main edit menu
    }

    //when save button is clicked
    @FXML
    protected void onSaveClick() throws IOException {
        //combine all TextField inputs to one serial number string

        //verify that the serial number entered is a valid format
            //set error message and exit method if serial number is not correctly input

        //verify that the serial number does not match an existing serial number
            //if serial number is the same as current item, display unique error and exit method

            //set error label and exit method if a matching serial number is found

        //if method executes to this point, get the item in the list that is being edited from AppManager

        //get the position in the list of the item

        //replace item #itemPos's serial number with the new serial number string

        //return to the main edit menu
    }
}
