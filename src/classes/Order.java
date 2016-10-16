package classes;

import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;

public class Order {
	private Date dateTime;
	private String orderNumber;
	public ArrayList<OrderLineItem> orderList;
	public Staff staffCreated;
	public Invoice invoice;
	
	public Order(ArrayList<OrderLineItem> orderList, String orderNumber, Staff staffCreated){
		this.orderList = orderList;
		this.orderNumber = orderNumber;
		this.staffCreated = staffCreated;
		Calendar cal = Calendar.getInstance();
		this.dateTime = cal.getTime(); 
	}
	
	public ArrayList<OrderLineItem> getOrderList(){return orderList;}
	public void setOrderNo(ArrayList<OrderLineItem> orderList){this.orderList = orderList;}
	
	public Date getDateTime(){return dateTime;}
	
	public String getOrderNo(){return orderNumber;}
	public void setOrderNo(String orderNumber){this.orderNumber = orderNumber;}
	
	public Staff getStaffCreated(){return staffCreated;}
	public void setStaffCreated(Staff staffCreated){this.staffCreated = staffCreated;}
	
	//add to end of orderList array
	public void addOrderItem(OrderLineItem orderItem){orderList.add(orderItem);}
		
	public void removeOrderItem(){
		Scanner sc = new Scanner(System.in);
		
		System.out.println("What item would you like to remove from the order?");
		
		for (OrderLineItem orderItem : orderList)
			System.out.println(orderList.indexOf(orderItem) + ": " + orderItem.getMenuItem().getMenuName());

		int index = sc.nextInt();
		sc.close();
		
		try {
			String orderItemRemoved = orderList.get(index).toString();
			orderList.remove(index);
			System.out.println(orderItemRemoved + " removed from order."); 
		}catch(IndexOutOfBoundsException e){
			System.out.println("Order item removal failed! (Invalid index provided");
		}
	}
	
	public void generateInvoice(){invoice.printInvoice();}
	
}
