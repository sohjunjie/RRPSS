package mgr;
import java.util.ArrayList;
import java.util.Scanner;

import classes.Food;
import classes.MenuItem;
import classes.PromotionPackage;
import db.Restaurant;

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
		
		boolean continueAdd = true;
		do{
			newPP.addFood(foodMenu);
			System.out.println("Continue adding menu item to promotion package? (Y/N)");
			continueAdd = ('Y' == Character.toUpperCase(sc.next().charAt(0)));
		}while(continueAdd);
		
		foodMenu.add((MenuItem) newPP);
		
	}
	
	/**
	 * Remove a menu item from the restaurant food menu
	 * base on a menu index
	 * @param menuIndex menu item to remove
	 */
	public static void removeMenuItem(int menuIndex){
		
		String itemRemoved;
		try {
			itemRemoved = foodMenu.get(menuIndex).getMenuName();
			foodMenu.remove(menuIndex);
			System.out.println(itemRemoved + " removed from food menu."); 
		}catch(IndexOutOfBoundsException e){
			System.out.println("Remove menu item from food menu failed! (Invalid index provided");
		}

	}
	
}
