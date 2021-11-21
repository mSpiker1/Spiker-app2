/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Matthew Spiker
 */

package inv.inventorymanagementapplication;

import javafx.fxml.FXML;
import java.io.IOException;

public class EditController {
    //when name is clicked
    @FXML
    protected void onNameClick() throws IOException {
        //load name edit fxml file over edit fxml
        AppManager.popUp(2, "Edit Item Name");
    }

    //when serial number is clicked
    @FXML
    protected void onSerialClick() throws IOException {
        //load serial edit fxml over edit fxml
        AppManager.popUp(3, "Edit Item Serial Number");
    }

    //when price is clicked
    @FXML
    protected void onPriceClick() throws IOException {
        //load price edit fxml over edit fxml
        AppManager.popUp(4, "Edit Item Price");
    }
}
