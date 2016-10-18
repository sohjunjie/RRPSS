package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Invoice implements Serializable{

	private static final long serialVersionUID = 103202593696268715L;
	private Order order;
	private long invoiceNumber;
	private double gst;
	private double price;
	private double totalPrice;
	public static final double GSTPERCENTAGE = 0.07;
	
	public Invoice(Order order, long invoiceNumber){
		this.order = order;
		this.invoiceNumber = invoiceNumber;
		
		ArrayList<OrderLineItem> orderLineItems = order.getorderLineItems();
		for(OrderLineItem o : orderLineItems)
			this.price += o.getMenuItem().getPrice();

		this.gst = GSTPERCENTAGE * this.price;
		this.totalPrice = price + gst;
		}
	
	public void printInvoice(){
		System.out.println("Date & Time: " + this.order.getDateTime());
		System.out.println("Invoice Number: " + this.invoiceNumber);
		// Converts the orderList array to a string and then prints it out
		System.out.println("Items ordered: " + this.order);
		System.out.println("Subotal: " + price);
		System.out.println("GST: " + gst);
		System.out.println("Total: " + totalPrice);
	}
}
