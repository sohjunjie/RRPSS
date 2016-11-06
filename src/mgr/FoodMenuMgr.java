package mgr;
import java.util.ArrayList;
import java.util.Scanner;

import classes.Food;
import classes.Food.CourseType;
import classes.MenuItem;
import classes.PromotionPackage;
import db.Restaurant;
import user_lib.ScannerExt;

/**
 * Food Menu Manager class that manages creation, removal
 * of menu item in a food menu
 * @author user soh jun jie
 * @version 1.0
 * @since 2016-10-31
 */
public class FoodMenuMgr {

	private static ArrayList<MenuItem> foodMenu = Restaurant.foodMenu;
	private static Scanner sc = new Scanner(System.in);
	
	public static ArrayList<MenuItem> getRestaurantFoodMenu(){ return foodMenu; }
	
	/**
	 * Create new food item into the restaurant food menu.
	 * @param foodName Name of the food
	 * @param foodDesc Description of the food
	 * @param foodPrice Price of the food
	 * @param foodCourseType Course type of the food
	 */
	public static void createNewFood(String foodName, String foodDesc, double foodPrice, Food.CourseType foodCourseType){		
		Food newFood;
		newFood = new Food(foodName, foodDesc, foodPrice, foodCourseType);
		foodMenu.add((MenuItem) newFood);
		System.out.println("Food " + foodName + " was added to the food menu.");
	}
	
	/**
	 * Create a promotion package into the restaurant food menu
	 * @param menuName Name of the promotion package
	 * @param menuDesc Description of the promotion package
	 * @param menuPrice Price of the promotion package
	 */
	public static void createNewPromotionPackage(String menuName, String menuDesc, double menuPrice){

		PromotionPackage newPP;
		newPP = new PromotionPackage(menuName, menuDesc, menuPrice);
		String choice = "";
		
		boolean continueAdd = true;
		do{
			newPP.addFood(foodMenu);
			System.out.println("Continue adding menu item to promotion package? (Y/N)");
			choice = sc.nextLine();
			continueAdd = ('Y' == Character.toUpperCase(choice.charAt(0)));
		}while(continueAdd);
		
		foodMenu.add((MenuItem) newPP);
		
	}
	
	/**
	 * Remove a menu item from the restaurant food menu
	 * based on a menu index
	 * @param menuIndex Menu item to remove
	 */
	public static void removeMenuItem(int menuIndex){
		
		try {
			String itemRemoved = foodMenu.get(menuIndex).getMenuName();
			foodMenu.remove(menuIndex);
			System.out.println(itemRemoved + " removed from food menu."); 
		}catch(IndexOutOfBoundsException e){
			System.out.println("Remove menu item from food menu failed! (Invalid index provided");
		}

	}
	
	/**
	 * Update a promotion package from the restaurant food menu
	 * based on a menu index
	 * @param menuIndex Menu item to update
	 */
	public static void updatePromotionPackageFood(int menuIndex) {
		int choice;
		int index = 0;
		PromotionPackage pp;
		
		pp = (PromotionPackage) foodMenu.get(menuIndex);
		ArrayList<Food> pp_array = pp.getFoodSet();
		System.out.println("\nFood list: ");
		for(Food food: pp_array)
			System.out.println("(" + index++ + ") " + food.getMenuName());
		try{
			
			do{
			
				System.out.println("\nWould you like to add or delete food from Promotion Package?");
				System.out.println("(1) Add food");
				System.out.println("(2) Remove food");
				System.out.println("(3) Back"); 
				choice = ScannerExt.nextInt("\n    Enter the number of your choice: ", 1, 3);
		
				switch (choice){
					case 1:
							System.out.println();
							pp.addFood(foodMenu);
							break;
					case 2:
							System.out.println();
							pp.removeFood();
							break;
					case 3:
				}
			} while (choice !=  3);
		
		}catch(IndexOutOfBoundsException e){
			System.out.println("Update menu item in food menu failed! (Invalid index provided");
		}
	}
	
	/**
	 * Update the menu item name in the restaurant food menu
	 * based on a menu index
	 * @param menuIndex Menu item to be updated
	 */
	public static void updateMenuItemName(int menuIndex){
		String menuName;
		String itemSearched = foodMenu.get(menuIndex).getMenuName();
		
		System.out.println("\nEnter Updated Menu Name : ");
		menuName = sc.nextLine();
		foodMenu.get(menuIndex).setMenuName(menuName);
		System.out.println("\nMenu name : " + itemSearched + " has been updated to " + menuName);
	}
	
