import java.util.ArrayList;
import java.util.Scanner;

import classes.*;
import db.Restaurant;
import mgr.InvoiceMgr;
import mgr.OrderMgr;
import ui.FoodMenuUI;
import ui.ReservationUI;


/**
 * Main application class to start up the Restaurant Reservation
 * Point of Sales (RRPSS) application. Provides an interface for
 * restaurant staff to perform reservation, order and manage
 * restaurant backend.
 * @author soh jun jie
 * @version 1.0
 * @since 2016-10-24
 */
public class application {
	
	private static Scanner sc = new Scanner(System.in);
	private static Staff thisStaff = null;	
	
	public static void main(String[] args) {

		Restaurant.loadRestaurant();
		
		while(thisStaff == null)
			thisStaff = menuGetStaffIdentity(Restaurant.staffs);

		showRestaurantOptions();

		// close shop - settle all pending orders before closing application

		Restaurant.saveRestaurant();		
		System.exit(0);

	}


	/**
	 * Method for user to select his staff identity
	 * from a given printed list of staff registered
	 * in the restaurant.
	 * @param staffs List of staff registered in this restaurant
	 * @return Staff representing the user
	 */
	public static Staff menuGetStaffIdentity(ArrayList<Staff> staffs){
		
		Staff retStaff = null;
		
		int index = 0;
		System.out.println("Who are you ?");
		for(Staff s : staffs){
			System.out.println("(" + index++ + ") " + s);
		}
    	System.out.print("    Enter the number of your choice: "); int choice = sc.nextInt();
		
		try {
			retStaff = staffs.get(choice);
		}catch(IndexOutOfBoundsException e){
			System.out.println("Invalid index entered!");
		}
		
		return retStaff;
		
	}
	
	/**
	 * Method for user to select which month and year
	 * for the sales report to be printed.
	 */
	public static void showSalesReportUI(){
		
		int month, year;
		
		System.out.println("\nMonth:");
		System.out.println("1. January");
		System.out.println("2. Feburary");
		System.out.println("3. March");
		System.out.println("4. April");
		System.out.println("5. May");
		System.out.println("6. June");
		System.out.println("7. July");
		System.out.println("8. August");
		System.out.println("9. September");
		System.out.println("10. October");
		System.out.println("11. November");
		System.out.println("12. December");
		
		System.out.print("Enter month (1~12): "); 	month = sc.nextInt(); 
		System.out.print("Year(YYYY): "); 			year = sc.nextInt();
		
		InvoiceMgr.printSalesRevenueReport(month, year);
		
	}
	
	/**
	 * Display a list of restaurant options staff can perform through
	 * the application.
	 */
	public static void showRestaurantOptions(){
		
		int choice;
		
        do {
            System.out.println("\nSelect a choice: ");
            System.out.println("(1) Reservation");
            System.out.println("(2) Orders");
            System.out.println("(3) Restaurant Food Menu");
            System.out.println("(4) Sales Revenue report");
            System.out.println("(5) Exit");
        	System.out.println();
        	System.out.print("    Enter the number of your choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1: // reservation
                		ReservationUI.menuShowReservationOptions(thisStaff);
                        break;
                case 2:	// edit a pending order
                		OrderMgr.editPendingOrder();
                        break;
                case 3:
                		FoodMenuUI.menuShowFoodMenuOptions();
                    	break;
                case 4:
                		showSalesReportUI();
                		break;
                case 5:
            }

        } while (choice < 5);
        
	}

	
}
