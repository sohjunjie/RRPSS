package classes;


import java.io.Serializable;
import java.util.ArrayList;

import classes.Food.CourseType;

public class Restaurant implements Serializable{

	private static final long serialVersionUID = 5980715250927144785L;
	public static ArrayList<Table> 				tables;
	public static ArrayList<Staff> 				staffs;
	public static ArrayList<MenuItem> 			foodMenu;
	
	public static ArrayList<Invoice> 			invoices;
	public static ArrayList<Order> 				orders;
	public static ArrayList<Reservation>		reservations;

	public Restaurant(){
		// Initialize restaurant
        this.tables 	= initTables();
        this.staffs 	= initStaff();
        this.foodMenu	= initMenu();
	}

	public ArrayList<MenuItem> initMenu(){
		
		ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
		Food food1 = new Food("Burger", "This is a burger", 3.50, CourseType.MAIN_COURSE);
		Food food2 = new Food("NuggetS", "Nuggets - halal", 1.00, CourseType.MAIN_COURSE);
		Food food3 = new Food("Oreo McFlurry", "Ice cream dessert", 3.85, CourseType.DESSERT);
		Food food4 = new Food("Apple ice cream", "Apple dessert", 8.00, CourseType.DESSERT);
		Food food5 = new Food("Ice Lemon Tea", "Cheap drink", 1.50, CourseType.DRINKS);
		Food food6 = new Food("Plain water", "free drink", 0.00, CourseType.DRINKS);
		Food food7 = new Food("Healthy fruit juice", "fruit juice", 2.00, CourseType.DRINKS);
		
		PromotionPackage promo1 = new PromotionPackage("McNugget Meal", "6pc nuggets in 1", 3.00);
		promo1.addFood(food2);
		promo1.addFood(food2);
		promo1.addFood(food2);
		promo1.addFood(food2);
		promo1.addFood(food2);
		promo1.addFood(food2);

		PromotionPackage promo2 = new PromotionPackage("Burger set meal", "Burger set at cheaper price", 5.00);
		promo2.addFood(food1);
		promo2.addFood(food2);
		promo2.addFood(food7);

		PromotionPackage promo3 = new PromotionPackage("Healthy set meal", "Very healthy meal", 2.00);
		promo3.addFood(food7);
		promo3.addFood(food6);
		promo3.addFood(food4);

		menuItems.add((MenuItem) food1);
		menuItems.add((MenuItem) food2);
		menuItems.add((MenuItem) food3);
		menuItems.add((MenuItem) food4);
		menuItems.add((MenuItem) food5);
		menuItems.add((MenuItem) food6);
		menuItems.add((MenuItem) food7);
		menuItems.add((MenuItem) promo1);
		menuItems.add((MenuItem) promo2);
		menuItems.add((MenuItem) promo3);
		
		return menuItems;
		
	}

	public ArrayList<Staff> initStaff(){
		ArrayList<Staff> retStaff = new ArrayList<Staff>();
		retStaff.add(new Staff("John", 1, true, "Chef"));
		retStaff.add(new Staff("May", 2, false, "Cashier"));
		retStaff.add(new Staff("Nitro", 3, true, "Waiter"));
		retStaff.add(new Staff("Miki", 4, false, "Cashier"));
		return retStaff;
	}

	public ArrayList<Table> initTables(){
		ArrayList<Table> retTables = new ArrayList<Table>();
		int i;
		for(i=0; i<5; i++)						// 5 x 10 seats
			retTables.add(new Table(i, 10));	
		for(i=5; i<10; i++)						// 5 x 8 seats
			retTables.add(new Table(i, 8));
		for(i=10; i<20; i++)					// 10 x 4 seats
			retTables.add(new Table(i, 4));
		for(i=20; i<30; i++)					// 10 x 2 seats
			retTables.add(new Table(i, 2));
		return retTables;
	}


}