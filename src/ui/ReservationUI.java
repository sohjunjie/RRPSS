package ui;

import java.util.Scanner;

import classes.Staff;
import mgr.ReservationMgr;

public class ReservationUI {

	public static void menuShowReservationOptions(Staff staff){

		int choice;
		Scanner sc = new Scanner(System.in);
		
        do {
            System.out.println("\nSelect a choice: ");
            System.out.println("(1) Show availability");
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
                		makeReservation();
                        break;
                case 3:
                		acceptReservation(staff);
                    	break;
                case 4:
            }

        } while (choice < 4);
		
        sc.close();
        
	}

	public static void showTableAvailability(){
		
	}
	
	public static void makeReservation(){
		ReservationMgr.makeReservation();
	}
	
	public static void acceptReservation(Staff staff){
		ReservationMgr.acceptReservation(staff);
	}
	
	
}