	/**
	 * Update the menu item description in the restaurant food menu
	 * based on a menu index
	 * @param menuIndex Menu item to be updated
	 */
	public static void updateMenuItemDesc(int menuIndex){
		String menuDesc;
		String itemSearched = foodMenu.get(menuIndex).getMenuName();
		String itemDesc = foodMenu.get(menuIndex).getDesc();
		
		System.out.println("\nEnter Updated Menu Description : ");
		menuDesc = sc.nextLine();
		foodMenu.get(menuIndex).setDesc(menuDesc);
		System.out.println("\nDescription of " + itemSearched + " has been updated from '" + itemDesc + "' to '" + menuDesc + "'");
	}
	
	/**
	 * Update the menu item price in the restaurant food menu
	 * based on a menu index
	 * @param menuIndex Menu item to be updated
	 */
	public static void updateMenuItemPrice(int menuIndex){
		double menuPrice;
		String itemSearched = foodMenu.get(menuIndex).getMenuName();
		double itemPrice = foodMenu.get(menuIndex).getPrice();
		
		menuPrice = ScannerExt.nextDouble("\nEnter Updated Menu Price : ");
		foodMenu.get(menuIndex).setPrice(menuPrice);
		System.out.println("\nPrice of " + itemSearched + " has been updated from " + itemPrice + " to " + menuPrice);
	}
	
	/**
	 * Update the menu item course type in the restaurant food menu
	 * based on a menu index
	 * @param menuIndex Menu item to be updated
	 */
	public static void updateFoodCourseType(int menuIndex){
		int menuCourseType;
		CourseType foodCourseType = null;
		Food food;
		String itemSearched = foodMenu.get(menuIndex).getMenuName();
		
		System.out.println("\nEnter Updated Food Course Type : ");
		System.out.println("(1) MAIN_COURSE");
		System.out.println("(2) DESSERT");
		System.out.println("(3) DRINKS");
		
		menuCourseType = ScannerExt.nextInt("\n    Enter the number of your choice: ", 1, 3);
		food = (Food) foodMenu.get(menuIndex);
		CourseType itemCourseType = food.getType();
		
		switch (menuCourseType) {
			case 1:
				food.setType(Food.CourseType.MAIN_COURSE);
				foodCourseType = Food.CourseType.MAIN_COURSE;
				break;
			case 2:
				food.setType(Food.CourseType.DESSERT);
				foodCourseType = Food.CourseType.DESSERT;
				break;
			case 3:
				food.setType(Food.CourseType.DRINKS);
				foodCourseType = Food.CourseType.DRINKS;
				break;
		}
			
		System.out.println("\nCourse Type of " + itemSearched + " has been updated from " + itemCourseType + " to " + foodCourseType);
	}
	
	/**
	 * Print out food name, description and price
	 * based on menu Index
	 * @param menuIndex Menu item to be printed
	 */
	public static void printFood(int menuIndex) {
		System.out.println("\nFood Name: " + foodMenu.get(menuIndex).getMenuName());
		System.out.println("Food Description: " + foodMenu.get(menuIndex).getDesc());
		System.out.println("Food Price: " + foodMenu.get(menuIndex).getPrice());	
	}

	/**
	 * Print out Promotion Package name, description and price,
	 * followed by food items in the promotion package
	 * based on menu Index
	 * @param menuIndex Menu item to be printed
	 */
	public static void printPromotionPackage(int menuIndex) {
		int index=0;
		PromotionPackage pp;
		
		System.out.println("\nPromotion Package Name: " + foodMenu.get(menuIndex).getMenuName());
		System.out.println("Promotion Package Description: " + foodMenu.get(menuIndex).getDesc());
		System.out.println("Promotion Package Price: " + foodMenu.get(menuIndex).getPrice());
		
		pp = (PromotionPackage) foodMenu.get(menuIndex);
		ArrayList<Food> pp_array = pp.getFoodSet();
		System.out.println("\nThis Promotion Package contains: ");
		for(Food food: pp_array)
			System.out.println("(" + index++ + ") " + food.getMenuName());
	}
}
