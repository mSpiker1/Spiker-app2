/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Matthew Spiker
 */

package inv.inventorymanagementapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    //initialize all fxml objects
    @FXML
    private TextArea nameText;
    @FXML
    private TextField serial1;
    @FXML
    private TextField serial2;
    @FXML
    private TextField serial3;
    @FXML
    private TextField serial4;
    @FXML
    private TextField priceText;
    @FXML
    private Label countLabel;
    @FXML
    private Label errorLabel;

    //initialize method to run when scene is loaded
    @Override
    public void initialize(URL location, ResourceBundle resources){
        //set max characters in nameText text area to 256
        nameText.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().length() <= 256 ? change : null));

        //set max characters in serial1 text field to 1
        serial1.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().length() <= 1 ? change : null));

        //set max characters in serial2 text field to 3
        serial2.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().length() <= 3 ? change : null));

        //set max characters in serial3 text field to 3
        serial3.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().length() <= 3 ? change : null));

        //set max characters in serial4 text field to 3
        serial4.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().length() <= 3 ? change : null));
    }

    //when a key is typed in the name text area
    @FXML
    protected void onNameTyped(){
        //get the length of the text in the name text area, store in variable charCount
        int charCount = nameText.getLength();

        //if charCount > 256, set it to 256 (formatter prevents it from going higher than 256 anyway)
        if(charCount > 256){charCount = 256;}

        //update the character count label
        countLabel.setText("Character count: " + charCount + "/256");
    }

    @FXML
    protected void onSaveClick(){
        //AppManager class object
        AppUtilSettings as = new AppUtilSettings();

        //convert all text fields to their respective strings
        String serial = serial1.getText().toUpperCase() + "-" + serial2.getText().toUpperCase() + "-" +
                serial3.getText().toUpperCase() + "-" + serial4.getText().toUpperCase();
        String name = nameText.getText();
        String price = priceText.getText();

        //verify all fields are properly input separately to output specific errors
        if(AppUtilSettings.verifyName(name)){
            //set error message and exit method if name is not correctly input
            errorLabel.setText("Error: Name field is not input correctly");
            return;
        } else if(AppUtilSettings.verifySerial(serial)){
            //set error message and exit method if serial number is not correctly input
            errorLabel.setText("Error: Serial number fields not input correctly");
            return;
        } else if(AppUtilSettings.verifyPrice(price)){
            //set error message and exit method if price is not correctly input
            errorLabel.setText("Error: Price field is not input correctly");
            return;
        }

        //verify that the serial number does not match an existing serial number
        if(as.serialMatch(serial)){
            //set error label and exit method if a matching serial number is found
            errorLabel.setText("Error: Serial number already exists");
            return;
        }

        //set the price variable to the correct format
        DecimalFormat cents = new DecimalFormat("0.00");
        price = "$" + cents.format(Double.parseDouble(price));

        //create a new item and fill it with the appropriate strings
        Item newItem = new Item();
        newItem.setName(name);
        newItem.setSerial(serial);
        newItem.setValue(price);

        //add newItem to the list of items
        InvManager.addItem(newItem);

        //update item count in main menu field
        FXMLLoader loader = AppManager.getMainMenuFxml();
        MenuController mc = loader.getController();
        mc.updateItemCount();

        //clear all fields and reset the character count so the user can enter a new item
        nameText.clear();
        priceText.clear();
        serial1.clear();
        serial2.clear();
        serial3.clear();
        serial4.clear();
        countLabel.setText("Character count: 0/256");
        errorLabel.setText("");
    }
}
