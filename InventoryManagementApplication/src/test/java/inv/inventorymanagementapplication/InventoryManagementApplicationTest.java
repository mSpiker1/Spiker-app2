/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Matthew Spiker
 */

package inv.inventorymanagementapplication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryManagementApplicationTest{
    //These tests will go through every non-javafx method in the program
    //each test's name is indicative of the method within the application that it is testing
    //e.g. verifyPriceTest will test the verifyPrice method (within AppManager)

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
}