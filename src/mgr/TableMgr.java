package mgr;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import classes.Table;
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
		boolean isAM=false;						//check am or pm session for reserveDateTime
		if(reserveDateTime.HOUR<15){isAM=true;}
		boolean isAMtoo;						//check am or pm session for existing reservation
		boolean available;
		for(int i=0;i<tables.size();i++){
			available = true;
			isAMtoo=false;
			if(tables.get(i).getCapacity()>=numPax){
				for(int j=0;j<tables.get(i).getReserveBy().size();j++){				//compare same month, same date, same session
					if(tables.get(i).getReserveBy().get(j).getArrivalTime().MONTH==reserveDateTime.MONTH){
						if(tables.get(i).getReserveBy().get(j).getArrivalTime().DATE==reserveDateTime.DATE){
							if(tables.get(i).getReserveBy().get(j).getArrivalTime().HOUR<15){isAMtoo=true;}
							if(isAM == isAMtoo){
								available = false;
								break;			//table is unavailable, proceed to check next table
							}
						}
					}	
				}
			}
			else{available = false;}
			if (available == true){availableTables.add(tables.get(i));}
		}		
		return availableTables;
	}
	
	
}
