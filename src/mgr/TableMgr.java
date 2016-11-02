package mgr;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import classes.Reservation;
import classes.Table;
import classes.Table.TableStatus;
import db.Restaurant;

/**
 * Table manager class allowing user to find
 * reservation table
 * availability
 * @author wang xing yue
 * @version 1.0
 * @since 2016-10-31
 */
public class TableMgr {

	private static ArrayList<Table> tables = Restaurant.tables;
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * Find a reservation that fits the reservation datetime
	 * and number of people.
	 * @param reserveDateTime Datetime to reserve the table for
	 * @param numPax Number of people dining
	 * @return Reservation table to reserve
	 */
	public static Table findReservationTable(Calendar reserveDateTime, int numPax){
		
		ArrayList<Table> availableTables = checkAvailableTables(reserveDateTime, numPax);
		if(availableTables.size() == 0 || availableTables == null){
			System.out.println("No tables available for reservation on datetime " + reserveDateTime.getTime());
			return null;
		}
		
		// GET SMALLEST CAPACITY RESERVATION TABLE
		Table retTable = availableTables.get(0);
		for(Table t : availableTables)
			if(t.getCapacity() < retTable.getCapacity())
				retTable = t;

		return retTable;
	}
	
	
	/**
	 * Find all tables that matches satisfy reservation date time and
	 * number of people
	 * @param reserveDateTime Datetime of reservation
	 * @param numPax Number of people
	 * @return ArrayList of tables satisfying reservation criteria
	 */
	public static ArrayList<Table> checkAvailableTables(Calendar reserveDateTime, int numPax){
		
		ReservationMgr.removeExpiredReservation();
		ArrayList<Table> availableTables = new ArrayList<Table>();
		
		boolean isLunchSession=false;						//check lunch or dinner session for reserveDateTime
		if(reserveDateTime.get(Calendar.HOUR) < Restaurant.AMEndTime){isLunchSession=true;}
		boolean alsoLunchSession;							//check lunch or dinner session for existing reservation
		boolean available;
		
		for(Table t : tables){
			available = true;
			alsoLunchSession=false;
			if(t.getCapacity() >= numPax && t.getStatus()!=TableStatus.OCCUPIED){
				for(Reservation r : t.getReservedBy()){
					if(r.getArrivalTime().get(Calendar.MONTH)==reserveDateTime.get(Calendar.MONTH)){	//check same month
						if(r.getArrivalTime().get(Calendar.DATE)==reserveDateTime.get(Calendar.DATE)){	//check same date
							if(r.getArrivalTime().get(Calendar.HOUR)<15){alsoLunchSession=true;}
							if(isLunchSession==alsoLunchSession){										//check same session
								available=false;
								break;
							}
						}
					}
				}
			}else{available=false;}
			if (available == true){availableTables.add(t);}
		}
		
		return availableTables;
	
	}
	
	
}
