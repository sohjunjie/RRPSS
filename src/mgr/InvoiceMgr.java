package mgr;

import java.util.ArrayList;
import classes.Invoice;
import classes.MenuItem;
import classes.OrderLineItem;
import db.Restaurant;
import java.util.Calendar;

/**
 * Invoice Manager class that manages generation of 
 * report statistics from order invoices.
 * @author user stella
 * @version 1.0
 * @since 2016-10-31
 */
public class InvoiceMgr {
	
	private static ArrayList<Invoice> invoices = Restaurant.invoices;
	private static ArrayList<MenuItem> foodMenu = Restaurant.foodMenu;
	
	/**
	 * Print the sales revenue report for a specified month
	 * and year. Revenue and quantity sold for each product
	 * for that time period is reported.
	 * @param month Month for statistics to be reported
	 * @param year Year for statistics to be reported
	 */
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
	
	/**
	 * Find the total revenue made for a reporting
	 * period
	 * @param month Month for statistics to be reported
	 * @param year Year for statistics to be reported
	 * @return Total revenue for reporting period
	 */
	private static double totalRevenue(int month, int year){
		int j=0;
		double total = 0;
		for (Invoice i: invoices) {
			if ((i.getDateGenerated().get(Calendar.MONTH) + 1 == month) && (i.getDateGenerated().get(Calendar.YEAR) == year))
				total += i.getTotalPrice();
		}
		return total;
	}
	
	/**
	 * Find the quantity sold for each menu item in
	 * a reporting period
	 * @param month Month for statistics to be reported
	 * @param year Year for statistics to be reported
	 * @return Array containing quantity sold for each menu item
	 */
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
