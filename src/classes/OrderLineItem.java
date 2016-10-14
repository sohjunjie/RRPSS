package classes;

public class OrderLineItem {

	private MenuItem orderItem;
	
	public OrderLineItem(MenuItem orderItem) { this.orderItem = orderItem; }
	public MenuItem getMenuItem(){ return this.orderItem; }
	
}
