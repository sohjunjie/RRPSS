package ui;

import java.util.Scanner;

import classes.Order;
import db.Restaurant;
import mgr.OrderMgr;

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
            System.out.println("(1) Show order details");
            System.out.println("(2) Take order");
            System.out.println("(3) Remove order line item");
            System.out.println("(4) Print invoice");
            System.out.println("(5) Back");
        	System.out.println();
        	System.out.print("    Enter the number of your choice: ");
            choice = sc.nextInt(); sc.nextLine(); // get dummy line
            
            switch (choice) {
            	case 1:
            			System.out.println(order);
            			break;
                case 2:
                		takeOrder(order);
                        break;
                case 3:
                		removeOrderItem(order);
                        break;
                case 4:
                		printInvoice(order);
                    	break;
                case 5:
            }

        } while (choice < 4);
        
	}
	
	/**
	 * Adds order line item to order
	 * @param order Order that is added to
	 */
	private static void takeOrder(Order order){
		order.addOrderItem();
	}
	
	/**
	 * Remove order line item from order
	 * @param order Order that is removed from
	 */
	private static void removeOrderItem(Order order){
		order.removeOrderItem();
	}
	
	/**
	 * Generates invoice for an order and prints the invoice.
	 * @param order Order that invoice is generated for
	 */
	private static void printInvoice(Order order){
		if(order.getInvoice() == null){
			order.generateInvoice();
			Restaurant.invoices.add(order.getInvoice());
			OrderMgr.moveToSettledOrder(order);
		}
		order.getInvoice().printInvoice();
	}

}
