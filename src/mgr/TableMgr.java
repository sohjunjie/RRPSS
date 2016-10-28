package mgr;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import classes.Reservation;
import classes.Table;
import classes.Table.TableStatus;
import db.Restaurant;

public class TableMgr {

	public static ArrayList<Table> tables = Restaurant.tables;
	private static Scanner sc = new Scanner(System.in);
	
	
	public static Table findReservationTable(Calendar reserveDateTime, int numPax){
		
		ArrayList<Table> availableTables = checkAvailableTables(reserveDateTime, numPax);
		if(availableTables == null){
			System.out.println("No tables available for reservation on datetime " + reserveDateTime.getTime());
			return null;
		}
		
		showTableAvailability(availableTables, reserveDateTime, numPax);
		System.out.println("Please enter selected table number.");
		
		int choice = sc.nextInt();
		Table retTable = null;
		
		do{for(Table t : availableTables){
				if (t.getTableId()==choice){
					retTable = t;	
					break;}
			}		
			if(retTable!=null){break;}
			System.out.print("Invalid table number! Please enter again: ");
			choice = sc.nextInt();
		}while(retTable==null);

		return retTable;
	}
	
	
	public static void showTableAvailability(ArrayList<Table> availableTables, Calendar reserveDateTime, int numPax){

		System.out.println("The following tables are available for Pax no " + numPax + " and datetime " + reserveDateTime.getTime());
		System.out.println("Table number			Capacity");
		
		for(Table t : availableTables)
			System.out.println(t.getTableId() + "				" + t.getCapacity());

	}
	
	
	// TODO: improve readability of checkAvailableTables code
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
				for(Reservation r : t.getReserveBy()){
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
