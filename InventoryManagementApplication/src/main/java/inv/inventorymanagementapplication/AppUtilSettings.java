package inv.inventorymanagementapplication;

import javafx.collections.ObservableList;

public class AppUtilSettings {

    //int to manage confirmation window, main menu sets and confirmation gets to determine what to confirm
    //0 for nothing, 1 for clear list, 2 for removing an item
    private static int confirm = 0;

    //int to manage search criteria, search dropdown sets and search bar gets in order to know what parameters to search
    //0 for nothing, 1 for nameMode, 2 for serialMode
    private static int searchMode = 0;

    //Item object to represent a selected item, used by remove and edit item buttons
    private static Item currentItem;

    ////////////////////////////////////////////
    ////         Utility Methods            ////
    ////////////////////////////////////////////

    //method to verify price as a number that is greater than or equal to 0
    public static boolean verifyPrice(String num) {
        //if the string is null, return true
        if (num == null) {
            return true;
        }

        //try/catch to return true if parsing the string as a double gives an error
        try {
            Double.parseDouble(num);
        } catch (NumberFormatException nfe) {
            return true;
        }

        //return true if numeric value is less than 0, false otherwise as all verification tests have passed
        return Double.parseDouble(num) < 0;
    }

    //method to verify the name string is between 2-256 characters
    public static boolean verifyName(String name){
        //verify that the length of the name is between 2 and 256 characters
        return name.length() < 2 || name.length() > 256;
    }

    //method to verify a serial number is the correct format
    public static boolean verifySerial(String serial){
        //remove dashes from serial string
        String serialTest = serial.replace("-", "");

        //verify the length of serial string is 10, it is alphanumeric, and the first character is not a number
        return serialTest.length() != 10 || !serialTest.matches("^[a-zA-Z0-9]*$") ||
                !verifyPrice(String.valueOf(serialTest.charAt(0)));
    }

    //see if a serial number matches an existing serial number
    public boolean serialMatch(String serial){
        //run a for loop through every item in the main list, test if it's serial number matches the string
        for(int i=0; i<InvManager.getList().size(); i++){
            //if serial number of item i matches serial string, return true
            if(InvManager.getList().get(i).getSerial().equals(serial)){return true;}
        }

        //return false if the for loop completes without issue
        return false;
    }

    //test if any serial numbers match after loading a file
    public boolean serialLoadTest(){
        //initialize a new observable list and copy the main list to it
        ObservableList<Item> tempList = InvManager.getList();

        //use nested for loops to test if any item's serial number matches another
        for(int i=0; i<InvManager.getList().size(); i++){
            for(int j=0; j<tempList.size(); j++){
                if(InvManager.getList().get(i).getSerial().equals(tempList.get(j).getSerial()) && i != j){
                    //return false if two serial numbers match
                    return false;
                }
            }
        }
        //return true if the method completes without issue
        return true;
    }

    ////////////////////////////////////////////
    ////          SETTER METHODS            ////
    ////////////////////////////////////////////

    //confirm setter
    public static void setConfirm(int newConfirm){
        confirm = newConfirm;
    }

    //search setter
    public static void setSearchMode(int search){
        searchMode = search;
    }

    //currentItem setter
    public static void setCurrentItem(Item newItem){
        currentItem = newItem;
    }

    ////////////////////////////////////////////
    ////          GETTER METHODS            ////
    ////////////////////////////////////////////

    //confirm getter
    public static int getConfirm(){
        return confirm;
    }

    //search getter
    public static int getSearchMode(){
        return searchMode;
    }

    //currentItem getter
    public static Item getCurrentItem(){return currentItem;}
}
