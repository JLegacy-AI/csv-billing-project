
/**
 * Billing - Start of this Application
 * This class has InventoryMSystem Class instance
 * @version (v1.0 - 4/29/22)
 */
import java.util.*;
import java.io.*;

public class Billing
{   
    private Scanner reader = new Scanner(System.in);
    private InventoryMSystem ims = new InventoryMSystem("JK-Legacy Shop");
    
    public void start(){
        int option = options();
        if(option == 0){getName();}
        else if(option == 1){listAllItems();}
        else if(option == 2){listAllCards();}
        else if(option == 3){orderOneItem();}
        else if(option == 4){orderListOfItems();}
        else if(option == 5){quit();}
        start();
    }
    
    public int options(){
        System.out.println("****Welcome**Billing****");
        System.out.println("0. Shop Name");
        System.out.println("1. List All Items");
        System.out.println("2. List All Cards");
        System.out.println("3. Order one Item");
        System.out.println("4. Order By CSV-File");
        System.out.println("5. Quit");
        System.out.println("****Choose-Option*******");
        return reader.nextInt();
    }
    
    public void getName(){
        System.out.println("************************");
        System.out.println("Name:\t"+ims.getName());        
        System.out.println("************************");
    }
    
    public void listAllItems(){
        System.out.println("************************");
        System.out.println(ims.listAllItems());
        System.out.println("************************");
    }
    
    public void listAllCards(){
        System.out.println("************************");
        System.out.println(ims.listAllCards());
        System.out.println("************************");
    }
    
    public void orderOneItem(){
        System.out.println("************************");
        //Input Name of Item
        System.out.print("Enter Item Name: ");
        String itemName = reader.next();
        //Input Quantity of Item
        System.out.print("Enter Quantity of "+itemName+": ");
        int quantity = reader.nextInt();
        //Input Card Number
        System.out.print("Enter Card Number: ");
        String cardNumber = reader.next();
        //Create Anonymus Object there is no furthur Utillization
        System.out.println(ims.orderOneItem(new Order.Builder(itemName).setQuantity(quantity).setCardNumber(cardNumber).build()));
        System.out.println("************************");
    }
    
    public void orderListOfItems(){
        System.out.println("************************");
        reader.nextLine();
        System.out.println("Enter File-Path: ");
        String path = reader.nextLine();
        System.out.println(ims.orderByCSV(path));
        System.out.println("************************");
    }
    
    public void quit(){
        System.out.println("************************");
        System.out.println("Thank \u2665 You");
        System.exit(0);
    }
    
    public static void main(String[] arg){
         Billing bill = new Billing();
         bill.start();
    }
}
