package kitchen.inventory.app;


import javax.swing.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    List<UnitOfVolume> unitsOfVolume = new ArrayList<>();
    Scanner userInput = new Scanner(System.in);
    List<InventoryItem> inventoryItemList = new ArrayList<>();
    UnitOfVolume quart = new UnitOfVolume("Quart");
    UnitOfVolume pint = new UnitOfVolume("Pint");
    UnitOfVolume cup = new UnitOfVolume("Cup");

    public static void main(String[] args) {
        Application inventoryApp = new Application();
        inventoryApp.run();
    }

    private void run(){
        //Create Units of Volume
        //Add them to unitsOfVolume ArrayList
        createUnitsOfVolumeAddToList();

        //Create and Display Menu For User Input
        //Collect User Input and Do Something With It
        createMenuAndScannerForUserInput();

    }

    public void createMenuAndScannerForUserInput(){
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<------------- Select An Option From The Menu Below --------------->");
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<--Input The Number of Your Selection and Press Enter to Continue-->");
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<------ 1 ------ Add new Inventory Item --------------------------->");
        System.out.println("<------ 2 ------ Delete Inventory Item ---------------------------->");
        System.out.println("<------ 3 ------ Show Current Inventory Items --------------------->");
        System.out.println("<------ 4 ------ Update Items ------------------------------------->");
        System.out.println("<------------------------------------------------------------------>");
        String optionSelected = userInput.nextLine();
        switch(optionSelected){
            case "1":
                addNewInventoryItemMenu();
                break;
            case "2":
                deleteInventoryItem();
                break;
            case "3":
                currentInventoryItemListHeader();
                displayListOfInventoryItems();
                returnToMainMenuPrompt();
                break;
            case "4":
                currentInventoryItemListHeader();
                displayListOfInventoryItems();
                updateInventoryItemAmount();
                break;
            default:
                System.out.println("Invalid Selection");
        }

    }

    public void createUnitsOfVolumeAddToList(){
        unitsOfVolume.add(quart);
        unitsOfVolume.add(pint);
        unitsOfVolume.add(cup);
    }

    public void addNewInventoryItemMenu(){
        UnitOfVolume setUnit;
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<----------- Type The Name Of The New Inventory Item -------------->");
        System.out.println("<------------------------------------------------------------------>");
        String nameOfItem = userInput.nextLine();
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<------------------ Enter The Unit of Volume ---------------------->");
        System.out.println("<--------Quart--------------Pint-----------------Cup--------------->");
        System.out.println("<------------------------------------------------------------------>");
        String unitOfVolume = userInput.nextLine();
        if(unitOfVolume.equalsIgnoreCase(quart.getType())){
            setUnit = quart;
        } else if(unitOfVolume.equalsIgnoreCase(pint.getType())){
            setUnit = pint;
        } else
            setUnit = cup;
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<------------------ Enter The Number Of Units --------------------->");
        System.out.println("<------------------------------------------------------------------>");
        double numberOfUnits = Double.parseDouble(userInput.nextLine());
        inventoryItemList.add(new InventoryItem(nameOfItem, numberOfUnits, setUnit));
        displayListOfInventoryItems();
        returnToMainMenuPrompt();
    }

    public void deleteInventoryItem(){
        //int itemToBeDeleted = -1;
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<---------- Type The Name Of Inventory Item To Delete ------------->");
        System.out.println("<------------------------------------------------------------------>");
        displayListOfInventoryItems();
        String itemToDelete = userInput.nextLine();
        inventoryItemList.remove(searchInventoryItemList(itemToDelete));
        displayListOfInventoryItems();
        returnToMainMenuPrompt();
    }

    public void updateInventoryItemAmount(){
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<---------- Type The Name Of Inventory Item To Update ------------->");
        System.out.println("<------------------------------------------------------------------>");
        String itemToUpdate = userInput.nextLine();
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<--------------- What Would You Like To Do? ----------------------->");
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<------ 1 ------ Change Name -------------------------------------->");
        System.out.println("<------ 2 ------ Change Unit of Volume ---------------------------->");
        System.out.println("<------ 3 ------ Update Number of Units --------------------------->");
        System.out.println("<------ 4 ------ Return to Main Menu ------------------------------>");
        System.out.println("<------------------------------------------------------------------>");
        String optionSelected = userInput.nextLine();
        switch (optionSelected){
            case "1":
                changeInventoryItemName(searchInventoryItemList(itemToUpdate));
                break;
            case "2":
                changeInventoryItemUnitOfVolume(searchInventoryItemList(itemToUpdate));
                break;
            case "3":
                changeInventoryItemNumberOfUnits(searchInventoryItemList(itemToUpdate));
                break;
            case "4":
                returnToMainMenuPrompt();
                break;
            default:
                System.out.println("<------------------------------------------------------------------>");
                System.out.println("<---------------- Invalid Selection!!!!!!! ------------------------>");
                System.out.println("<------------------------------------------------------------------>");
                updateInventoryItemAmount();
        }


    }

    public int searchUnitOfVolumeList(String itemToSearchFor){
        int indexOfItemToBeUpdated = 0;
        for (int i = 0; i < unitsOfVolume.size(); i++){
            if(unitsOfVolume.get(i).getType().equalsIgnoreCase(itemToSearchFor)){
                indexOfItemToBeUpdated = i;
                break;
            }
        }
        return indexOfItemToBeUpdated;
    }

    public int searchInventoryItemList(String itemToSearchFor){
        int indexOfItemToBeUpdated = 0;
        for (int i = 0; i < inventoryItemList.size(); i ++){
            if(inventoryItemList.get(i).getName().equalsIgnoreCase(itemToSearchFor)) {
                indexOfItemToBeUpdated = i;
                break;
            }
        }
        return indexOfItemToBeUpdated;
    }

    public void changeInventoryItemName(int indexOfItem){
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<---------- Type The New Name Of Inventory Item  ------------------>");
        System.out.println("<------------------------------------------------------------------>");
        String newName = userInput.nextLine();
        inventoryItemList.get(indexOfItem).setName(newName);
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<--------------------------  Success!!  --------------------------->");
        System.out.println("<------------------------------------------------------------------>");
        returnToMainMenuPrompt();
    }

    public void changeInventoryItemUnitOfVolume(int indexOfItem){
        String newUnitOfVolume = "";
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<---------- Type The New Unit Of Volume --------------------------->");
        System.out.println("<------------------------------------------------------------------>");
        newUnitOfVolume = userInput.nextLine();
        inventoryItemList.get(indexOfItem).setUnit(unitsOfVolume.get(searchUnitOfVolumeList(newUnitOfVolume)));
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<--------------------------  Success!!  --------------------------->");
        System.out.println("<------------------------------------------------------------------>");
        returnToMainMenuPrompt();
    }

    public void changeInventoryItemNumberOfUnits(int indexOfItem){
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<---------- Type The Updated Unit Amount -------------------------->");
        System.out.println("<------------------------------------------------------------------>");
        double newNumberOfUnits = Double.parseDouble(userInput.nextLine());
        inventoryItemList.get(indexOfItem).setNumberOfUnits(newNumberOfUnits);
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<--------------------------  Success!!  --------------------------->");
        System.out.println("<------------------------------------------------------------------>");
        returnToMainMenuPrompt();
    }

    public void returnToMainMenuPrompt(){
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<-------------------- Return To Main Menu ? ----------------------->");
        System.out.println("<-----------------------Yes-----------NO--------------------------->");
        String reloadMenu = userInput.nextLine();
        if(reloadMenu.equalsIgnoreCase("Yes")){
            createMenuAndScannerForUserInput();
        } else if(reloadMenu.equalsIgnoreCase("No")){
            closeOutSession();
        } else
            System.out.println("<-------------------- Invalid Selection---------------------------->");
            returnToMainMenuPrompt();

    }

    public void currentInventoryItemListHeader(){
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<---------------------- Current Inventory ------------------------->");
        System.out.println("<------------------------------------------------------------------>");
    }

    public void displayListOfInventoryItems(){
        for(InventoryItem item : inventoryItemList){
            System.out.println(item.getName() + " " + item.getNumberOfUnits() + " " + item.getUnitOfVolume() +"'s");
        }
    }

    public void closeOutSession(){
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<---------------------Goodbye For Now!!---------------------------->");
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("<------------------------------------------------------------------>");

    }

    public void createNewInventoryItem(){

    }


}
