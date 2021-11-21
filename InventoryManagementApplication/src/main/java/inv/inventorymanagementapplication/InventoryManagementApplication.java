/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Matthew Spiker
 */

package inv.inventorymanagementapplication;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.*;

public class InventoryManagementApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        //send the primary stage to AppManager to handle the stages and scenes
        AppManager.setMainStage(primaryStage);

        //load the main stage up with the correct scene
        AppManager.loadMainStage();
    }

    public static void main(String[] args) {
        launch();
    }
}

//class to handle saving inventories to files
class FileSaver{
    //method to call the correct save method
    public static void invToFile(String ext, File saveFile){
        //switch statement for each extension case
                    //call the saveToJson method for ext = .json

                    //call the saveToTsv method for ext = .txt

                    //call saveToHtml method for ext = .html

                    //default to json if file extension is not recognized
        }

    //method to save json files
    public void saveToJson(File saveFile){
        //create gson object

        //create a FileWriter for the save file

        //write the main observable list of items to the file

        //flush and close the file writer
    }

    //method to save TSV files
    public void saveToTsv(File saveFile){
        //create a FileWriter for the save file

        //write the first line out as "Serial Number\tName\tValue"

        //for loop to loop through each item, writing each of the three item parameters to the file separated by tabs

        //flush and close the writer
    }

    //method to save HTML files
    public void saveToHtml(File saveFile){
        //create a FileWriter for the save file

        //write the initial headings, etc. to html file until reaching the table where the list will be stored

        //for loop through the main list of items and save them to the file

        //finish writing the last of the html file

        //flush and close the writer
    }
}

//class to handle loading inventories from files
class FileLoader{
    public boolean invFromFile(String extension, File loadFile){
        //switch statement for each extension case, inside try/catch
            //call the saveToJson method for ext = .json

            //call the saveToTsv method for ext = .txt

            //call saveToHtml method for ext = .html

            //default to json if file extension is not recognized
        //if an error is caught, return true to indicate an error to the menu controller

        //otherwise return false, signifying that no errors occured
        return false;
    }

    //method to load from json
    public void loadFromJson(File loadFile){
        //create gson object

        //create a reader object to read in the json file

        //read the gson to the main list through a for loop
    }

    //method to load from TSV
    public void loadFromTsv(File loadFile){
        //create buffered reader

        //create a string to store Strings from each line

        //if statement to test that the first line is "Serial Number\tName\tValue"
            //while loop to grab items from lines as long as the line is not empty
                    //write each item separated by tab characters to a String in an array

                    //add the three parts of the array in order (sn, name, price) to a new item

                    //add newItem to the main list
    }

    //method to load from HTML
    public void loadFromHtml(File loadFile){
        //create a buffered reader

        //read and verify that each line leading up to the table where items are stored is correct

        //while loop to grab items from the lines as long as the line is not the end of the item table
            //write each item parameter to a String

            //create a new item from the strings

            //add newItem to the main list
    }
}