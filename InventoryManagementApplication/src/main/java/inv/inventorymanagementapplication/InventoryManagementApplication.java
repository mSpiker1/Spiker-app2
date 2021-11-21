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
        //create a FileWriter for saveFile
        try (FileWriter writer = new FileWriter(saveFile)) {
            //create a few constant strings to hold some repeated values
            final String THS = "<td>";
            final String TH = "</td>";

            //write the first few lines of the html document to ensure it is valid
            writer.write("<html>\n");
            writer.write("<head>\n");
            writer.write("<title>Inventory of "
                    + saveFile.toString().substring(saveFile.toString().lastIndexOf("\\") + 1) + "</title>\n");
            writer.write("</head>\n");
            writer.write("<body>\n");

            //open the table and write the table heading
            writer.write("<table>\n");
            writer.write("<thead>\n");
            writer.write("<tr>\n");
            writer.write("<th scope=\"col\">Serial Number</th>\n");
            writer.write("<th scope=\"col\">Value</th>\n");
            writer.write("<th scope=\"col\">Name</th>\n");
            writer.write("</tr>\n");
            writer.write("</thead>\n");
            writer.write("<tbody>\n");

            //write in each item from the list via a for loop
            for (int i = 0; i < InvManager.getList().size(); i++) {
                writer.write("<tr>\n");
                writer.write("<th scope=\"row\">" + InvManager.getList().get(i).getSerial() + "</th>\n");
                writer.write(THS + InvManager.getList().get(i).getValue() + TH + "\n");
                writer.write(THS + InvManager.getList().get(i).getName() + TH + "\n");
                writer.write("</tr>\n");
            }

            //close off the table and html document
            writer.write("</tbody>\n");
            writer.write("</table>\n");
            writer.write("</body>\n");
            writer.write("</html>\n");

            //flush filewriter
            writer.flush();
        }
    }
}

//class to handle loading inventories from files
class FileLoader{
    public boolean invFromFile(String extension, File loadFile){
        //FileLoader class object
        FileLoader fl = new FileLoader();

        //boolean to store in case html file messes up loading
        boolean htmlCatch = true;

        //sonarlint doesnt like assigning htmlcatch within the switch statement as it makes the code hard to read,
        //but I'm adding a comment here specifically to explain that because this is an exception in which I need
        //this variable to be assigned in order for the html parser to report issues if the file is not in the
        //correct format.
        try {
            //switch statement for each extension case
            switch (extension) {
                case "json" ->
                        //call the loadFromJson method
                        fl.loadFromJson(loadFile);
                case "txt" ->
                        //call the loadFromTsv method
                        fl.loadFromTsv(loadFile);
                case "html" ->
                        //call the loadFromHtml method
                        htmlCatch = fl.loadFromHtml(loadFile);
                default ->
                        //default to json if file extension is not recognized
                        fl.loadFromJson(loadFile);
            }
        } catch (IOException e){
            //return true if the file gives an error when opening
            return true;
        }

        //return true if htmlCatch is true, as file was not loaded correctly
        return htmlCatch;

        //return false, signifying that no errors occured
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
    public boolean loadFromHtml(File loadFile) throws IOException {
        //create buffered reader for loadFile
        try (BufferedReader reader = new BufferedReader(new FileReader(loadFile))) {
            //skip the next 13 lines
            //sonarlint doesn't like skipping lines with buffered readers, but there's a reason
            for(int i=0; i<14; i++){
                reader.readLine();
            }

            //read the next 5 lines on loop to assign each set of strings to an item, looping to fill the main list
            while (!reader.readLine().equals("</tbody>")) {
                //read three lines to a string
                String serial = reader.readLine();
                String value = reader.readLine();
                String name = reader.readLine();

                //if statement to catch wrong strings
                if(serial.length() < 16){
                    return true;
                }

                //parse the strings to only be the values that should be shown in the tableview
                serial = serial.substring(16, serial.length() - 5);
                value = value.substring(4, value.length() - 5);
                name = name.substring(4, name.length() - 5);

                //assign the strings to an item
                Item newItem = new Item();
                newItem.setSerial(serial);
                newItem.setValue(value);
                newItem.setName(name);

                //add the item to the list
                InvManager.addItem(newItem);

                //skip one line
                reader.readLine();
            }
        }
        //return false as long as method executes without issue
        return false;
    }
}