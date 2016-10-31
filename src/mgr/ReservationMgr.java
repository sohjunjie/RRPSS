
package mgr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import classes.Order;
import classes.Reservation;
import classes.Staff;
import classes.Table;
import db.Restaurant;

/**
 * Reservation Manager class for user to create, accept, and
 * cancel reservations.
 * @author soh jun jie
 * @version 1.0
 * @since 2016-10-31
 */
public class ReservationMgr {

	private static Scanner sc = new Scanner (System.in);
	
	private static ArrayList<Reservation> reservations = Restaurant.reservations;
	private static ArrayList<Reservation> settledReservations = Restaurant.settledReservations;
	
	/**
	 * Show all reservations for the next month
	 */
	public static void showReservations(){
		removeExpiredReservation();
		System.out.println("Showing reservations for the next 30 days: ");
		int index = 0;
		for(Reservation r : reservations){
			printReservation(index,r);
			index++;}
	}
	
	/**
	 * Allow the user to accept a reservation and create
	 * an order from the reservation
	 * @param staff Staff accepting the reservation
	 */
	public static void acceptReservation(Staff staff){
		
		Calendar now = Calendar.getInstance();
		
		removeExpiredReservation();
		boolean AMSession;
		
		AMSession = (now.get(Calendar.HOUR) < Restaurant.AMEndTime);
		int index = 0;
		System.out.println("Select which reservation to accept: ");
		for(Reservation r : reservations)
			if(now.get(Calendar.DATE) == r.getArrivalTime().get(Calendar.DATE))
				if(AMSession == (r.getArrivalTime().get(Calendar.HOUR) < Restaurant.AMEndTime)){
					printReservation(index,r);
					index++;}

		int choice = sc.nextInt();
		
		try {
			Reservation reservation = reservations.get(choice);
			Order newOrder = staff.acceptReservation(reservation);
			Restaurant.orders.add(newOrder);
			moveToSettledReservation(reservation);
			System.out.println("Reservation accepted.");
		}catch(IndexOutOfBoundsException e){
			System.out.println("Fail to accept reservation! (Invalid index provided)");
		}
		
	}
	
	/**
	 * Print selected reservation on screen
	 * @param index Index number of reservation to be printed
	 * @param r Reservation to be printed
	 */
	public static void printReservation(int index, Reservation r){
		System.out.println("(" + index + ") Customer Name:" + r.getCustomerName() +
				"    Contact: " + r.getCustomerContact() +
				"    Arrival time: " + r.getArrivalTime().getTime()+
				"	 Reservation ID: "+ r.getReservationID()+
				"	 Table ID: "+ r.getReserveTable().getTableId());
		
	}
	
	/**
	 * Allow user to remove a reservation
	 * @param staff Staff removing the reservation
	 */
	public static void removeReservation(){
		removeExpiredReservation();
		System.out.println("Select which reservation to remove: ");
		int index = 0;
		for(Reservation r : reservations){
			printReservation(index,r);
			index++;}
		int choice = sc.nextInt();
		
		try {
			Reservation reservation = reservations.get(choice);
			moveToSettledReservation(reservation);
			System.out.println("Reservation removed.");
		}catch(IndexOutOfBoundsException e){
			System.out.println("Fail to remove reservation! (Invalid index provided)");
		}
	}
	
	/**
	 * Allow user to make a reservation
	 */
	public static void makeReservation() {
				
		System.out.print("Enter customer name: "); 				String customerName = sc.next();
		sc.nextLine(); // get dummy line
		System.out.print("Enter customer contact number: "); 	int customerContact = sc.nextInt();
		System.out.print("Enter number of people: "); 			int numPax = sc.nextInt();
		sc.nextLine();	// get dummy line
		Calendar arrivalTime = getValidReservationDateTime();

		Table reserveTable = TableMgr.findReservationTable(arrivalTime,numPax);
		
		if(reserveTable != null){
			reservations.add(new Reservation(customerName, customerContact, numPax, arrivalTime, reserveTable));
			System.out.println("Reservation added.");
		}
				
	}

