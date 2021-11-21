/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Matthew Spiker
 */

package inv.inventorymanagementapplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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
    public static void invToFile(String extension, File saveFile) throws IOException {
        //FileSaver class object
        FileSaver fs = new FileSaver();

        //switch statement for each extension case
        switch (extension) {
            case "json" ->
                    //call the savetojson method
                    fs.saveToJson(saveFile);
            case "txt" ->
                    //call the savetotsv method
                    fs.saveToTsv(saveFile);
            case "html" ->
                    //call the savetohtml method
                    fs.saveToHtml(saveFile);
            default ->
                    //default to json if file extension is not recognized
                    fs.saveToJson(saveFile);
        }
    }

    //method to save json files
    public void saveToJson(File saveFile) throws IOException {
        //create gson object
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        //create a file writer for the save file
        FileWriter write = new FileWriter(saveFile);

        //write the main observable list of items to the file
        gson.toJson(InvManager.getList(), write);

        //flush and close the file writer
        write.flush();
        write.close();
    }

    //method to save TSV files
    public void saveToTsv(File saveFile) throws IOException {
        //create a FileWriter
        try (FileWriter writer = new FileWriter(saveFile)) {

            //write the first line out (Serial Number\tName\tValue)
            writer.write("Serial Number\tName\tValue");

            //for loop to loop through each item
            for (int i = 0; i < InvManager.getList().size(); i++) {
                writer.write("\n" + InvManager.getList().get(i).getSerial() + "\t"
                        + InvManager.getList().get(i).getName() + "\t" + InvManager.getList().get(i).getValue());
            }

            //flush and close the writer
            writer.flush();
        }
    }

    //method to save HTML files
    public void saveToHtml(File saveFile) throws IOException {

    }
}

//class to handle loading inventories from files
class FileLoader{
    public boolean invFromFile(String extension, File loadFile){
        //FileLoader class object
        FileLoader fl = new FileLoader();

        try {
            //switch statement for each extension case
            switch (extension) {
                case "json" ->
                        //call the savetojson method
                        fl.loadFromJson(loadFile);
                case "txt" ->
                        //call the savetotsv method
                        fl.loadFromTsv(loadFile);
                case "html" ->
                        //call the savetohtml method
                        fl.loadFromHtml(loadFile);
                default ->
                        //default to json if file extension is not recognized
                        fl.loadFromJson(loadFile);
            }
        } catch (IOException e){
            //return true if the file gives an error when opening
            return true;
        }

        //return false, signifying that no errors occured
        return false;
    }

    //method to load from json
    public void loadFromJson(File loadFile) throws IOException {
        //create gson object
        Gson gson = new Gson();

        //create a reader object to read in the json file
        Reader gsonReader = Files.newBufferedReader(Paths.get(loadFile.toString()));

        //read the gson to the main list through a for loop
        InvManager.setList(gson.fromJson(gsonReader, Item[].class));
    }

    //method to load from TSV
    public void loadFromTsv(File loadFile){
        //create buffered reader
        try(BufferedReader reader = new BufferedReader(new FileReader(loadFile))){
            //create a string to store Strings from each line
            String line;

            //if statement to ensure the file format is correct from line 1
            if(reader.readLine().equals("Serial Number\tName\tValue")) {
                //while loop to grab items from lines as long as the line is not empty
                while ((line = reader.readLine()) != null) {
                    //write each item separated by tab characters to a String in an array
                    String[] lineItems = line.split("\t");

                    //add the three parts of the array in order (sn, name, price) to a new item
                    Item newItem = new Item();
                    newItem.setSerial(lineItems[0]);
                    newItem.setName(lineItems[1]);
                    newItem.setValue(lineItems[2]);

                    //add newItem to the main list
                    InvManager.addItem(newItem);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method to load from HTML
    public void loadFromHtml(File loadFile){

    }
}