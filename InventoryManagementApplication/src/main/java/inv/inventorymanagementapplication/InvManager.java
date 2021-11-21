package inv.inventorymanagementapplication;

import javafx.collections.ObservableList;

public class InvManager {
    //static observable list to hold all items and automatically update the tableview as it is changed

    //list getter
    public static ObservableList<Item> getList(){return null;}

    //list setter
    public static void setList(Item[] itemArray){
        //for loop through each item in the item array
            //add item i to the main list
    }

    //method to sort the list based on which items' names match a search string
    public static void sortByName(String search){
        //create a temporary observable list to store items that match the search criteria

        //create a temporary observable list to store items that don't match the search criteria

        //for loop to sort the main list's items between the two temp arrays
            //test if item i's name String contains the search string (ignoring case)
                //if it matches, add to match list
            //otherwise, add to other list

        //clear the main list so the two sorted lists can be re-added to it

        //first add matchList to the main list, then add tempList
    }

    //method to sort the list based on which items' serial numbers match a search string
    public static void sortBySerial(String search){
        //create a temporary observable list to store items that match the search criteria

        //create a temporary observable list to store items that don't match the search criteria

        //for loop to sort the main list's items between the two temp arrays
            //grab the current item's serial number, and remove the dashes from it for a string

            //test if newSerial or noDash contain the search string, to allow the user to search with or without dashes
                //if it matches, add to match list
            //otherwise, add to other list

        //clear the main list so the two sorted lists can be re-added to it

        //first add matchList to the main list, then add tempList
    }

    //method to add an item to the list
    public static void addItem(Item addItem){
        //add addItem to the main list
    }

    //method to edit serial number of a specific item
    public static void editSerial(int itemNum, String newSerial){
        //create an item equal to item #itemNum in the main list

        //replace newItem's serial number with the new one

        //replace the item at itemNum in the main list with newItem
    }

    //method to edit the name of a specific item
    public static void editName(int itemNum, String newName){
        //create an item equal to item #itemNum in the main list

        //replace newItem's name with the new one

        //replace the item at itemNum in the main list with newItem
    }

    //method to edit the price of a specific item
    public static void editPrice(int itemNum, String newPrice){
        //create an item equal to item #itemNum in the main list

        //replace newItem's price with the new one

        //replace the item at itemNum in the main list with newItem
    }

    //method to remove an item
    public static void removeItem(int itemNum){
        //remove item number itemNum from the list
    }

    //method to match a given item with an item in the list
    public static int getItemPos(Item matchItem){
        //return -1 if matchItem is null

        //loop through the list until a match is found
            //test if the serial numbers match, as there should be no duplicate serial numbers
                //return i if a match is found

        //return -1 if no item match is found
        return 0;
    }

    //method to clear the list
    public static void clearList() {
        //clear the main list
    }
}
