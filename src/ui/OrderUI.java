package ui;

import java.util.Scanner;

import classes.Order;
import db.Restaurant;

/**
 * Represents the UI displayed to the user when creating, changing or finalising orders.
 * @author Sean
 *
 */

public class OrderUI {

	private static Scanner sc = new Scanner(System.in);
	

	/**
	 * While loop that scans user input to decide which operation to execute.
	 * Can create order, remove order line item from order or create invoice.
	 * Terminate by entering number greater than 4.
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
	 * Generates invoice for the order if it does not exist.
	 * Adds the invoice to the restaurant's ArrayList of all of its invoices.
	 * Prints the invoice showing date and time, invoice number, all items ordered,
	 * price of the items, GST applied and total price to be paid.
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
