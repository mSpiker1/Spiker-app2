/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Matthew Spiker
 */

package inv.inventorymanagementapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class InventoryManagementApplicationTest{
    //These tests will go through every non-javafx method in the program, aside from getters and setters.
    //each test's name is indicative of the method within the application that it is testing
    //e.g. verifyPriceTest will test the verifyPrice method (within AppUtilSettings)
    //note that verification methods are supposed to return true if a problem is found. just noticed it seems
    //a little weird while writing the tests

    @Test
    void verifyPriceTest(){
        //initialize string variable with proper price value to test
        String price = "19.99";

        //initialize string variable with improper price value
        String notPrice = "not a number";

        //make assertions that calling the verifyPrice method will return false for price and true for notPrice
        assertFalse(AppUtilSettings.verifyPrice(price));
        assertTrue(AppUtilSettings.verifyPrice(notPrice));
    }

    @Test
    void verifyNameTest(){
        //initialize three strings, one that is too short, one too long, and one within specifications for a name
        String tooLong = "PNC45OZus6XpQAb5Bl4IVdcAICxcFMpPXwhaRk9QTWhAkTBSkCtyWWYa3x6kVSPowqmsydt4Y5CiAjZYF2YB2PiLx9rk" +
                "fXFQgbaUE03iUpL4G0ONn4UbJiSdkVjW9Q5EiI4R3fNtwxMNEh0SjTuWI5OEAdaY6AMzc9ApRCWnrSrAgUc3CXthLF5qm47yCAStC" +
                "f7qBkdN4lnFymUvTssOPK7F4q5gT5y8TSY6vMCNWTeizNwMhrtuXuIkng9qYaJjw";
        String tooShort = "a";
        String justRight = "Name";

        //make assertions that verifyName will return true for tooLong and tooShort, and false for justRight
        assertTrue(AppUtilSettings.verifyName(tooLong));
        assertTrue(AppUtilSettings.verifyName(tooShort));
        assertFalse(AppUtilSettings.verifyName(justRight));
    }

    @Test
    void verifySerialTest(){
        //initialize four strings, one with a number for the first character, one that is not alphanumeric,
        //one that is not the right length for a serial number, and one that is a proper serial number
        String numSerial = "1-ASD-ASD-ASD";
        String badFormat = "A-;'/-*&@-)(#";
        String tooShort = "A-AXY-POL-TY";
        String serial = "A-1XY-SS4-98G";

        //assert that numSerial, badFormat, and tooShort will return true from verifySerial, while serial returns false
        assertTrue(AppUtilSettings.verifySerial(numSerial));
        assertTrue(AppUtilSettings.verifySerial(badFormat));
        assertTrue(AppUtilSettings.verifySerial(tooShort));
        assertFalse(AppUtilSettings.verifySerial(serial));
    }

    @Test
    void serialMatchTest(){
        //AppUtilSettings class object
        AppUtilSettings as = new AppUtilSettings();

        //first, add six items to the main list to check the serial number against
        for(int i=0; i<=5; i++){
            //create new item
            Item newItem = new Item();

            //assign item values using i so each on is different
            newItem.setName("Item #" + i);
            newItem.setSerial("A-" + i + "AA-AAA-AAA");
            newItem.setValue(i + ".99");

            //add the item to the list
            InvManager.addItem(newItem);
        }

        //make two strings, one with a serial number that should match one of the items, and one that is unique
        String match = "A-1AA-AAA-AAA";
        String unique = "Z-130-290-P60";

        //assert that match will return true and unique will return false when serialMatch is called
        assertTrue(as.serialMatch(match));
        assertFalse(as.serialMatch(unique));

        //clear the list before moving to next test
        InvManager.clearList();
    }

    @Test
    void serialLoadTestTest(){
        //AppUtilSettings class object
        AppUtilSettings as = new AppUtilSettings();

        //first, add six items to the main list to check it
        for(int i=0; i<=5; i++){
            //create new item
            Item newItem = new Item();

            //assign item values using i so each on is different
            newItem.setName("Item #" + i);
            newItem.setSerial("A-" + i + "AA-AAA-AAA");
            newItem.setValue(i + ".99");

            //add the item to the list
            InvManager.addItem(newItem);
        }

        //assert that serialLoadTest will return true when no serial numbers match
        assertTrue(as.serialLoadTest());

        //clear the list
        InvManager.clearList();

        //add six items with identical serial numbers to the list
        for(int i=0; i<=5; i++){
            //create new item
            Item newItem = new Item();

            //assign item values using i so each on is different
            newItem.setName("Item #" + i);
            newItem.setSerial("A-AAA-AAA-AAA");
            newItem.setValue(i + ".99");

            //add the item to the list
            InvManager.addItem(newItem);
        }

        //assert that serialLoadTest will return false when serial numbers are found that match
        assertFalse(as.serialLoadTest());

        //clear the list
        InvManager.clearList();
    }

    //this one tests all the methods in the fileSaver class at once, because they are all fairly similar
    @Test
    void fileSaverTest() throws IOException {
        //first, add six items to the main list to check it
        for(int i=0; i<=5; i++){
            //create new item
            Item newItem = new Item();

            //assign item values using i so each on is different
            newItem.setName("Item #" + i);
            newItem.setSerial("A-" + i + "AA-AAA-AAA");
            newItem.setValue(i + ".99");

            //add the item to the list
            InvManager.addItem(newItem);
        }

        //create four file objects, one to make a test file directory, and three to make a test file for each extension
        File dir = new File("test");
        File json = new File("test/test.json");
        File tsv = new File("test/test.txt");
        File html = new File("test/test.html");

        //create the test directory
        dir.mkdir();

        //write the list to all files using FileSaver's invToFile method. extension string should just be
        //the extension of the file string after the last . character, so those are given
        FileSaver.invToFile("json", json);
        FileSaver.invToFile("txt", tsv);
        FileSaver.invToFile("html", html);

        //test that expected output is on a certain line in each file
        assertEquals("    \"serial\": \"A-0AA-AAA-AAA\",",
                Files.readAllLines(Paths.get("test/test.json")).get(3));
        assertEquals("A-1AA-AAA-AAA\tItem #1\t1.99",
                Files.readAllLines(Paths.get("test/test.txt")).get(2));
        assertEquals("<td>Item #2</td>",
                Files.readAllLines(Paths.get("test/test.html")).get(27));

        //clear the list
        InvManager.clearList();

        //delete test files, then test directory
        json.delete();
        tsv.delete();
        html.delete();
        dir.delete();
    }

    //this one tests all the methods in the fileSaver class at once, because they are all fairly similar
    //the main method also just calls the right sub method to load the right file type. If these tests were done
    //individually, it would take forever and waste so much more room
    @Test
    void fileLoaderTest() throws IOException {
        //this is going to be a copy/paste of the FileSaver test to generate the test files, but
        //the items will also be saved to a second local observable list that will be used to test
        //the main list later, after loading each of the file types
        ObservableList<Item> compareList = FXCollections.observableArrayList();

        //first, add six items to the main list to check it
        for(int i=0; i<=5; i++){
            //create new item
            Item newItem = new Item();

            //assign item values using i so each on is different
            newItem.setName("Item #" + i);
            newItem.setSerial("A-" + i + "AA-AAA-AAA");
            newItem.setValue(i + ".99");

            //add the item to both the main list and the compare list
            InvManager.addItem(newItem);
            compareList.add(newItem);
        }

        //create four file objects, one to make a test file directory, and three to make a test file for each extension
        File dir = new File("test");
        File json = new File("test/test.json");
        File tsv = new File("test/test.txt");
        File html = new File("test/test.html");

        //create the test directory
        dir.mkdir();

        //write the list to all files using FileSaver's invToFile method. extension string should just be
        //the extension of the file string after the last . character, so those are given
        FileSaver.invToFile("json", json);
        FileSaver.invToFile("txt", tsv);
        FileSaver.invToFile("html", html);

        //clear the list
        InvManager.clearList();

        //FileLoader class object
        FileLoader fl = new FileLoader();

        //load and test the json file first
        fl.invFromFile("json", json);

        //assert that the compare list and main list are equal after loading inventory from the json file
        //I just used an item name because I couldn't find an easy way to compare two observable lists. it works.
        assertEquals(compareList.get(2).getName(), InvManager.getList().get(2).getName());

        //clear the list
        InvManager.clearList();

        //load and test the json file first
        fl.invFromFile("txt", tsv);

        //assert that the compare list and main list are equal after loading inventory from the json file
        //I just used an item name because I couldn't find an easy way to compare two observable lists. it works.
        assertEquals(compareList.get(2).getName(), InvManager.getList().get(2).getName());

        //clear the list
        InvManager.clearList();

        //load and test the json file first
        fl.invFromFile("html", html);

        //assert that the compare list and main list are equal after loading inventory from the json file
        //I just used an item name because I couldn't find an easy way to compare two observable lists. it works.
        assertEquals(compareList.get(2).getName(), InvManager.getList().get(2).getName());

        //clear the list
        InvManager.clearList();

        //delete test files, then test directory
        json.delete();
        tsv.delete();
        html.delete();
        dir.delete();
    }

    @Test
    void setListTest(){
        //create an item array
        Item[] itemArr = new Item[5];

        //write five items to the array
        for(int i=0; i<5; i++){
            //create new item
            Item newItem = new Item();

            //assign item values using i so each on is different
            newItem.setName("Item #" + i);
            newItem.setSerial("A-" + i + "AA-AAA-AAA");
            newItem.setValue(i + ".99");

            //add the item to the item array
            itemArr[i] = newItem;
        }

        //assert that the size of the main list being 5 should return false
        assertNotEquals(5, InvManager.getList().size());

        //call setlist to add the five items from the item array to the main list
        InvManager.setList(itemArr);

        //assert that the size of the main list being 5 should return true
        assertEquals(5, InvManager.getList().size());

        //clear the list
        InvManager.clearList();
    }

    @Test
    void sortByNameTest(){
        //write five items to the main list
        for(int i=0; i<5; i++){
            //create new item
            Item newItem = new Item();

            //assign item values using i so each on is different
            newItem.setName("i = " + i);
            newItem.setSerial("A-" + i + "AA-AAA-AAA");
            newItem.setValue(i + ".99");

            //add the item to the main list
            InvManager.addItem(newItem);
        }

        //assert that the first item in the list's name is not "i = 3"
        assertNotEquals("i = 3", InvManager.getList().get(0).getName());

        //call the sort by name function with "i = 3" as the search parameter
        InvManager.sortByName("i = 3");

        //assert that the first item in the list's name is "i = 3"
        assertEquals("i = 3", InvManager.getList().get(0).getName());

        //clear the list
        InvManager.clearList();
    }

    @Test
    void sortBySerialTest(){
        //write five items to the main list
        for(int i=0; i<5; i++){
            //create new item
            Item newItem = new Item();

            //assign item values using i so each on is different
            newItem.setName("Item #" + i);
            newItem.setSerial("A-" + i + i + i + "-" + i + i + i + "-" + i + i + i);
            newItem.setValue(i + ".99");

            //add the item to the main list
            InvManager.addItem(newItem);
        }

        //assert that the first item in the list's name is not "A-333-333-333"
        assertNotEquals("A-333-333-333", InvManager.getList().get(0).getSerial());

        //call the sort by serial function with "A-333-333-333" as the search parameter
        InvManager.sortBySerial("A-333-333-333");

        //assert that the first item in the list's name is "i = 3"
        assertEquals("A-333-333-333", InvManager.getList().get(0).getSerial());

        //clear the list
        InvManager.clearList();
    }

    @Test
    void addItemTest(){
        //assert that the length of the main list is 0
        assertEquals(0, InvManager.getList().size());

        //write five items to the main list
        for(int i=0; i<5; i++){
            //create new item
            Item newItem = new Item();

            //assign item values using i so each on is different
            newItem.setName("Item #" + i);
            newItem.setSerial("A-" + i + i + i + "-" + i + i + i + "-" + i + i + i);
            newItem.setValue(i + ".99");

            //add the item to the main list
            InvManager.addItem(newItem);
        }

        //assert that the length of the main list is 5
        assertEquals(5, InvManager.getList().size());

        //clear the list
        InvManager.clearList();
    }

    @Test
    void editSerialTest(){
        //write five items to the main list
        for(int i=0; i<5; i++){
            //create new item
            Item newItem = new Item();

            //assign item values using i so each on is different
            newItem.setName("Item #" + i);
            newItem.setSerial("A-" + i + i + i + "-" + i + i + i + "-" + i + i + i);
            newItem.setValue(i + ".99");

            //add the item to the main list
            InvManager.addItem(newItem);
        }

        //create a string that is a valid serial number
        String newSerial = "A-111-222-333";

        //assert that the first item in the list's serial number is not newSerial
        assertNotEquals(newSerial, InvManager.getList().get(0).getSerial());

        //call the editSerial method to change the first item's serial number to newSerial
        InvManager.editSerial(0, newSerial);

        //assert that the first item in the list's serial number is newSerial
        assertEquals(newSerial, InvManager.getList().get(0).getSerial());

        //clear the list
        InvManager.clearList();
    }

    @Test
    void editNameTest(){
        //write five items to the main list
        for(int i=0; i<5; i++){
            //create new item
            Item newItem = new Item();

            //assign item values using i so each on is different
            newItem.setName("Item #" + i);
            newItem.setSerial("A-" + i + i + i + "-" + i + i + i + "-" + i + i + i);
            newItem.setValue(i + ".99");

            //add the item to the main list
            InvManager.addItem(newItem);
        }

        //create a string that is a valid name
        String newName = "This is a name";

        //assert that the first item in the list's name is not newName
        assertNotEquals(newName, InvManager.getList().get(0).getName());

        //call the editName method to change the first item's name to newName
        InvManager.editName(0, newName);

        //assert that the first item in the list's name is newName
        assertEquals(newName, InvManager.getList().get(0).getName());

        //clear the list
        InvManager.clearList();
    }

    @Test
    void editPriceTest(){
        //write five items to the main list
        for(int i=0; i<5; i++){
            //create new item
            Item newItem = new Item();

            //assign item values using i so each on is different
            newItem.setName("Item #" + i);
            newItem.setSerial("A-" + i + i + i + "-" + i + i + i + "-" + i + i + i);
            newItem.setValue(i + ".99");

            //add the item to the main list
            InvManager.addItem(newItem);
        }

        //create a string that is a valid name
        String newPrice = "2.67";

        //assert that the first item in the list's price is not newPrice
        assertNotEquals(newPrice, InvManager.getList().get(0).getValue());

        //call the editPrice method to change the first item's price to newPrice
        InvManager.editPrice(0, newPrice);

        //assert that the first item in the list's price is newPrice
        assertEquals(newPrice, InvManager.getList().get(0).getValue());

        //clear the list
        InvManager.clearList();
    }

    @Test
    void removeItemTest(){
        //write five items to the main list
        for(int i=0; i<5; i++){
            //create new item
            Item newItem = new Item();

            //assign item values using i so each on is different
            newItem.setName("Item #" + i);
            newItem.setSerial("A-" + i + i + i + "-" + i + i + i + "-" + i + i + i);
            newItem.setValue(i + ".99");

            //add the item to the main list
            InvManager.addItem(newItem);
        }

        //assert that main list's size is 5
        assertEquals(5, InvManager.getList().size());

        //call the removeItem method to remove item 0 from the list
        InvManager.removeItem(0);

        //assert that the main list's size is now 4
        assertEquals(4, InvManager.getList().size());

        //clear the list
        InvManager.clearList();
    }

    @Test
    void getItemPosTest(){
        //create a new item to store one item from the for loop in
        Item itemPos2 = new Item();

        //write five items to the main list
        for(int i=0; i<5; i++){
            //create new item
            Item newItem = new Item();

            //assign item values using i so each on is different
            newItem.setName("Item #" + i);
            newItem.setSerial("A-" + i + i + i + "-" + i + i + i + "-" + i + i + i);
            newItem.setValue(i + ".99");

            if(i == 2){
                itemPos2 = newItem;
            }

            //add the item to the main list
            InvManager.addItem(newItem);
        }

        //assert that itemPos2's position is equal to 2
        assertEquals(2, InvManager.getItemPos(itemPos2));

        //clear the list
        InvManager.clearList();
    }

    @Test
    void clearListTest(){
        //write five items to the main list
        for(int i=0; i<5; i++){
            //create new item
            Item newItem = new Item();

            //assign item values using i so each on is different
            newItem.setName("Item #" + i);
            newItem.setSerial("A-" + i + i + i + "-" + i + i + i + "-" + i + i + i);
            newItem.setValue(i + ".99");

            //add the item to the main list
            InvManager.addItem(newItem);
        }

        //assert that main list's size is 5
        assertEquals(5, InvManager.getList().size());

        //call the clearList method to clear all items from the list
        InvManager.clearList();

        //assert true that the main list is now empty
        assertTrue(InvManager.getList().isEmpty());
    }

    //this comparator should return the lower of the two numbers
    @Test
    void StringDoubleComparatorTest(){
        //StringDoubleComparator class object
        StringDoubleComparator sdc = new StringDoubleComparator();

        //create two strings
        String o1 = "$19.99";
        String o2 = "$999.99";

        //assert that the comparator method returns 1 when comparing the two strings
        assertEquals(1, sdc.compare(o1, o2));
    }
}