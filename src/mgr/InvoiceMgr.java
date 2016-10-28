package mgr;

import java.util.ArrayList;
import classes.Invoice;
import classes.MenuItem;
import classes.OrderLineItem;
import db.Restaurant;
import java.util.Calendar;

public class InvoiceMgr {
	
	public static ArrayList<Invoice> invoices = Restaurant.invoices;
	public static ArrayList<MenuItem> foodMenu = Restaurant.foodMenu;
	
	public static void printSalesRevenueReport(int month, int year){
		double totalRevenue = totalRevenue(month, year);
		int[] productSales = productStatistics(month, year);
		System.out.println("\nSales Revenue Report for Number " + month + " of year " + year);
		System.out.println("Total Sales Revenue: " + totalRevenue);
		System.out.println("Total Number of Food Products: \n");
		for (MenuItem fd: foodMenu){
			System.out.println(fd.getMenuName() + ": " + productSales[foodMenu.indexOf(fd)]);
		}
	}
	
	private static double totalRevenue(int month, int year){
		int j=0;
		double total = 0;
		for (Invoice i: invoices) {
			if ((i.getDateGenerated().get(Calendar.MONTH) + 1 == month) && (i.getDateGenerated().get(Calendar.YEAR) == year))
				total += i.getTotalPrice();
		}
		return total;
	}
	
	private static int[] productStatistics(int month, int year){
		
		int[] productSales = new int[foodMenu.size()];

		for (Invoice i: invoices) {
			if ((i.getDateGenerated().get(Calendar.MONTH) + 1 == month) && (i.getDateGenerated().get(Calendar.YEAR) == year))
				for (OrderLineItem ol: i.getOrder().getOrderLineItems()){
					productSales[foodMenu.indexOf(ol.getMenuItem())] += 1;
				}
		}
		
		return productSales;
		
	}
	
}
