package ui;

import java.util.Scanner;

public class ReservationUI {

	public static void menuShowReservationOptions(){

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
                        break;
                case 2:
                        break;
                case 3:
                    	break;
                case 4:
            }

        } while (choice < 4);
		
        sc.close();
        
	}

	public static void showAvailability(){
		
	}
	
	public static void makeReservation(){
		// show make reservation menu
	}
	
	public static void acceptReservation(){
		// 0. pre-processing -- move expired reservations to settledReservations
		// 1. show list of pending reservations
		// 2. select a reservation and show reservation interface
	}
	
	
}
