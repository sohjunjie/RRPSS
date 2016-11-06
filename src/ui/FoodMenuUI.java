package ui;

import java.util.ArrayList;
import java.util.Scanner;

import classes.Food;
import classes.MenuItem;
import classes.PromotionPackage;
import mgr.FoodMenuMgr;
import user_lib.ScannerExt;

/**
 * Represents the UI displayed to the user when changing the food menu.
 * @author Sean
 * @version 1.0
 * @since 2016-10-28
 */
public class FoodMenuUI {
	
	private static Scanner sc = new Scanner(System.in);
	
	
	/**
	 * Display options user can perform on restaurant food menu.
	 * User can Add new food, new promotion package or remove 
	 * menu item from menu
	 */	
	public static void menuShowFoodMenuOptions(){
		
		int choice;
		
        do {
        	System.out.println("\nSelect a choice: ");
            System.out.println("(1) Add new food to food menu");
            System.out.println("(2) Add new promotion package to food menu");
            System.out.println("(3) Update menu item in food menu");
            System.out.println("(4) Remove a menu item from food menu");
            System.out.println("(5) Back");
        	choice = ScannerExt.nextInt("\n    Enter the number of your choice: ", 1, 5);
        	
        	switch (choice) {
            case 1: 
            		createNewFoodUI();
                    break;
            case 2:
            		createNewPromotionPackageUI();
                    break;
            case 3:
            		updateMenuItemUI();
                	break;
            case 4:
            		removeMenuItemUI();
            		break;
            case 5:
        }

    } while (choice < 5);
	
}
	
	/**
	 * Show a UI that prompts user for menu item to be
	 * removed.
	 */
	private static void removeMenuItemUI(){
		
		int choice;
		ArrayList<MenuItem> foodMenu = FoodMenuMgr.getRestaurantFoodMenu();

		int index = 0;
		System.out.println("\nSelect the menu item to remove from the food menu:");
		for(MenuItem menuItem : foodMenu)
			System.out.println("(" + index++ + ") " + menuItem.getMenuName());
    	choice = ScannerExt.nextInt("    Enter the number of your choice: ");
		

		FoodMenuMgr.removeMenuItem(choice);

	}
	
	/**
	 * Show a UI that prompts user for details of
	 * the promotion package to be created.
	 */
	private static void createNewPromotionPackageUI(){
		
		String menuName;
		String menuDesc;
		double menuPrice;
		
		System.out.print("Enter menu name of promotion package: "); menuName = sc.nextLine();
		System.out.print("Enter menu description of promotion package: "); menuDesc = sc.nextLine();
		menuPrice = ScannerExt.nextDouble("Enter price of promotion package: ");
		
		FoodMenuMgr.createNewPromotionPackage(menuName, menuDesc, menuPrice);
	}
	
	/**
	 * Show a UI that prompts user for details of
	 * the food to be created.
	 */
	private static void createNewFoodUI(){
		
		String foodName;
		String foodDesc;
		double foodPrice;
		Food.CourseType foodCourseType = null;

		System.out.print("Enter menu name of food: "); foodName = sc.nextLine();
		System.out.print("Enter menu description of food: "); foodDesc = sc.nextLine();
		foodPrice = ScannerExt.nextDouble("Enter price of food: ");
		
		int choice;
		do{
			System.out.println("\nSelect course type of your food.");
			int index = 1;
			for (Food.CourseType courseType : Food.CourseType.values())
				System.out.println((index++) + " " + courseType);
        	choice = ScannerExt.nextInt("    Enter the number of your choice: ");

	    	switch (choice) {
		        case 1:
		        	foodCourseType = Food.CourseType.MAIN_COURSE;
		            break;
		        case 2:
		        	foodCourseType = Food.CourseType.DRINKS;
		            break;
		        case 3:
		        	foodCourseType = Food.CourseType.DESSERT;
		            break;
		        default:
		        	System.out.println("Invalid choice entered!");
		            break;
	        }
	    
		}while(choice > 3 || choice < 0);
		
		FoodMenuMgr.createNewFood(foodName, foodDesc, foodPrice, foodCourseType);

	}
	
	/**
	 * Show a UI that prompts user for menu item to be 
	 * updated.
	 */
	private static void updateMenuItemUI(){
		
		int choice;
		ArrayList<MenuItem> foodMenu = FoodMenuMgr.getRestaurantFoodMenu();
		
		int index = 0;
		System.out.println("\nSelect the menu item to update in the food menu:");
		for(MenuItem menuItem : foodMenu)
			System.out.println("(" + index++ + ") " + menuItem.getMenuName());
		
		choice = ScannerExt.nextInt("\n    Enter the number of your choice: ", 0, index);
		
		if (foodMenu.get(choice) instanceof Food){
			updateFood(choice);
		}
		else if (foodMenu.get(choice) instanceof PromotionPackage){
			updatePromotionPackage(choice);
		}
		else
			System.out.println("Invalid number!");
	}
	
	/**
	 * Update menu item name, desc or price from restaurant food menu
	 * based on menu index
	 * @param menuIndex Menu item to be updated
	 */
	private static void updateFood(int menuIndex){

		int choice;

		try {
			
			do {
				System.out.println("\nWhat would you like to update?");
				System.out.println("(1) Food Name");
				System.out.println("(2) Food Description");
				System.out.println("(3) Food Price");
				System.out.println("(4) Food Course Type");
				System.out.println("(5) Back"); 
				choice = ScannerExt.nextInt("\n    Enter the number of your choice: ", 1, 5);
			
				switch (choice) {
					case 1:
							FoodMenuMgr.updateMenuItemName(menuIndex);
							break;
					case 2:
							FoodMenuMgr.updateMenuItemDesc(menuIndex);
							break;
					case 3: 
							FoodMenuMgr.updateMenuItemPrice(menuIndex);
							break;
					case 4:
							FoodMenuMgr.updateFoodCourseType(menuIndex);
							break;
					case 5:
				}
			
			} while (choice != 5);
		
		}catch(IndexOutOfBoundsException e){
			System.out.println("Update menu item in food menu failed! (Invalid index provided");
		}

	}
	
	/**
	 * Update promotion package name, desc or price from restaurant food menu
	 * based on menu index
	 * @param menuIndex Menu item to be updated
	 */
	private static void updatePromotionPackage(int menuIndex){
		int choice;

		try {
			
			do {
				System.out.println("\nWhat would you like to update?");
				System.out.println("(1) Promotion Package Name");
				System.out.println("(2) Promotion Package Description");
				System.out.println("(3) Promotion Package Price");
				System.out.println("(4) Promotion Package Food");
				System.out.println("(5) Back"); 
				choice = ScannerExt.nextInt("\n    Enter the number of your choice: ", 1, 5);
			
				switch (choice) {
					case 1:
							FoodMenuMgr.updateMenuItemName(menuIndex);
							break;
					case 2:
							FoodMenuMgr.updateMenuItemDesc(menuIndex);
							break;
					case 3: 
							FoodMenuMgr.updateMenuItemPrice(menuIndex);
							break;
					case 4:
							FoodMenuMgr.updatePromotionPackageFood(menuIndex);
							break;
					case 5:					
				}
			
			} while (choice != 5);
		
		}catch(IndexOutOfBoundsException e){
			System.out.println("Update menu item in food menu failed! (Invalid index provided");
		}

	}
}

