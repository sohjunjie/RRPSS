package ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import classes.Staff;
import classes.Table;
import mgr.ReservationMgr;
import mgr.TableMgr;

public class ReservationUI {

	private static Scanner sc = new Scanner(System.in);
	
	public static void menuShowReservationOptions(Staff staff){

		int choice;
		
        do {
            System.out.println("\nSelect a choice: ");
            System.out.println("(1) Show table availability");
            System.out.println("(2) Walk in dining");
            System.out.println("(3) Make reservation");
            System.out.println("(4) Accept reservation");
            System.out.println("(5) Remove reservation");
            System.out.println("(6) Show reservations");
            System.out.println("(7) Back");
        	System.out.println();
        	System.out.print("    Enter the number of your choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                		showTableAvailability();
                        break;
                case 2:
                		ReservationMgr.makeWalkInReservation(staff);
                		break;
                case 3:
                		makeReservationUI();
                        break;
                case 4:
                		ReservationMgr.acceptReservation(staff);
                    	break;
                case 5:
                		ReservationMgr.removeReservation();
                		break;
                case 6:
                		ReservationMgr.showReservations();
                		break;
                case 7:
            }

        } while (choice < 7);

	}

	
	/**
	 * Prompts user for reservation details and make a 
	 * reservation.
	 */
	private static void makeReservationUI(){
		
		System.out.print("Enter customer name: "); 				String customerName = sc.nextLine();
		System.out.print("Enter customer contact number: "); 	int customerContact = sc.nextInt();
		sc.nextLine();	// get dummy line
		System.out.print("Enter number of people: "); 			int numPax = sc.nextInt();
		sc.nextLine();	// get dummy line
		Calendar arrivalTime = ReservationMgr.getValidReservationDateTime();
		
		ReservationMgr.makeReservation(customerName, customerContact, numPax, arrivalTime);

	}
	
	/**
	 * Prompt user for a reservation datetime and #pax
	 * and display available tables for reservation.
	 */
	private static void showTableAvailability(){
		
		System.out.print("Enter number of people: "); int numPax = sc.nextInt();
		sc.nextLine();	// get dummy line
		Calendar reserveDateTime = ReservationMgr.getValidReservationDateTime();
		
		ArrayList<Table> availableTables = TableMgr.checkAvailableTables(reserveDateTime, numPax);
		if(availableTables == null){
			System.out.println("No tables available for reservation on datetime " + reserveDateTime.getTime());
			return;
		}
		
		System.out.println("The following tables are available for Pax no " + numPax + " and datetime " + reserveDateTime.getTime());
		System.out.println("Table number			Capacity");
		for(Table t : availableTables)
			System.out.println(t.getTableId() + "				" + t.getCapacity());

	}
	
	
}
