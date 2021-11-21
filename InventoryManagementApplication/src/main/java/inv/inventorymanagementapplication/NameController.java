/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Matthew Spiker
 */

package inv.inventorymanagementapplication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NameController implements Initializable {
    //initialize all fxml objects
    @FXML
    private TextArea nameText;
    @FXML
    private Label errorLabel;
    @FXML
    private Label countLabel;

    //initialize method to run when scene is loaded
    public void initialize(URL location, ResourceBundle resources){
        //set max characters in nameText text area to 256
        nameText.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().length() <= 256 ? change : null));

        //make sure current item is not null before trying to load this, or issues occur when loading other scenes
        if(AppManager.getCurrentItem() != null) {
            //set the TextField to contain the current item's name already
            nameText.setText(AppManager.getCurrentItem().getName());

            //update count label
            countLabel.setText("Character count: " + nameText.getText().length() + "/256");
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
        if(AppManager.verifyName(nameText.getText())){
            //display error and exit method if price field is not input correctly
            errorLabel.setText("Error: Name field not input correctly");
            return;
        } else if(AppManager.getCurrentItem().getName().equals(nameText.getText())){
            //display a unique error label if price has not been changed and exit method
            errorLabel.setText("Error: Name has not been changed");
            return;
        }

        //get item that is being edited
        Item item = AppManager.getCurrentItem();

        //get the position of the item in the list
        int itemPos = InvManager.getItemPos(item);

        //replace item #itemPos's Value with new value string
        InvManager.editName(itemPos, nameText.getText());

        //return to main edit menu
        AppManager.popUp(0, "Edit an Item");
    }

    //when key is typed in the text area
    @FXML
    protected void onNameTyped(){
        //update count label
        countLabel.setText("Character count: " + nameText.getText().length() + "/256");
    }
}
