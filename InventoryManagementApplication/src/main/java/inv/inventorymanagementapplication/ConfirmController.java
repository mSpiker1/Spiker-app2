/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Matthew Spiker
 */

package inv.inventorymanagementapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmController implements Initializable {
    //initialize FXML objects
    @FXML
    private Label promptLabel;

    //initialize method to load the proper prompt
    public void initialize(URL location, ResourceBundle resources){
        //if statement to determine the prompt
        if(AppManager.getConfirm() == 1){
            //set label
            promptLabel.setText("Are you sure you want to clear the list?");
        } else if(AppManager.getConfirm() == 2){
            //set label
            promptLabel.setText("Are you sure you want to remove this item?");
        }
    }

    //if the confirm button is clicked
    @FXML
    protected void onConfirmClick(){
        //if statement to determine what is being confirmed
        if(AppManager.getConfirm() == 1){
            //call clearConfirmed method
            clearConfirmed();
        } else if(AppManager.getConfirm() == 2){
            //call clearConfirmed method
            removeConfirmed();

            //reset currentItem to null
            AppManager.setCurrentItem(null);
        }

        //reset the confirm value
        AppManager.setConfirm(0);

        //close the confirmation window
        AppManager.closePopUp();
    }

    //if the cancel button is clicked
    @FXML
    protected void onCancelClick(){
        //close the confirmation window
        AppManager.closePopUp();
    }

    //method for when list clear is confirmed
    public void clearConfirmed(){
        //clear inventory list
        InvManager.clearList();

        //reset main menu title to default
        AppManager.setMainStageTitle("Inventory Manager");
    }

    //method for when remove is confirmed
    public void removeConfirmed(){
        //set up MenuController object
        FXMLLoader loader = AppManager.getMainMenuFxml();
        MenuController mc = loader.getController();

        //get the currently selected item
        Item item = AppManager.getCurrentItem();

        //get the position of the current item from InvManager's getItemPos method
        int itemPos = InvManager.getItemPos(item);

        //remove the item from the observable list through InvManager's removeItem method, then update item count
        InvManager.removeItem(itemPos);
        mc.updateItemCount();
    }
}
