/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Matthew Spiker
 */

package inv.inventorymanagementapplication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    //initialize all FXML components
    @FXML
    private Label errorLabel;
    @FXML
    private TableView<Item> mainTable;
    @FXML
    private TableColumn<Item, String> nameCol;
    @FXML
    private TableColumn<Item, String> serCol;
    @FXML
    private TableColumn<Item, String> valCol;
    @FXML
    private Label itemCount;
    @FXML
    private ImageView refreshButton;
    @FXML
    private SplitMenuButton searchDropdown;
    @FXML
    private TextField searchBar;

    //error label constants
    private static final String POPUPOPEN = "Error: Please close existing pop-up window first";

    //initialize method to run when scene is loaded
    @Override
    public void initialize(URL location, ResourceBundle resources){
        //set what columns should inherit which item properties
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        serCol.setCellValueFactory(new PropertyValueFactory<>("serial"));
        valCol.setCellValueFactory(new PropertyValueFactory<>("value"));

        //set value column comparator
        valCol.setComparator(new StringDoubleComparator());

        //assign the main list to the table
        mainTable.setItems(InvManager.getList());
    }

    //add button is clicked
    @FXML
    protected void onAddClick() throws IOException {
        //check if another window is already open, if so, end method with error
        if(AppManager.popUpIsOpen()){
            //set error label
            errorLabel.setText(POPUPOPEN);

            //exit method
            return;
        }

        //reset errorLabel
        setError("");

        //create a new stage with the NewItem fxml scene
        AppManager.popUp(5, "Add a New Item");
    }

    //edit button is clicked
    @FXML
    protected void onEditClick() throws IOException {
        //check if another window is already open, if so, end method with error
        if(AppManager.popUpIsOpen()){
            //set error label
            errorLabel.setText(POPUPOPEN);

            //exit method
            return;
        }

        //reset errorLabel
        setError("");

        //grab the currently selected item from the table view
        Item item = mainTable.getSelectionModel().getSelectedItem();

        //if item is null, set error label and exit method
        if(item == null){
            setError("Error: No item selected to edit");
            return;
        }

        //set the current item in AppManager (used by EditController)
        AppUtilSettings.setCurrentItem(item);

        //create a new stage with the EditMenu fxml scene
        AppManager.popUp(0, "Edit an Item");
    }

    //remove button is clicked
    @FXML
    protected void onRemoveClick() throws IOException {
        //check if another window is already open, if so, end method with error
        if(AppManager.popUpIsOpen()){
            //set error label
            errorLabel.setText(POPUPOPEN);

            //exit method
            return;
        }

        //reset errorLabel
        setError("");

        //set confirm to 2 for remove item (used by ConfirmController)
        AppUtilSettings.setConfirm(2);

        //grab the current item from the table view
        Item item = mainTable.getSelectionModel().getSelectedItem();

        //if item is null, set error label and exit method
        if(item == null){
            setError("Error: No item selected for removal");
            return;
        }

        //set the current item in AppManager (used by ConfirmController)
        AppUtilSettings.setCurrentItem(item);

        //open the confirmation popUp window
        AppManager.popUp(1, "Confirm Selection");
    }

    //clear all button is clicked
    @FXML
    protected void onClearClick() throws IOException {
        //check if another window is already open, if so, end method with error
        if(AppManager.popUpIsOpen()){
            //set error label
            errorLabel.setText(POPUPOPEN);

            //exit method
            return;
        }

        //set confirm to 1 for clear list (used by ConfirmController)
        AppUtilSettings.setConfirm(1);

        //open the confirmation popUp window
        AppManager.popUp(1, "Confirm Selection");

        //reset errorLabel
        setError("");
    }

    //refresh button is clicked
    @FXML
    protected void onRefreshClick(){
        //reset errorLabel
        setError("");

        //update item count
        itemCount.setText("Total Items: " + InvManager.getList().size());

        //clear the search bar text
        searchBar.clear();

        //reset search dropdown
        searchDropdown.setText("Search by...");
        AppUtilSettings.setSearchMode(0);
    }

    //hovering refresh button
    @FXML
    protected void hoverRefresh(){
        //add blue glow effect to refresh button
        refreshButton.setStyle("-fx-effect: dropshadow( one-pass-box , aqua , 3 , 0.0 , 0.0 , 0.0 )");
    }

    //mouse leaves refresh button
    @FXML
    protected void leaveRefresh(){
        //remove glow effect
        refreshButton.setStyle("-fx-effect: null");
    }

    //when serial is selected from search dropdown
    @FXML
    protected void serialSearchSelect(){
        //set search mode integer to 2
        AppUtilSettings.setSearchMode(2);

        //change the text on the search bar to "Serial Number"
        searchDropdown.setText("Serial Number");

        //close dropdown
        searchDropdown.hide();

        //call searchKeyTyped method to update the list if search bar was already typed in
        searchKeyTyped();

        //reset errorLabel
        setError("");
    }

    //when name is selected from search dropdown
    @FXML
    protected void nameSearchSelect(){
        //set search mode integer to 1
        AppUtilSettings.setSearchMode(1);

        //change the text on the search bar to "Name"
        searchDropdown.setText("Name");

        //close dropdown
        searchDropdown.hide();

        //call searchKeyTyped method to update the list if search bar was already typed in
        searchKeyTyped();

        //reset errorLabel
        setError("");
    }

    //when search bar is typed in
    @FXML
    protected void searchKeyTyped(){
        //if the search bar is empty, exit the method
        if(searchBar.getText().isEmpty()){
            return;
        }

        //if statement to determine which search function to call
        if(AppUtilSettings.getSearchMode() == 1){
            //call searchByName method in InvManager
            InvManager.sortByName(searchBar.getText());
        } else if(AppUtilSettings.getSearchMode() == 2){
            //call searchBySerial method in InvManager
            InvManager.sortBySerial(searchBar.getText());
        }
    }

    //load option is clicked
    @FXML
    protected void selectLoad(){
        //AppManager class object
        AppUtilSettings as = new AppUtilSettings();

        //check if another window is already open, if so, end method with error
        if(AppManager.popUpIsOpen()){
            //set error label
            errorLabel.setText(POPUPOPEN);

            //exit method
            return;
        }

        //FileLoader class object
        FileLoader fl = new FileLoader();

        //create a new stage
        Stage files = new Stage();

        //create a fileChooser object and a File object to store the chosen file
        FileChooser loadFile = new FileChooser();

        //set starting directory
        File startDir = new File("data");
        loadFile.setInitialDirectory(startDir);

        //launch fileChooser
        loadFile.setTitle("Load Previous Inventory From File");
        File newFile = loadFile.showOpenDialog(files);

        //if newFile is null, exit the method as the filechooser has been closed
        if(newFile == null){return;}

        //clear the main list before loading in a new one
        InvManager.clearList();

        //grab the extension to a string
        String baseExt = newFile.toString();
        String ext = baseExt.substring(baseExt.lastIndexOf(".") + 1);

        //call to invFromFile to read the file to the main list, set boolean to catch an error if it is false
        boolean errorCatch = fl.invFromFile(ext, newFile);

        //set error label and exit method if an error is caught
        if(errorCatch){
            errorLabel.setText("Error: File is not readable");
            return;
        }

        //call serialLoadTest to test if any duplicate serial numbers are in the list
        if(!as.serialLoadTest()){
            //if a match is found, clear the list and set the error label, exit method
            errorLabel.setText("Error: Duplicate items were found, unable to load");
            InvManager.clearList();
            return;
        }

        //if the whole method has been successful so far, change the title of the main menu to reflect the current file
        AppManager.setMainStageTitle("Current Editing Inventory From: " + baseExt.substring(baseExt.lastIndexOf("\\") + 1));

        //reset errorLabel and update item count
        setError("");
        updateItemCount();
    }

    //save option is clicked
    @FXML
    protected void selectSave() {
        //check if the list is empty
        if (InvManager.getList().isEmpty()) {
            //set error label
            errorLabel.setText("Error: No list to save");

            //exit method
            return;
        }

        //check if another window is already open, if so, end method with error
        if (AppManager.popUpIsOpen()) {
            //set error label
            errorLabel.setText(POPUPOPEN);

            //exit method
            return;
        }

        //create a new stage to host a fileChooser
        Stage files = new Stage();

        //create a fileChooser
        FileChooser saveFile = new FileChooser();
        saveFile.setTitle("Save Inventory to File");

        //set the filechooser to default to json
        saveFile.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Json File", "*.json"));
        saveFile.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TSV File", "*.txt"));
        saveFile.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("HTML File", "*.html"));

        //set the default filechooser starting directory
        File startDir = new File("data");
        saveFile.setInitialDirectory(startDir);

        //open the stage and create a file object where the user wants
        File newFile = saveFile.showSaveDialog(files);

        //if newFile is null, exit the method as the filechooser has been closed
        if (newFile == null) {
            return;
        }

        //grab the file's extension and store it in a string "ext" to determine which method to call
        String ext = newFile.toString();
        ext = ext.substring(ext.lastIndexOf(".") + 1);

        //call the invToFile method with the parameters ext and saveFile
        try {
            FileSaver.invToFile(ext, newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //reset errorLabel
        setError("");
    }

    //method to report an error from another controller using ints
    public void setError(String error){
        //set the error label to the input string
        errorLabel.setText(error);
    }

    //method to adjust the item count
    public void updateItemCount(){
        itemCount.setText("Total Items: " + InvManager.getList().size());
    }
}

//class to define a comparator specifically for the value column
class StringDoubleComparator implements Comparator<String>{
    //comparator method
    public int compare(String o1, String o2){
        //remove $ from strings, swap strings place b/c it was sorting backwards for some reason
        String no2 = o1.substring(1);
        String no1 = o2.substring(1);

        //turn the strings into doubles
        Double o1d = Double.parseDouble(no1);
        Double o2d = Double.parseDouble(no2);

        //compare the two new doubles and return the correct one
        return o1d.compareTo(o2d);
    }
}