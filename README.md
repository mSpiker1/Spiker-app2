# Spiker-app2

# Application Usage

The InventoryManagementApplication I created is meant to manage and keep track of inventories of potentially
thousands of items. Each item in an inventory has three parameters: Name, serial number, and value (price).
The application should be intuitive and difficult to break as many error catches have been implemented while
building the application, and while I'm sure it's far from perfect, it is most certainly functional on a
more than acceptable level. The gui is built to be simple but descriptive so as to be user friendly, making
managing an inventory a smooth experience.4:14 AM 11/21/2021

# Application functionality Guide
Guide format:

## Menu name

+ Function Name (e.g. Add New Item Button) -
Function description

-------------------------------------------------------------------------------------------------------------
## Main Menu, "Inventory Manager"

+ File Menu -
Dropdown menu that gives you the option to load a previous inventory from a file, or save the current inventory 
to a file

+ Load -
Allows you to load a previously created inventory of items from your choice of a Json (.json), Tsv (.txt), or
HTML (.html) file. The file you choose to load must be compatible and of the correct format; as such, the
file will need to have been created by this program.

+ Save -
Allows you to save the currently displayed inventory to a new file. The file can be from your choise of a Json
(.json), TSV (.txt), or HTML (.html) file, and will save all items in the currently displayed list managed by 
the application.

+ Refresh (top right) -
Clicking this will fix the program any time anything that should update has not updated yet. It can be used to
update the current item count if it is not displaying correctly, remove errors from the screen, and update the
displayed list if it has failed to update on its own.

+ Search Bar -
Use this bar to type in a search phrase. The dropdown menu next to it allows you to choose whether you want to
search for either a specific name, or a specific serial number.

+ Search by... -
Dropdown menu next to the search bar that allows you to choose if you want to search by name, or by serial
number.

+ Search by name -
Allows you to use the search bar to search for items by their name. Items matching your search term will be
brought to the top of the displayed list.

+ Search by serial number -
Allows you to use the search bar to search for items by serial numbers. Items matching you search term will be
brought to the top of the list.

+ Name Column -
Clicking the top of this column will sort the inventory in ascending (one click) or descending (two clicks)
order, alphabetically (with numeric names above alphabetical names).

+ Serial Number Column - 
Clicking the top of this column will sort the inventory in ascending (one click) or descending (two clicks)
order, alphabetically (with numeric serial numbers above alphabetical ones).

+ Value Column -
Clicking the top of this column will sort the inventory in ascending (one click) or descending (two clicks)
order, numerically.

+ Remove Item Button -
Clicking this button while an item in the inventory is selected will remove that item from the inventory. A 
confirmation window will pop up before removing the item to ensure you do not remove an item by mistake.

+ Clear All Items Button -
Click this button will remove all items from the current inventory. A confirmation window will pop up before
clearing the inventory to ensure you do not mistakenly delete your entire inventory.

+ Add New Item Button -
Clicking this button will launch a pop up window in which you can fill out a form for a new item to be added
to the current inventory.

+ Edit Item Button -
Clicking this button while an item in the inventory is selected will launch a pop up window where you can
select a property of that item to change (name, serial number, price).

## Confirmation Window

+ Confirm Button -
This will confirm whatever action brought this window up (e.g. if you clicked on "Remove an Item" and this
window popped up, the item would not be removed unless you clicked the Confirm Button to verify).

+ Cancel Button -
This will cancel whatever action brought this window up (e.g. if you clicked on "Remove an Item" and this
window popped up, you could click the Cancel Button to stop the removal process and keep the item.

## Edit Menu

+ Name Button -
Clicking this button will bring up a menu in which you can change the name of the item you selected to edit.

+ Serial Number Button -
Clicking this button will bring up a menu in which you can change the serial number of the item you selected
to edit.

+ Price Button -
Clicking this button will bring up a menu in which you can change the price value of the item you selected
to edit.

## Name Edit Menu

+ Text Box -
This text box should start with the current name of the item you chose to edit in it. You can change the name
of the item to whatever you wish, so long as it is between 2 and 256 characters.

+ < Back Button -
Clicking this will take you back to the main edit menu, where you can choose a different item property to edit.

+ Save Button -
This will save the changes you have made to the item you selected to edit.

## Price Edit Menu

+ Text Box -
This text box should start with the current price of the item you chose to edit in it. You can change the price
of the item to whatever you wish, so long as it is a number greater than zero.

+ < Back Button -
Clicking this will take you back to the main edit menu, where you can choose a different item property to edit.

+ Save Button -
This will save the changes you have made to the item you selected to edit.

## Serial Number Edit Menu

+ Text Boxes -
These text boxes contain the four parts of a serial number: the first letter, then three sets of three different
alphanumeric characters. The text boxes should already have the current serial number of the item you chose to 
edit in them. You can edit them to whatever you wish, so long as the first character is a letter from the
alphabet, and the other 9 characters are either letters or numbers.

+ < Back Button -
Clicking this will take you back to the main edit menu, where you can choose a different item property to edit.

+ Save Button -
This will save the changes you have made to the item you selected to edit.

## New Item Creation Menu

+ Name Text Box -
This is where you can type the name of the new item you are adding to the inventory. It can be anything you want,
so long as it is between 2 and 25 characters in length

+ Serial Number Text Boxes -
These text boxes are where you can type the serial number of the item you are adding to the inventory. The first
character must be a letter of the alphabet, and the other 9 characters must be alphanumeric.

+ Price Text Box -
This is where you can type the value (price) of the new item you are adding to the inventory. It must be a number,
and it must be greater than zero.

+ Save New Item Button -
Clicking this button will create a new item with your specified parameters (so long as they have been correctly 
filled out) and add it to the current inventory. This will also clear the text boxes, but the window will stay open,
so you can continue adding more items to the inventory if you choose.