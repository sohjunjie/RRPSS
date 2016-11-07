package db;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import classes.Food;
import classes.Food.CourseType;
import classes.Invoice;
import classes.MenuItem;
import classes.Order;
import classes.PromotionPackage;
import classes.Reservation;
import classes.Staff;
import classes.Table;

public class Restaurant {

	public static final int		bookingMthInAdvance			= 1;
	public static final	int		AMStartTime					= 11;
	public static final	int		AMEndTime					= 15;
	public static final	int		PMStartTime					= 18;
	public static final	int		PMEndTime					= 22;
	
	public static final Path 	dataPath 					= Paths.get(System.getProperty("user.dir"), "data");
	public static final String 	tableFileName				= "table.dat";
	public static final String 	staffFileName				= "staff.dat";
	public static final String 	menuItemFileName			= "menuItem.dat";
	public static final String 	invoiceFileName				= "invoice.dat";
	public static final String 	orderFileName				= "order.dat";
	public static final String 	settledOrderFileName		= "settledOrder.dat";
	public static final String 	reservationFileName			= "reservation.dat";
	public static final String 	settledReservationFileName	= "settledReservation.dat";
	public static final String 	restaurantFileName			= "restaurant.dat";	
	
	public static ArrayList<Table> 				tables;
	public static ArrayList<Staff> 				staffs;
	public static ArrayList<MenuItem> 			foodMenu;
	
	public static ArrayList<Invoice> 			invoices;
	
	public static ArrayList<Order> 				orders;
	public static ArrayList<Order> 				settledOrders;
	
	public static ArrayList<Reservation>		reservations;
	public static ArrayList<Reservation>		settledReservations;
	
	public static void main(String args[]){
		System.out.println(dataPath.toString());
	}
	
