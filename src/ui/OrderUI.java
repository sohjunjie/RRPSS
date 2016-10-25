package ui;

import java.util.Scanner;

import classes.Order;
import db.Restaurant;

public class OrderUI {

	private static Scanner sc = new Scanner(System.in);
	
	public static void menuShowOrderOptions(Order order){
		
		int choice;
		
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
                		takeOrder(order);
                        break;
                case 2:
                		removeOrderItem(order);
                        break;
                case 3:
                		printInvoice(order);
                    	break;
                case 4:
            }

        } while (choice < 4);
        
	}
	
	public static void takeOrder(Order order){
		order.addOrderItem();
	}
	
	public static void removeOrderItem(Order order){
		order.removeOrderItem();
	}
	
	public static void printInvoice(Order order){
		if(order.getInvoice() == null){
			
			order.generateInvoice();
			Restaurant.invoices.add(order.getInvoice());
		}
		order.getInvoice().printInvoice();
	}

}
