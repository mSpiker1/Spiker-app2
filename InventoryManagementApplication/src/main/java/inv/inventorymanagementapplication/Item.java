/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Matthew Spiker
 */

package inv.inventorymanagementapplication;

public class Item {
    //initialize item components: name, serial, value
    private String name;
    private String serial;
    private String value;

    //item value setters
    public void setName(String newName){
        //set the item's name to the input string
        this.name = newName;
    }

    public void setSerial(String newSerial){
        //set the item's serial to the input string
        this.serial = newSerial;
    }

    public void setValue(String newValue){
        //set the item's value to the input string
        this.value = newValue;
    }

    //item value getters
    public String getName(){return this.name;}

    public String getSerial(){return this.serial;}

    public String getValue(){return this.value;}
}
