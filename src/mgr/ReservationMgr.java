package mgr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import classes.Reservation;
import classes.Staff;
import classes.Table;
import db.Restaurant;

public class ReservationMgr {

	private static Scanner sc = new Scanner (System.in);
	
	public static ArrayList<Reservation> reservations = Restaurant.reservations;
	public static ArrayList<Reservation> settledReservations = Restaurant.settledReservations;
	
	public static void acceptReservation(Staff staff){

		removeExpiredReservation();
		
		int index = 0;
		
		System.out.println("Select which reservation to accept: ");
		Calendar now = Calendar.getInstance();
		for(Reservation r : reservations){
			if(r.getArrivalTime().get(Calendar.DATE) == now.get(Calendar.DATE)){
			System.out.println("(" + index++ + ") Customer Name:" + r.getCustomerName() + 
												"    Contact: " + r.getCustomerContact() +
												"    Arrival time: " + r.getArrivalTime().getTime());
			}
		}
		int choice = sc.nextInt();
		
		try {
			Reservation reservation = reservations.get(choice);
			staff.acceptReservation(reservation);
			moveToSettledReservation(reservation);
			System.out.println("Reservation accepted.");
		}catch(IndexOutOfBoundsException e){
			System.out.println("Fail to accept reservation! (Invalid index provided)");
		}
		
	}
	
	public static void makeReservation() {
				
		System.out.print("Enter customer name: "); 				String customerName = sc.next();
		sc.nextLine(); // get dummy line
		System.out.print("Enter customer contact number: "); 	int customerContact = sc.nextInt();
		System.out.print("Enter number of people: ");			int numPax = sc.nextInt();
		sc.nextLine();	// get dummy line
		Calendar arrivalTime = getValidReservationDateTime();

		Table reserveTable = TableMgr.findReservationTable(arrivalTime,numPax);
		
		if(reserveTable != null){
			reservations.add(new Reservation(customerName, customerContact, numPax, arrivalTime, reserveTable));
			System.out.println("Reservation added.");
		}
				
	}

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
	
	public static void moveToSettledReservation(Reservation reservation){
		settledReservations.add(reservation);
		reservations.remove(reservation);
	}
	
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
	    
	    ///debugging/////////////////////////////////////////////////////////////////////////
	    //System.out.println(date.getTime());
	    //System.out.println(now.getTime());
	    //System.out.println(maxBookingDate.getTime());
	    //System.out.println(date.before(maxBookingDate.getTime()));
	    //System.out.println(date.after(maxBookingDate.getTime()));
	    //System.out.println(date.getTime().compareTo(maxBookingDate.getTime())>0);
	    //System.out.println(date.before(now));
	    //System.out.println(date.after(now));
	    //System.out.println(date.getTime().compareTo(now.getTime())<0);
	    //debugging///////////////////////////////////////////////////////////////////////////
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
