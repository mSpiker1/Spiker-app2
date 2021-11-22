/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Matthew Spiker
 */

package inv.inventorymanagementapplication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class PriceController implements Initializable {
    //initialize all fxml objects
    @FXML
    private Label errorLabel;
    @FXML
    private TextField priceText;

    //initialize method to run when scene is loaded
    public void initialize(URL location, ResourceBundle resources){
        //make sure current item is not null before trying to load this, or issues occur when loading other scenes
        if(AppUtilSettings.getCurrentItem() != null) {
            //grab current item's price and remove $ char from it
            String price = AppUtilSettings.getCurrentItem().getValue();
            price = price.replace("$", "");

            //set the TextField to contain the current item's price already
            priceText.setText(price);
        }
    }

    //when back button is clicked
    @FXML
    protected void onBackClick() throws IOException {
        //return to main edit menu
        AppManager.popUp(0, "Edit an Item");
    }

    //when save button is clicked
    @FXML
    protected void onSaveClick() throws IOException {
        //verify that the input price text is valid
        if(AppUtilSettings.verifyPrice(priceText.getText())){
            //display error and exit method if price field is not input correctly
            errorLabel.setText("Error: Price field not input correctly");
            return;
        } else if(AppUtilSettings.getCurrentItem().getValue().equals("$" + priceText.getText())){
            //display a unique error label if price has not been change and exit method
            errorLabel.setText("Error: Price has not been changed");
            return;
        }

        //create a price string in the correct USD format
        DecimalFormat cents = new DecimalFormat("0.00");
        String price = "$" + cents.format(Double.parseDouble(priceText.getText()));

        //get item that is being edited
        Item item = AppUtilSettings.getCurrentItem();

        //get the position of the item in the list
        int itemPos = InvManager.getItemPos(item);

        //replace item #itemPos's Value with new value string
        InvManager.editPrice(itemPos, price);

        //return to main edit menu
        AppManager.popUp(0, "Edit an Item");
    }
}
