import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import classes.*;
import db.Restaurant;
import mgr.InvoiceMgr;
import mgr.OrderMgr;
import ui.FoodMenuUI;
import ui.ReservationUI;

public class application {
	
	public static Staff thisStaff = null;
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {

		Restaurant.loadRestaurant();
		
		while(thisStaff == null)
			thisStaff = menuGetStaffIdentity(Restaurant.staffs);

		// close shop - settle all pending orders before closing application
		
		Restaurant.saveRestaurant();
		
		System.exit(0);

	}


	
	public static Staff menuGetStaffIdentity(ArrayList<Staff> staffs){
		
		int index = 0;
		System.out.println("Who are you ?");
		for(Staff s : staffs){
			System.out.println("(" + index++ + ") " + s);
		}
    	System.out.print("    Enter the number of your choice: ");
		int choice = sc.nextInt();
		
		try {
			return staffs.get(choice);
		}catch(IndexOutOfBoundsException e){
			System.out.println("Invalid index entered!");
			return null;
		}

	}
	
	public void menuShowRestaurantOptions(){
		
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

	}

	
}
