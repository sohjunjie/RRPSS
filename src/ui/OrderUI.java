package ui;

import java.util.Scanner;

import classes.Order;
import classes.Staff;

public class OrderUI {

	public static void menuShowOrderOptions(Order order, Staff thisStaff){
		
		int choice;
		Scanner sc = new Scanner(System.in);
		
        do {
            System.out.println("\nSelect a choice: ");
            System.out.println("(1) Take order");
            System.out.println("(2) Remove order line item");
            System.out.println("(3) Print invoice");
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
	
	public static void takeOrder(Order order){
		
	}
	
	public static void removeOrderItem(Order order){
		
	}
	
	public void printInvoice(Order order){
		
	}
	
}
