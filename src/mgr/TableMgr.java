package mgr;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import classes.Reservation;
import classes.Table;
import classes.Table.TableStatus;
import db.Restaurant;

public class TableMgr {

	public static ArrayList<Table>			tables = Restaurant.tables;
	
	// TODO: Find list of table for reservation given datetime and allow
	//		 user to select the table to reserve
	public static Table findReservationTable(Calendar reserveDateTime, int numPax){
		
		ArrayList<Table> availableTables = checkTableAvailability(reserveDateTime, numPax);
		if(availableTables == null){return null;}			///////??????? question-not sure if empty array = null
		
		Scanner sc = new Scanner(System.in);
		showTableAvailability(reserveDateTime, numPax);
		System.out.println("Please enter the chosen Selection No.");
		int choice = sc.nextInt();
		while(choice<1||choice>availableTables.size()+1){
			System.out.println("Invalid Selection No.");			//error message
			System.out.println("Please enter the chosen Selection No.");
			choice = sc.nextInt();
		}
		sc.close();
		return (availableTables.get(choice+1));
		
	}
	
	
	// TODO: print list of table available for given date, and numPax
	public static void showTableAvailability(Calendar reserveDateTime, int numPax){
		ArrayList<Table> availableTables = checkTableAvailability(reserveDateTime, numPax);
		if(availableTables==null){
			System.out.println("No tables available for reservation on datetime " + reserveDateTime.getTime());
		}
		System.out.println("Selection No.		TableID			Capacity");
		for(int i=0;i<availableTables.size();i++){
		System.out.println(i+"					"+availableTables.get(i).getTableId()+"			"+availableTables.get(i).getCapacity());
		}
	}
	
	
	// finds array of available tables for particular timing
	public static ArrayList<Table> checkTableAvailability(Calendar reserveDateTime, int numPax){
		ReservationMgr.removeExpiredReservation();
		ArrayList<Table> availableTables = new ArrayList<Table>();
		boolean isLunchSession=false;						//check lunch or dinner session for reserveDateTime
		if(reserveDateTime.get(Calendar.HOUR)<15){isLunchSession=true;}
		boolean alsoLunchSession;							//check lunch or dinner session for existing reservation
		boolean available;
		
		for(Table t : tables){
			available = true;
			alsoLunchSession=false;
			if(t.getCapacity() >= numPax||t.getStatus()!=TableStatus.OCCUPIED){
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
