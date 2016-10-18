package classes;

import java.io.Serializable;

public class OrderLineItem implements Serializable{

	private static final long serialVersionUID = -1249371886974645374L;
	private MenuItem orderItem;
	private double chargedPrice;
	public OrderLineItem(MenuItem orderItem, double chargedPrice){ 
		this.orderItem = orderItem;
		this.chargedPrice = chargedPrice;
	}
	public MenuItem getMenuItem(){ return this.orderItem; }
	public double getChargedPrice(){ return this.chargedPrice; }
	
}
