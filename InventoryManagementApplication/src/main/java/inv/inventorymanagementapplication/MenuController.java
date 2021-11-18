package inv.inventorymanagementapplication;

import javafx.fxml.FXML;

import java.io.IOException;

public class MenuController {
    @FXML
    protected void onEditClick() throws IOException {
        AppManager.popUp(0, "Edit Menu");
    }
}