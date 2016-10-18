package classes;

import java.util.Date;
import java.util.Scanner;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Order implements Serializable{
	
	private static final long serialVersionUID = -9135686500512288865L;
	private Date dateTime;
	private ArrayList<OrderLineItem> orderLineItems;
	private Staff staffCreated;
	private Invoice invoice;
	private Table OrderTable;
	
	public Order(Staff staffCreated, Table OrderTable){
		this.orderLineItems = new ArrayList<OrderLineItem>();
		this.staffCreated = staffCreated;
		Calendar cal = Calendar.getInstance();
		this.dateTime = cal.getTime();
		this.OrderTable = OrderTable;
	}
	
	public Order(ArrayList<OrderLineItem> orderLineItems, String orderNumber, Staff staffCreated){
		this.orderLineItems = orderLineItems;
		this.staffCreated = staffCreated;
		Calendar cal = Calendar.getInstance();
		this.dateTime = cal.getTime(); 
	}
	
	public ArrayList<OrderLineItem> getorderLineItems(){return orderLineItems;}
	public Date getDateTime(){return dateTime;}
	public Staff getStaffCreated(){return staffCreated;}
	
	public void setOrderNo(ArrayList<OrderLineItem> orderLineItems){this.orderLineItems = orderLineItems;}
	public void setStaffCreated(Staff staffCreated){this.staffCreated = staffCreated;}
	
	//add to end of orderLineItems array
	public void addOrderItem(OrderLineItem orderItem){orderLineItems.add(orderItem);}
	
	public void addOrderItem(ArrayList<MenuItem> FoodMenu){
		int choice;
		int index = 0;
		Scanner sc = new Scanner(System.in);
		OrderLineItem orderItem;
		
		System.out.println("Select the food item to add to the order:");
		for(MenuItem menuItem : FoodMenu){
			System.out.println("(" + index + ") " + menuItem.getMenuName());
			index++;
		}
		choice = sc.nextInt();
		sc.close();
		
		try {
			String orderItemAdded = orderLineItems.get(choice).toString();
			orderItem = new OrderLineItem(FoodMenu.get(choice), FoodMenu.get(choice).getPrice());
			this.orderLineItems.add(orderItem);
			System.out.println(orderItemAdded + " added to order."); 
		}catch(IndexOutOfBoundsException e){
			System.out.println("Add order item failed! (Invalid index provided");
		}
		

	}
	
	public void removeOrderItem(){
		Scanner sc = new Scanner(System.in);
		
		System.out.println("What item would you like to remove from the order?");
		
		for (OrderLineItem orderItem : orderLineItems)
			System.out.println(orderLineItems.indexOf(orderItem) + ": " + orderItem.getMenuItem().getMenuName());

		int index = sc.nextInt();
		sc.close();
		
		try {
			String orderItemRemoved = orderLineItems.get(index).toString();
			this.orderLineItems.remove(index);
			System.out.println(orderItemRemoved + " removed from order."); 
		}catch(IndexOutOfBoundsException e){
			System.out.println("Order item removal failed! (Invalid index provided");
		}
	}
	
	public void generateInvoice(ArrayList<Invoice> invoices){
		this.invoice = new Invoice(this, invoices.size());
		invoices.add(this.invoice);
	}
	
	public String toString(){
		String printOrderString = "";
		for(OrderLineItem o : orderLineItems){
			printOrderString += o.getMenuItem().getMenuName() + "    " + o.getChargedPrice() + "\n";
		}
		return printOrderString;
	}
	
}
