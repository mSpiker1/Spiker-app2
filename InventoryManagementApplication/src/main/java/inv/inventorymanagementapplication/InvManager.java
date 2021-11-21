/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Matthew Spiker
 */

package inv.inventorymanagementapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InvManager {

    private InvManager() {
        throw new IllegalStateException("Inventory List Manager Class");
    }

    //create a static observablelist to hold all items and automatically update the tableview as it is changed
    private static final ObservableList<Item> mainList = FXCollections.observableArrayList();

    //list getter
    public static ObservableList<Item> getList(){return mainList;}

    //list setter
    public static void setList(Item[] itemArray){
        //for loop through each item, adding them to the main list
        for (Item item : itemArray) {
            InvManager.addItem(item);
        }
    }

    //method to sort the list based on which items' names match a search string
    public static void sortByName(String search){
        //create a temporary observable list to store items that match the search criteria
        ObservableList<Item> matchList = FXCollections.observableArrayList();

        //create a temporary observable list to store items that don't match the search criteria
        ObservableList<Item> tempList = FXCollections.observableArrayList();

        //for loop to sort the main list's items between the two temp arrays
        for (Item item : mainList) {
            //test if item i's name String contains the search string (ignoring case)
            if (item.getName().toLowerCase().contains(search.toLowerCase())) {
                matchList.add(item);
            } else tempList.add(item);
        }

        //clear the main list so the two sorted lists can be re-added to it
        mainList.clear();

        //first add matchList to the main list, then add tempList
        mainList.addAll(matchList);
        mainList.addAll(tempList);
    }

    //method to sort the list based on which items' serial numbers match a search string
    public static void sortBySerial(String search){
        //create a temporary observable list to store items that match the search criteria
        ObservableList<Item> matchList = FXCollections.observableArrayList();

        //create a temporary observable list to store items that don't match the search criteria
        ObservableList<Item> tempList = FXCollections.observableArrayList();

        //for loop to sort the main list's items between the two temp arrays
        for (Item item : mainList) {
            //grab the current item's serial number, and remove the dashes from it for a string
            String newSerial = item.getSerial().toLowerCase();
            String noDash = newSerial.replace("-", "");

            //test if newSerial or noDash contain the search string, to allow the user to search with or without dashes
            if (newSerial.contains(search.toLowerCase()) || noDash.contains(search.toLowerCase())) {
                matchList.add(item);
            } else tempList.add(item);
        }

        //clear the main list so the two sorted lists can be re-added to it
        mainList.clear();

        //first add matchList to the main list, then add tempList
        mainList.addAll(matchList);
        mainList.addAll(tempList);
    }

    //method to add an item to the list
    public static void addItem(Item addItem){
        //add addItem to the main list
        mainList.add(addItem);
    }

    //method to edit serial number of a specific item
    public static void editSerial(int itemNum, String newSerial){
        //create an item equal to item #itemNum in the main list
        Item newItem = mainList.get(itemNum);

        //replace newItem's serial number with the new one
        newItem.setSerial(newSerial);

        //replace the item at itemNum in the main list with newItem
        mainList.set(itemNum, newItem);
    }

    //method to edit the name of a specific item
    public static void editName(int itemNum, String newName){
        //create an item equal to item #itemNum in the main list
        Item newItem = mainList.get(itemNum);

        //replace newItem's name with the new one
        newItem.setName(newName);

        //replace the item at itemNum in the main list with newItem
        mainList.set(itemNum, newItem);
    }

    //method to edit the price of a specific item
    public static void editPrice(int itemNum, String newPrice){
        //create an item equal to item #itemNum in the main list
        Item newItem = mainList.get(itemNum);

        //replace newItem's price with the new one
        newItem.setValue(newPrice);

        //replace the item at itemNum in the main list with newItem
        mainList.set(itemNum, newItem);
    }

    //method to remove an item
    public static void removeItem(int itemNum){
        //remove item number itemNum from the list
        mainList.remove(itemNum);
    }

    //method to match a given item with an item in the list
    public static int getItemPos(Item matchItem){
        //return -1 if matchItem is null
        if(matchItem == null){
            return -1;
        }

        //loop through the list until a match is found
        for(int i=0; i<mainList.size(); i++){
            //test if the serial numbers match, as there should be no duplicate serial numbers
            if(mainList.get(i).getSerial().equals(matchItem.getSerial())){
                return i;
            }
        }

        //return -1 if no item match is found
        return -1;
    }

    //method to clear the list
    public static void clearList() {
        //clear the main list
        mainList.clear();
    }
}
