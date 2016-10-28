package mgr;
import java.util.ArrayList;
import java.util.Scanner;

import classes.Food;
import classes.MenuItem;
import classes.PromotionPackage;
import classes.Table;
import db.Restaurant;

public class FoodMenuMgr {

	public static ArrayList<MenuItem> foodMenu = Restaurant.foodMenu;
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void createNewFood(){
		// TODO: Create new food and add to food menu
		String foodName;
		String foodDesc;
		double foodPrice;
		Food.CourseType foodCourseType = null;
		
		Food newFood;
		
		System.out.print("Enter menu name of promotion package: "); foodName = sc.nextLine();
		System.out.print("Enter menu description of promotion package: "); foodDesc = sc.nextLine();
		System.out.print("Enter price of promotion package: "); foodPrice = sc.nextDouble();
		
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
		
		newFood = new Food(foodName, foodDesc, foodPrice, foodCourseType);
		foodMenu.add((MenuItem) newFood);
		System.out.println("Food " + foodName + " was added to the food menu.");
		
	}
	
	public static void createNewPromotionPackage(){

		String menuName;
		String menuDesc;
		double menuPrice;
		PromotionPackage newPP;
		
		System.out.print("Enter menu name of promotion package: "); menuName = sc.nextLine();
		System.out.print("Enter menu description of promotion package: "); menuDesc = sc.nextLine();
		System.out.print("Enter price of promotion package: "); menuPrice = sc.nextDouble();
		
		newPP = new PromotionPackage(menuName, menuDesc, menuPrice);
		
		boolean continueAdd = true;
		do{
			
			newPP.addFood(foodMenu);
			
			System.out.println("Continue adding menu item to promotion package? (Y/N)");
			continueAdd = ('Y' == Character.toUpperCase(sc.next().charAt(0)));			
		}while(continueAdd);
		
		foodMenu.add((MenuItem) newPP);
		
	}
	
	public static void removeMenuItem(){
		
		int choice;
		String itemRemoved;
		
		int index = 0;
		System.out.println("\nSelect the menu item to remove from the food menu:");
		for(MenuItem menuItem : foodMenu)
			System.out.println("(" + index++ + ") " + menuItem.getMenuName());

    	System.out.print("    Enter the number of your choice: ");
		choice = sc.nextInt();
		sc.close();
		
		try {
			itemRemoved = foodMenu.get(choice).getMenuName();
			foodMenu.remove(choice);
			System.out.println(itemRemoved + " removed from food menu."); 
		}catch(IndexOutOfBoundsException e){
			System.out.println("Remove menu item from food menu failed! (Invalid index provided");
		}

	}
	
}
