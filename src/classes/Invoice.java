package classes;

public class Invoice {
	private Order order;
	private String invoiceNumber;
	private double gst;
	private double price;
	private double totalPrice;
	public static final double GSTPERCENTAGE = 0.07;
	
	public Invoice(Order order, String invoiceNumber){
		this.order = order;
		this.invoiceNumber = invoiceNumber;
		// Price before GST calculated by summing out prices of all menuItems in orderList[].
		for (int i = 0; i < order.orderLineItems.size(); i++){
			this.price += order.orderLineItems.get(i).getMenuItem().getPrice();
			}
		this.gst = GSTPERCENTAGE * price;
		this.totalPrice = price + gst;	
		}
	
	public void printInvoice(){
		System.out.println("Date & Time: " + order.getDateTime());
		System.out.println("Invoice Number: " + invoiceNumber);
		// Converts the orderList array to a string and then prints it out
		System.out.println("Items ordered: " + order.orderLineItems.toString());
		System.out.println("Subotal: " + price);
		System.out.println("GST: " + gst);
		System.out.println("Total: " + totalPrice);
	}
}
