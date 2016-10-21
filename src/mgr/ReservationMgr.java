package mgr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import classes.Reservation;
import classes.Staff;
import db.Restaurant;

public class ReservationMgr {

	public static ArrayList<Reservation> reservations = Restaurant.reservations;
	public static ArrayList<Reservation> settledReservations = Restaurant.settledReservations;
	
	public static void acceptReservation(Staff staff){
		
		int index = 0;
		Scanner sc = new Scanner (System.in);
		
		System.out.print("Select which reservation to accept: ");
		for(Reservation r : reservations){
			System.out.println("(" + index++ + ") Customer Name:" + r.getCustomerName() + 
												"    Contact: " + r.getCustomerContact() +
												"    Arrival time: " + r.getArrivalTime().getTime());
		}
		int choice = sc.nextInt();
		sc.close();
		
		try {
			Reservation reservation = reservations.get(choice);
			staff.acceptReservation(reservation);
			System.out.println("Reservation accepted.");
		}catch(IndexOutOfBoundsException e){
			System.out.println("Fail to accept reservation! (Invalid index provided");
		}
		
	}
	
	public static void makeReservation() {
		
		Scanner sc = new Scanner (System.in);

		int reservationID = Calendar.getInstance().hashCode();
		
		System.out.print("Enter customer name: "); 				String customerName = sc.nextLine();
		System.out.print("Enter customer contact number: "); 	int customerContact = sc.nextInt();
		System.out.print("Enter number of people: ");			int numPax = sc.nextInt();
		
		String date = "";
	    Date parsedDate = null;
		boolean validDate = false;
		Calendar arrivalTime = Calendar.getInstance();
		do{
		    System.out.println("Enter reservation datetime (dd/MM/yyyy hh:mm)");	date  = sc.nextLine();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		    try {
		    	parsedDate = dateFormat.parse(date);
		    } catch (ParseException e) {
		        System.out.println("Entered reservation date is not in the correct format!");
		        continue;
		    }

		    arrivalTime.setTime(parsedDate);
		    validDate = checkValidReservationDate(arrivalTime);

		} while(!validDate);

		// TODO: check table availability
		reservations.add(new Reservation(customerName, customerContact, numPax, reservationID, arrivalTime));
		
		sc.close();
		
	}

	public static boolean checkValidReservationDate(Calendar date){
		
		boolean validDate = false;
		
		Calendar now = Calendar.getInstance();
		
	    Calendar maxBookingDate = Calendar.getInstance();
	    maxBookingDate.add(Calendar.MONTH, Restaurant.bookingMthInAdvance);
	    
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
	    	if(date.before(now.getTime()))
	    		System.out.println("Reservation datetime cannot be in the past!");
	    	else if(date.after(maxBookingDate.getTime()))
	    		System.out.println("Reservation is for 1 month in advance only!");
	    	else if(!(date.after(AMStartCal) && date.before(AMEndCal)) || (date.after(PMStartCal) && date.before(PMEndCal)))
	    		System.out.println("Reservation must be within operation hours");
	    	else
	    		validDate = true;
	    		
		return validDate;
	}


}
