package classes;

import java.util.Date;
import java.util.Scanner;

import classes.Table.TableStatus;
import db.Restaurant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Order implements Serializable{
	
	private static final long serialVersionUID = -9135686500512288865L;
	private int							orderID;
	private ArrayList<OrderLineItem> 	orderLineItems;
	private Staff 						createdByStaff;
	private Reservation					fromReservation;
	private Date 						dateTime;
	private Invoice 					invoice;

	public Order(Staff createdByStaff, Reservation fromReservation){
		this.orderID			= Calendar.getInstance().hashCode();
		this.orderLineItems		= new ArrayList<OrderLineItem>();
		this.createdByStaff 	= createdByStaff;
		this.fromReservation 	= fromReservation;
		this.dateTime 			= Calendar.getInstance().getTime();
		this.invoice			= null;
	}
	
	public int getOrderID(){ return this.orderID; }
	public ArrayList<OrderLineItem> getorderLineItems(){return this.orderLineItems;}
	public Date getDateTime(){return this.dateTime;}
	public Staff getStaffCreated(){return this.createdByStaff;}
	public Invoice getInvoice(){ return this.invoice; }
	
	public void setOrderLineItems(ArrayList<OrderLineItem> orderLineItems){this.orderLineItems = orderLineItems;}
	
	public void addOrderItem(OrderLineItem orderItem){
		if(this.invoice != null) return;	//lock order for editing when invoice already generated
		orderLineItems.add(orderItem);
	}
	
	public void addOrderItem(){
		
		if(this.invoice != null) return;	//lock order for editing when invoice already generated
		
		int choice;
		int index = 0;
		Scanner sc = new Scanner(System.in);
		OrderLineItem orderItem;
		ArrayList<MenuItem> foodMenu = Restaurant.foodMenu;
		
		System.out.println("\nSelect the food item to add to the order:");
		for(MenuItem menuItem : foodMenu)
			System.out.println("(" + index++ + ") " + menuItem.getMenuName());

    	System.out.print("    Enter the number of your choice: ");
		choice = sc.nextInt();
		sc.close();
		
		try {
			String orderItemAdded = orderLineItems.get(choice).toString();
			orderItem = new OrderLineItem(foodMenu.get(choice));
			this.orderLineItems.add(orderItem);
			System.out.println(orderItemAdded + " added to order."); 
		}catch(IndexOutOfBoundsException e){
			System.out.println("Add order item failed! (Invalid index provided");
		}
		

	}
	
	public void removeOrderItem(){
		
		if(this.invoice != null) return;	//lock order for editing when invoice already generated
		
		int choice, index;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nWhat item would you like to remove from the order?");
		
		index = 0;
		for (OrderLineItem orderItem : orderLineItems)
			System.out.println(index++ + ": " + orderItem.getMenuItem().getMenuName());

    	System.out.print("    Enter the number of your choice: ");
		choice = sc.nextInt();
		sc.close();
		
		try {
			String orderItemRemoved = orderLineItems.get(choice).toString();
			this.orderLineItems.remove(choice);
			System.out.println(orderItemRemoved + " removed from order."); 
		}catch(IndexOutOfBoundsException e){
			System.out.println("Order item removal failed! (Invalid index provided");
		}
	}
	
	public void generateInvoice(){
		
		if(this.invoice != null) return;	//lock order for editing when invoice already generated
		this.invoice = new Invoice(this);
		this.fromReservation.getReserveTable().setStatus(TableStatus.VACATED);

	}
	
	public double calculateTotalOrderPrice(){
		double retPrice = 0;
		for(OrderLineItem o : this.orderLineItems)
			retPrice += o.getMenuItem().getPrice();
		return retPrice;
	}	
	
	public String toString(){
		String printOrderString = "";
		for(OrderLineItem o : orderLineItems){
			printOrderString += o.getMenuItem().getMenuName() + "    " + o.getChargedPrice() + "\n";
		}
		return printOrderString;
	}
	
}
