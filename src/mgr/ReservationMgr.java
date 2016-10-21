package mgr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import classes.Reservation;
import db.Restaurant;

public class ReservationMgr {

	public static ArrayList<Reservation> reservations = Restaurant.reservations;
	public static ArrayList<Reservation> settledReservations = Restaurant.settledReservations;
	
	public static void makeReservation() {
		
		Scanner sc = new Scanner (System.in);

		int reservationID = Calendar.getInstance().hashCode();
		
		System.out.print("Enter customer name: "); 				String customerName = sc.nextLine();
		System.out.print("Enter customer contact number: "); 	int customerContact = sc.nextInt();
		System.out.print("Enter number of people: ");			int numPax = sc.nextInt();
		
		String date = "";
	    Date parsedDate = null;
		boolean invalidDate = true;
		Calendar now;
		do{
		    System.out.println("Enter reservation datetime (dd/MM/yyyy hh:mm)");	date  = sc.nextLine();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		    try {
		    	parsedDate = dateFormat.parse(date);
		    } catch (ParseException e) {
		        e.printStackTrace();
		    }
		    
		    now = Calendar.getInstance();
		    if(parsedDate != null)
		    	if(parsedDate.before(now.getTime()))
		    		System.out.println("Reservation datetime cannot be in the past!");
		    	else{
		    		now.add(Calendar.MONTH, 1);
		    		if(parsedDate.after(now.getTime()))
		    			System.out.println("Reservation is for 1 month in advance only!");
		    		else
		    			invalidDate = false;
		    	}

		}while(invalidDate);

		Calendar arrivalTime = Calendar.getInstance();
		arrivalTime.setTime(parsedDate);
		reservations.add(new Reservation(customerName, customerContact, numPax, reservationID, arrivalTime));
		
		sc.close();
		
	}
	
	
}
