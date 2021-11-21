package inv.inventorymanagementapplication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class NameController implements Initializable {
    //initialize all fxml objects

    //initialize method to run when scene is loaded
    public void initialize(URL location, ResourceBundle resources){
        //set max characters in nameText text area to 256

        //set the TextField to contain the current item's name already
    }

    //when back button is clicked
    @FXML
    protected void onBackClick(){
        //return to main edit menu
    }

    //when save button is clicked
    @FXML
    protected void onSaveClick(){
        //verify that the input price text is valid
            //display error and exit method if price field is not input correctly
        //display a unique error label if price has not been changed and exit method

        //get item that is being edited from AppManager

        //get the position of the item in the list

        //replace item #itemPos's Value with new value string

        //return to main edit menu
    }
}
