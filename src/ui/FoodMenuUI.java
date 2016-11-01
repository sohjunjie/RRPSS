package ui;

import java.util.ArrayList;
import java.util.Scanner;

import classes.Food;
import classes.MenuItem;
import mgr.FoodMenuMgr;

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
            System.out.println("(3) Remove a menu item from food menu");
            System.out.println("(4) Back");
        	System.out.println();
        	System.out.print("    Enter the number of your choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1: 
                		createNewFoodUI();
                        break;
                case 2:
                		createNewPromotionPackageUI();
                        break;
                case 3:
                		removeMenuItemUI();
                    	break;
                case 4:
            }

        } while (choice < 4);
		
	}
	
	/**
	 * Show a UI that prompts user for menu item to be
	 * removed.
	 */
	public static void removeMenuItemUI(){
		
		int choice;
		ArrayList<MenuItem> foodMenu = FoodMenuMgr.getRestaurantFoodMenu();

		int index = 0;
		System.out.println("\nSelect the menu item to remove from the food menu:");
		for(MenuItem menuItem : foodMenu)
			System.out.println("(" + index++ + ") " + menuItem.getMenuName());

    	System.out.print("    Enter the number of your choice: ");
		choice = sc.nextInt();

		FoodMenuMgr.removeMenuItem(choice);

	}
	
	/**
	 * Show a UI that prompts user for details of
	 * the promotion package to be created.
	 */
	public static void createNewPromotionPackageUI(){
		
		String menuName;
		String menuDesc;
		double menuPrice;
		
		System.out.print("Enter menu name of promotion package: "); menuName = sc.nextLine();
		System.out.print("Enter menu description of promotion package: "); menuDesc = sc.nextLine();
		System.out.print("Enter price of promotion package: "); menuPrice = sc.nextDouble();
		
		FoodMenuMgr.createNewPromotionPackage(menuName, menuDesc, menuPrice);
	}
	
	public static void getFoodUI(ArrayList<MenuItem> foodMenu){
		
	}
	
	/**
	 * Show a UI that prompts user for details of
	 * the food to be created.
	 */
	public static void createNewFoodUI(){
		
		String foodName;
		String foodDesc;
		double foodPrice;
		Food.CourseType foodCourseType = null;

		System.out.print("Enter menu name of food: "); foodName = sc.nextLine();
		System.out.print("Enter menu description of food: "); foodDesc = sc.nextLine();
		System.out.print("Enter price of food: "); foodPrice = sc.nextDouble();
		
		int choice;
		do{
			System.out.println("\nSelect course type of your food.");
			int index = 1;
			for (Food.CourseType courseType : Food.CourseType.values())
				System.out.println((index++) + " " + courseType);
	    	System.out.print("    Enter the number of your choice: ");
	    	choice = sc.nextInt();
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
	
}
