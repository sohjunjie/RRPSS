package mgr;

import java.util.ArrayList;
import java.util.Scanner;

import classes.Order;
import classes.Staff;
import db.Restaurant;
import ui.OrderUI;

public class OrderMgr {

	private static Scanner sc = new Scanner(System.in);
	public static ArrayList<Order> orders = Restaurant.orders;
	
	public static void editPendingOrder(){
		
		if(orders.size() == 0){ 
			System.out.println("No orders available to edit!");
			return; 
		}
		
		int index = 0;
		Order order;
		
		System.out.println("Select an order.");
		for(Order o : orders){
			System.out.println("(" + index++ + ") " + o.getOrderID());
		}
    	System.out.print("    Enter the number of your choice: ");
		int choice = sc.nextInt();
		
		try {
			order = orders.get(choice);
			OrderUI.menuShowOrderOptions(order);
		}catch(IndexOutOfBoundsException e){
			System.out.println("Invalid index entered!");
		}
		
	}
	
}
