package classes;

import java.io.Serializable;
import java.util.Calendar;

// TODO: JAVADOC

/**
 * Represents an invoice generated from an order.
 * @author michelle
 * @version 1.0
 * @since 2016-10-24
 */
public class Invoice implements Serializable{

	private static final long serialVersionUID = 103202593696268715L;
	private Order order;
	private int invoiceNumber;
	private double gst;
	private double price;
	private double totalPrice;
	public static final double GSTPERCENTAGE = 0.07;
	
	public Invoice(Order order){
		this.order = order;
		this.invoiceNumber = Calendar.getInstance().hashCode();
		this.price = order.calculateTotalOrderPrice();
		this.gst = GSTPERCENTAGE * this.price;
		this.totalPrice = this.price + this.gst;
	}
	
	public void printInvoice(){
		System.out.println("Date & Time: " + this.order.getDateTime());
		System.out.println("Invoice Number: " + this.invoiceNumber);
		System.out.println("Items ordered: " + this.order);
		System.out.println("Subotal: " + price);
		System.out.println("GST: " + gst);
		System.out.println("Total: " + totalPrice);
	}

}