	/**
	 * Remove reservation that are more than 30min 
	 * past arrival time
	 */
	public static void removeExpiredReservation(){
		
		Calendar expiredDateTime = Calendar.getInstance();
		expiredDateTime.add(Calendar.MINUTE, -30);
		Reservation r;
		
		for (int i = reservations.size()-1; i >= 0; i--){
			r = reservations.get(i);
		    if(r.getArrivalTime().before(expiredDateTime))
				moveToSettledReservation(r);
		 }

	}
	
	/**
	 * Move pending reservation to settled
	 * @param reservation Reservation to move to settled
	 */
	public static void moveToSettledReservation(Reservation reservation){
		reservation.getReserveTable().removeTableReservation(reservation);
		settledReservations.add(reservation);
		reservations.remove(reservation);
	}
	
	/**
	 * Get a valid reservation date time from the user
	 * @return Valid reservation date time
	 */
	public static Calendar getValidReservationDateTime(){

		String date = "";
	    Date parsedDate = null;
	    SimpleDateFormat dateFormat = null;
		boolean validDate = false;		
		Calendar arrivalTime = Calendar.getInstance();
		
		do{
		    System.out.print("Enter reservation datetime (dd/MM/yyyy hh:mm)");	date  = sc.nextLine();
		    dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		    try {
		    	parsedDate = dateFormat.parse(date);
		    } catch (ParseException e) {
		        System.out.println("Entered reservation date is not in the correct format!");
		        continue;
		    }

		    arrivalTime.setTime(parsedDate);
		    validDate = checkValidReservationDate(arrivalTime);

		} while(!validDate);
				
		return arrivalTime;
	}
	
	/**
	 * Check if a reservation datetime is valid
	 * according to restaurant operation hours
	 * @param date Reservation datetime to check
	 * @return True-False value indicating valid or invalid reservation datetime
	 */
	public static boolean checkValidReservationDate(Calendar date){
		
		boolean validDate = false;
		
		Calendar now = Calendar.getInstance();
		
	    Calendar maxBookingDate = Calendar.getInstance();
	    maxBookingDate.add(Calendar.DAY_OF_MONTH, 30);
	    
	    Calendar AMStartCal = (Calendar) date.clone();
	    AMStartCal.set(Calendar.HOUR_OF_DAY, Restaurant.AMStartTime);
	    AMStartCal.set(Calendar.MINUTE, 0);
	    AMStartCal.set(Calendar.SECOND, 0);
	    AMStartCal.set(Calendar.MILLISECOND, 0);
	    
	    Calendar AMEndCal = (Calendar) date.clone();
	    AMEndCal.set(Calendar.HOUR_OF_DAY, Restaurant.AMEndTime);
	    AMEndCal.set(Calendar.MINUTE, 0);
	    AMEndCal.set(Calendar.SECOND, 0);
	    AMEndCal.set(Calendar.MILLISECOND, 0);
	    
	    Calendar PMStartCal = (Calendar) date.clone();
	    PMStartCal.set(Calendar.HOUR_OF_DAY, Restaurant.PMStartTime);
	    PMStartCal.set(Calendar.MINUTE, 0);
	    PMStartCal.set(Calendar.SECOND, 0);
	    PMStartCal.set(Calendar.MILLISECOND, 0);
	    
	    Calendar PMEndCal = (Calendar) date.clone();
	    PMStartCal.set(Calendar.HOUR_OF_DAY, Restaurant.PMEndTime);
	    PMStartCal.set(Calendar.MINUTE, 0);
	    PMStartCal.set(Calendar.SECOND, 0);
	    PMStartCal.set(Calendar.MILLISECOND, 0);
	    
	    if(date != null)
	    	if(date.before(now))
	    		System.out.println("Reservation datetime cannot be in the past!");
	    	else if(date.after(maxBookingDate))
	    		System.out.println("Reservation is for 1 month in advance only!");
	    	else if(!(date.after(AMStartCal) && date.before(AMEndCal)) || (date.after(PMStartCal) && date.before(PMEndCal)))
	    		System.out.println("Reservation must be within operation hours");
	    	else
	    		validDate = true;	
		return validDate;
	}
	

}
