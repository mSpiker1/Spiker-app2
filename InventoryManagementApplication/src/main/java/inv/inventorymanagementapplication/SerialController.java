/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Matthew Spiker
 */

package inv.inventorymanagementapplication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SerialController implements Initializable {
    //initialize all fxml objects
    @FXML
    private Label errorLabel;
    @FXML
    private TextField serial1;
    @FXML
    private TextField serial2;
    @FXML
    private TextField serial3;
    @FXML
    private TextField serial4;

    //initialize method to run when scene is loaded, sets maximum text field lengths for each text field
    @Override
    public void initialize(URL location, ResourceBundle resources){
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

        //make sure current item is not null before trying to load this, or issues occur when loading other scenes
        if(AppManager.getCurrentItem() != null) {
            //grab the current item's serial number and split it into the four parts without hyphens
            String serial = AppManager.getCurrentItem().getSerial();
            String[] s = serial.split("-");

            //set the TextFields to have the current serial number already displayed for the user
            serial1.setText(s[0]);
            serial2.setText(s[1]);
            serial3.setText(s[2]);
            serial4.setText(s[3]);
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
        //AppManager class object
        AppManager am = new AppManager();

        //combine all TextField inputs to one serial number string
        String serial = serial1.getText().toUpperCase() + "-" + serial2.getText().toUpperCase() + "-" +
                serial3.getText().toUpperCase() + "-" + serial4.getText().toUpperCase();

        //verify that the serial number entered is a valid format
        if(AppManager.verifySerial(serial)){
            //set error message and exit method if serial number is not correctly input
            errorLabel.setText("Error: Serial number fields not input correctly");
            return;
        }

        //verify that the serial number does not match an existing serial number
        if(am.serialMatch(serial)){
            //if serial number is the same as current item, display unique error and exit method
            if(AppManager.getCurrentItem().getSerial().equals(serial)){
                errorLabel.setText("Error: Serial number has not been changed");
                return;
            }

            //set error label and exit method if a matching serial number is found
            errorLabel.setText("Error: Serial number already exists");
            return;
        }

        //if method executes to this point, get the item in the list that is being edited
        Item item = AppManager.getCurrentItem();

        //get the position in the list of the item
        int itemPos = InvManager.getItemPos(item);

        //replace item #itemPos's serial number with the new serial number string
        InvManager.editSerial(itemPos, serial);

        //return to the main edit menu
        AppManager.popUp(0, "Edit an Item");
    }
}
