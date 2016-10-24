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
public class Application {
	
	public static Staff thisStaff = null;	
	
	public static void main(String[] args) {

		Restaurant.loadRestaurant();
		
		while(thisStaff == null)
			thisStaff = menuGetStaffIdentity(Restaurant.staffs);

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
		
		Scanner sc = new Scanner(System.in);
		
		int index = 0;
		System.out.println("Who are you ?");
		for(Staff s : staffs){
			System.out.println("(" + index++ + ") " + s);
		}
    	System.out.print("    Enter the number of your choice: ");
    	
		int choice = sc.nextInt();
		sc.close();
		
		try {
			return staffs.get(choice);
		}catch(IndexOutOfBoundsException e){
			System.out.println("Invalid index entered!");
			return null;
		}
		
	}
	
	/**
	 * Display a list of restaurant options staff can perform through
	 * the application.
	 */
	public void menuShowRestaurantOptions(){

		Scanner sc = new Scanner(System.in);
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
                		InvoiceMgr.printSalesRevenueReport();
                		break;
                case 5:
            }

        } while (choice < 5);

        sc.close();
        
	}

	
}
