package ui;

import java.util.Scanner;

import mgr.FoodMenuMgr;

/**
 * Represents the UI displayed to the user when changing the food menu.
 * @author Sean
 *
 */

public class FoodMenuUI {
	
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * While loop that scans user input to decide which operation to execute.
	 * Can Add new food, new promotion package or remove menu item from menu.
	 * Terminate by entering number greater than 4.
	 */
	
	public static void menuShowFoodMenuOptions(){
		
		int choice;
		
        do {
            System.out.println("\nSelect a choice: ");
            System.out.println("(1) Add new food to food menu");
            System.out.println("(2) Add new promotion package to food menu");
            System.out.println("(3) Remove a menu item from food menu");
            System.out.println("(4) Back");
        	System.out.println();
        	System.out.print("    Enter the number of your choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1: 
                		FoodMenuMgr.createNewFood();
                        break;
                case 2:
                		FoodMenuMgr.createNewPromotionPackage();
                        break;
                case 3:
                		FoodMenuMgr.removeMenuItem();
                    	break;
                case 4:
            }

        } while (choice < 4);
		
	}
	
}
