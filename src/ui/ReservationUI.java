package ui;

import java.util.Calendar;
import java.util.Scanner;

import classes.Staff;
import mgr.ReservationMgr;
import mgr.TableMgr;

public class ReservationUI {

	public static void menuShowReservationOptions(Staff staff){

		int choice;
		Scanner sc = new Scanner(System.in);
		
        do {
            System.out.println("\nSelect a choice: ");
            System.out.println("(1) Show table availability");
            System.out.println("(2) Make reservation");
            System.out.println("(3) Accept reservation");
            System.out.println("(4) Back");
        	System.out.println();
        	System.out.print("    Enter the number of your choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1: 
                		showTableAvailability();
                        break;
                case 2:
                		ReservationMgr.makeReservation();
                        break;
                case 3:
                		ReservationMgr.acceptReservation(staff);
                    	break;
                case 4:
            }

        } while (choice < 4);
		
        sc.close();

	}

	public static void showTableAvailability(){
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of people: "); int numPax = sc.nextInt();
		Calendar reserveDateTime = ReservationMgr.getValidReservationDateTime();
		sc.close();
		
		TableMgr.showTableAvailability(reserveDateTime, numPax);
		
	}
	

	
	
}
