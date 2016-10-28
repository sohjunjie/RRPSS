package ui;

import java.util.Scanner;

import classes.Order;
import db.Restaurant;

/**
 * Represents the UI displayed to the user when creating, changing or finalising an order.
 * @author Sean
 * @version 1.0
 * @since 2016-10-28
 */
public class OrderUI {

	private static Scanner sc = new Scanner(System.in);

	/**
	 * Display options user can perform on an order.
	 * User can take order, remove item from an order,
	 * or print the order's invoice.
	 */	
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
	
	/**
	 * Adds order line item to order
	 * @param order Order that is added to
	 */
	public static void takeOrder(Order order){
		order.addOrderItem();
	}
	
	/**
	 * Remove order line item from order
	 * @param order Order that is removed from
	 */
	public static void removeOrderItem(Order order){
		order.removeOrderItem();
	}
	
	/**
	 * Generates invoice for an order and prints the invoice.
	 * @param order Order that invoice is generated for
	 */
	public static void printInvoice(Order order){
		if(order.getInvoice() == null){
			
			order.generateInvoice();
			Restaurant.invoices.add(order.getInvoice());
		}
		order.getInvoice().printInvoice();
	}

}