	public static void saveRestaurant(){
		
		Object[] restaurantMember 	= {tables,
										staffs, 
										foodMenu, 
										invoices, 
										orders, 
										settledOrders, 
										reservations, 
										settledReservations};
		
		Path 				saveFileName 	= Paths.get(dataPath.toString(), restaurantFileName);
		FileOutputStream   	fos 			= null;
		ObjectOutputStream 	oos 			= null;
		
		try {
			fos = new FileOutputStream(saveFileName.toString());
			oos = new ObjectOutputStream(fos);
			oos.writeObject(restaurantMember);
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public static void loadRestaurant(){
		
		Object[] restaurantMember 	= null;
		Path saveData 				= Paths.get(dataPath.toString(), restaurantFileName);
		FileInputStream fis 		= null;
		ObjectInputStream ois 		= null;
		
		try {
			fis = new FileInputStream(saveData.toString());
			ois = new ObjectInputStream(fis);
			restaurantMember = (Object[]) ois.readObject();
			
			if(restaurantMember != null){
				tables = (ArrayList<Table>) restaurantMember[0];
				staffs = (ArrayList<Staff>) restaurantMember[1];
				foodMenu = (ArrayList<MenuItem>) restaurantMember[2];
				invoices = (ArrayList<Invoice>) restaurantMember[3];
				orders = (ArrayList<Order>) restaurantMember[4];
				settledOrders = (ArrayList<Order>) restaurantMember[5];
				reservations = (ArrayList<Reservation>) restaurantMember[6];
				settledReservations = (ArrayList<Reservation>) restaurantMember[7];
			}
			
			ois.close();
		} catch (IOException ex) {
			System.out.println(restaurantFileName + " not found or does not exists. Default settings will be loaded.");
			initRestaurant();
		} catch (ClassCastException|ClassNotFoundException ex) {
			System.out.println("Data file " + restaurantFileName + " is corrupted. Default settings will be loaded instead.");
			initRestaurant();
		}
		
	}
	
	public static void initRestaurant(){
		initTables();
		initStaffs();
		initFoodMenu();
		initInvoices();
		initOrders();
		initSettledOrders();
		initReservations();
		initSettledReservations();
	}
	
//	public static void saveRestaurant(){
//		saveTables();
//		saveStaffs();
//		saveFoodMenu();
//		saveInvoices();
//		saveOrders();
//		saveSettledOrders();
//		saveReservations();
//		saveSettledReservations();
//	}
//
//	public static void loadRestaurant(){
//		loadTables();
//		loadStaffs();
//		loadFoodMenu();
//		loadInvoices();
//		loadOrders();
//		loadSettledOrders();
//		loadReservations();
//		loadSettledReservations();
//	}
	
	public static void resetTables(){
		Restaurant.tables = null;
	}
	public static void initTables(){
		ArrayList<Table> tables = new ArrayList<Table>();
		int i;
		for(i=0; i<5; i++)						// 5 x 10 seats
			tables.add(new Table(i, 10));	
		for(i=5; i<10; i++)						// 5 x 8 seats
			tables.add(new Table(i, 8));
		for(i=10; i<20; i++)					// 10 x 4 seats
			tables.add(new Table(i, 4));
		for(i=20; i<30; i++)					// 10 x 2 seats
			tables.add(new Table(i, 2));
		Restaurant.tables = tables;
	}
	public static void saveTables(){
		Path 				saveFileName 	= Paths.get(dataPath.toString(), tableFileName);
		FileOutputStream   	fos 			= null;
		ObjectOutputStream 	oos 			= null;
		
		try {
			fos = new FileOutputStream(saveFileName.toString());
			oos = new ObjectOutputStream(fos);
			oos.writeObject(Restaurant.tables);
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}	
	
	public static void loadTables(){

		Path saveData 			= Paths.get(dataPath.toString(), tableFileName);
		FileInputStream fis 	= null;
		ObjectInputStream ois 	= null;
		
		try {
			fis = new FileInputStream(saveData.toString());
			ois = new ObjectInputStream(fis);
			Restaurant.tables = (ArrayList<Table>) ois.readObject();
			ois.close();
		} catch (IOException ex) {
			System.out.println(tableFileName + " not found or does not exists. Default settings will be loaded.");
			initTables();
		} catch (ClassCastException|ClassNotFoundException ex) {
			System.out.println("Data file " + tableFileName + " is corrupted. Default settings will be loaded instead.");
			initTables();
		}

	}
	
	public static void resetStaffs(){
		Restaurant.staffs = null;
	}
	public static void initStaffs(){
		ArrayList<Staff> staffs = new ArrayList<Staff>();
		staffs.add(new Staff("John", 1, 'M', "Chef"));
		staffs.add(new Staff("May", 2, 'F', "Cashier"));
		staffs.add(new Staff("Nitro", 3, 'M', "Waiter"));
		staffs.add(new Staff("Miki", 4, 'F', "Cashier"));
		Restaurant.staffs = staffs;
	}
	public static void saveStaffs(){
		Path 				saveFileName 	= Paths.get(dataPath.toString(), staffFileName);
		FileOutputStream   	fos 			= null;
		ObjectOutputStream 	oos 			= null;
		
		try {
			
			fos = new FileOutputStream(saveFileName.toString());
			oos = new ObjectOutputStream(fos);
			oos.writeObject(Restaurant.staffs);
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	public static void loadStaffs(){

		Path saveData 			= Paths.get(dataPath.toString(), staffFileName);
		FileInputStream fis 	= null;
		ObjectInputStream ois 	= null;
		
		try {
			fis = new FileInputStream(saveData.toString());
			ois = new ObjectInputStream(fis);
			Restaurant.staffs = (ArrayList<Staff>) ois.readObject();
			ois.close();
		} catch (IOException ex) {
			System.out.println(staffFileName + " not found or does not exists. Default settings will be loaded.");
			initStaffs();
		} catch (ClassCastException|ClassNotFoundException ex) {
			System.out.println("Data file " + staffFileName + " is corrupted. Default settings will be loaded instead.");
			initStaffs();
		}

	}
	
	public static void resetFoodMenu(){
		Restaurant.foodMenu = null;
	}
	public static void initFoodMenu(){
		
		ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
		
		Food food1 = new Food("Burger", "Juicy American burger", 3.50, CourseType.MAIN_COURSE);
		Food food2 = new Food("Nuggets", "Nuggets - halal", 1.00, CourseType.MAIN_COURSE);
		Food food3 = new Food("Oreo McFlurry", "Ice cream dessert filled with crushed oreos", 3.85, CourseType.DESSERT);
		Food food4 = new Food("Apple Ice Cream", "Apple flavoured ice cream dessert", 8.00, CourseType.DESSERT);
		Food food5 = new Food("Ice Lemon Tea", "Homemade Ice Lemon Tea", 1.50, CourseType.DRINKS);
		Food food6 = new Food("Plain Water", "On the house", 0.00, CourseType.DRINKS);
		Food food7 = new Food("Healthy Fruit Juice", "Mixed fruit juice", 2.00, CourseType.DRINKS);
		
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
		
		Restaurant.foodMenu = menuItems;
		
	}
	public static void saveFoodMenu(){
		
		Path 				saveFileName 	= Paths.get(dataPath.toString(), menuItemFileName);
		FileOutputStream   	fos 			= null;
		ObjectOutputStream 	oos 			= null;
		
		try {
			
			fos = new FileOutputStream(saveFileName.toString());
			oos = new ObjectOutputStream(fos);
			oos.writeObject(Restaurant.foodMenu);
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
	public static void loadFoodMenu(){

		Path saveData 			= Paths.get(dataPath.toString(), menuItemFileName);
		FileInputStream fis 	= null;
		ObjectInputStream ois 	= null;
		
		try {
			fis = new FileInputStream(saveData.toString());
			ois = new ObjectInputStream(fis);
			Restaurant.foodMenu = (ArrayList<MenuItem>) ois.readObject();
			ois.close();
		} catch (IOException ex) {
			System.out.println(menuItemFileName + " not found or does not exists. Default settings will be loaded.");
			initFoodMenu();
		} catch (ClassCastException|ClassNotFoundException ex) {
			System.out.println("Data file " + menuItemFileName + " is corrupted. Default settings will be loaded instead.");
			initFoodMenu();
		}

	}
	
	public static void resetInvoices(){
		Restaurant.invoices = null;
	}
	public static void initInvoices(){
        Restaurant.invoices = new ArrayList<Invoice>();
	}
	public static void saveInvoices(){
		Path 				saveFileName 	= Paths.get(dataPath.toString(), invoiceFileName);
		FileOutputStream   	fos 			= null;
		ObjectOutputStream 	oos 			= null;
		
		try {
			fos = new FileOutputStream(saveFileName.toString());
			oos = new ObjectOutputStream(fos);
			oos.writeObject(Restaurant.invoices);
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}	
	public static void loadInvoices(){

		Path saveData 			= Paths.get(dataPath.toString(), invoiceFileName);
		FileInputStream fis 	= null;
		ObjectInputStream ois 	= null;
		
		try {
			fis = new FileInputStream(saveData.toString());
			ois = new ObjectInputStream(fis);
			Restaurant.invoices = (ArrayList<Invoice>) ois.readObject();
			ois.close();
		} catch (IOException ex) {
			System.out.println(invoiceFileName + " not found or does not exists. Default settings will be loaded.");
			initInvoices();
		} catch (ClassCastException|ClassNotFoundException ex) {
			System.out.println("Data file " + invoiceFileName + " is corrupted. Default settings will be loaded instead.");
			initInvoices();
		}

	}
	
	public static void resetOrders(){
		Restaurant.orders = null;
	}
	public static void initOrders(){
		Restaurant.orders = new ArrayList<Order>();
	}
	public static void saveOrders(){
		Path 				saveFileName 	= Paths.get(dataPath.toString(), orderFileName);
		FileOutputStream   	fos 			= null;
		ObjectOutputStream 	oos 			= null;
		
		try {
			fos = new FileOutputStream(saveFileName.toString());
			oos = new ObjectOutputStream(fos);
			oos.writeObject(Restaurant.orders);
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}	
	public static void loadOrders(){

		Path saveData 			= Paths.get(dataPath.toString(), orderFileName);
		FileInputStream fis 	= null;
		ObjectInputStream ois 	= null;
		
		try {
			fis = new FileInputStream(saveData.toString());
			ois = new ObjectInputStream(fis);
			Restaurant.orders = (ArrayList<Order>) ois.readObject();
			ois.close();
		} catch (IOException ex) {
			System.out.println(orderFileName + " not found or does not exists. Default settings will be loaded.");
			initOrders();
		} catch (ClassCastException|ClassNotFoundException ex) {
			System.out.println("Data file " + orderFileName + " is corrupted. Default settings will be loaded instead.");
			initOrders();
		}

	}
	
	public static void resetSettledOrders(){
		Restaurant.settledOrders = null;
	}
	public static void initSettledOrders(){
        Restaurant.settledOrders = new ArrayList<Order>();
	}
	public static void saveSettledOrders(){
		Path 				saveFileName 	= Paths.get(dataPath.toString(), settledOrderFileName);
		FileOutputStream   	fos 			= null;
		ObjectOutputStream 	oos 			= null;
		
		try {
			fos = new FileOutputStream(saveFileName.toString());
			oos = new ObjectOutputStream(fos);
			oos.writeObject(Restaurant.settledOrders);
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	public static void loadSettledOrders(){

		Path saveData 			= Paths.get(dataPath.toString(), settledOrderFileName);
		FileInputStream fis 	= null;
		ObjectInputStream ois 	= null;
		
		try {
			fis = new FileInputStream(saveData.toString());
			ois = new ObjectInputStream(fis);
			Restaurant.settledOrders = (ArrayList<Order>) ois.readObject();
			ois.close();
		} catch (IOException ex) {
			System.out.println(settledOrderFileName + " not found or does not exists. Default settings will be loaded.");
			initSettledOrders();
		} catch (ClassCastException|ClassNotFoundException ex) {
			System.out.println("Data file " + settledOrderFileName + " is corrupted. Default settings will be loaded instead.");
			initSettledOrders();
		}

	}
	
	public static void resetReservations(){
		Restaurant.reservations = null;
	}
	public static void initReservations(){
        Restaurant.reservations = new ArrayList<Reservation>();
	}
	public static void saveReservations(){
		Path 				saveFileName 	= Paths.get(dataPath.toString(), reservationFileName);
		FileOutputStream   	fos 			= null;
		ObjectOutputStream 	oos 			= null;
		
		try {
			fos = new FileOutputStream(saveFileName.toString());
			oos = new ObjectOutputStream(fos);
			oos.writeObject(Restaurant.reservations);
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}	
	public static void loadReservations(){

		Path saveData 			= Paths.get(dataPath.toString(), reservationFileName);
		FileInputStream fis 	= null;
		ObjectInputStream ois 	= null;
		
		try {
			fis = new FileInputStream(saveData.toString());
			ois = new ObjectInputStream(fis);
			Restaurant.reservations = (ArrayList<Reservation>) ois.readObject();
			ois.close();
		} catch (IOException ex) {
			System.out.println(reservationFileName + " not found or does not exists. Default settings will be loaded.");
			initReservations();
		} catch (ClassCastException|ClassNotFoundException ex) {
			System.out.println("Data file " + reservationFileName + " is corrupted. Default settings will be loaded instead.");
			initReservations();
		}

	}
	
	public static void resetSettledReservations(){
		Restaurant.settledReservations = null;
	}
	public static void initSettledReservations(){
        Restaurant.settledReservations = new ArrayList<Reservation>();
	}
	public static void saveSettledReservations(){
		Path 				saveFileName 	= Paths.get(dataPath.toString(), settledReservationFileName);
		FileOutputStream   	fos 			= null;
		ObjectOutputStream 	oos 			= null;
		
		try {
			fos = new FileOutputStream(saveFileName.toString());
			oos = new ObjectOutputStream(fos);
			oos.writeObject(Restaurant.settledReservations);
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}	
	public static void loadSettledReservations(){

		Path saveData 			= Paths.get(dataPath.toString(), settledReservationFileName);
		FileInputStream fis 	= null;
		ObjectInputStream ois 	= null;
		
		try {
			fis = new FileInputStream(saveData.toString());
			ois = new ObjectInputStream(fis);
			Restaurant.settledReservations = (ArrayList<Reservation>) ois.readObject();
			ois.close();
		} catch (IOException ex) {
			System.out.println(settledReservationFileName + " not found or does not exists. Default settings will be loaded.");
			initSettledReservations();
		} catch (ClassCastException|ClassNotFoundException ex) {
			System.out.println("Data file " + settledReservationFileName + " is corrupted. Default settings will be loaded instead.");
			initSettledReservations();
		}

	}
}
