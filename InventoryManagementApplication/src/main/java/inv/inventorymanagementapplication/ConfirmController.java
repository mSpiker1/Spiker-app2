package inv.inventorymanagementapplication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmController implements Initializable {
    //initialize fxml objects

    //initialize method to load the proper prompt
    public void initialize(URL location, ResourceBundle resources){
        //if-else statement to determine the prompt based on AppManager's confirm value

        //if confirm = 1, set prompt to "Are you sure you want to clear the list?"

        //if confirm = 2, set the prompt to "Are you sure you want to remove this item?"
    }

    //if the confirm button is clicked
    @FXML
    protected void onConfirmClick(){
        //if statement to determine what is being confirmed (via AppManager) and what method to call for it

        //if confirm = 1 call clearConfirmed method

        //if confirm = 2 call removeConfirmed method and reset AppManager's currentItem to null

        //reset the confirm value

        //close the confirmation window
    }

    //if the cancel button is clicked
    @FXML
    protected void onCancelClick(){
        //close the confirmation window
    }

    //method for when list clear is confirmed
    public void clearConfirmed(){
        //clear inventory list

        //reset main menu title to default "Inventory Manager"
    }

    //method for when remove is confirmed
    public void removeConfirmed(){
        //get access to MenuController using AppManager's mainFxml loader

        //get the currently selected item from AppManager

        //get the position of the current item from InvManager's getItemPos

        //remove the item from the observable list through InvManager's removeItem method, then update item count
    }
}
