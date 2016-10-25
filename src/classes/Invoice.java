package classes;

import java.io.Serializable;
import java.util.Calendar;

public class Invoice implements Serializable{

	private static final long serialVersionUID = 103202593696268715L;
	private Order order;
	private int invoiceNumber;
	private double gst;
	private double price;
	private double totalPrice;
	private Calendar dateGenerated;
	public static final double GSTPERCENTAGE = 0.07;
	
	public Invoice(Order order){
		this.order = order;
		this.invoiceNumber = Calendar.getInstance().hashCode();
		this.price = order.calculateTotalOrderPrice();
		this.gst = GSTPERCENTAGE * this.price;
		this.totalPrice = this.price + this.gst;
		this.dateGenerated = Calendar.getInstance();
	}
	
	public Calendar getDateGenerated() { return dateGenerated; }
	public double getTotalPrice() { return totalPrice; }
	public Order getOrder() { return order; }
	
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
