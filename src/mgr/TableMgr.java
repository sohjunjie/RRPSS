package mgr;

import java.util.ArrayList;
import java.util.Calendar;

import classes.Table;
import db.Restaurant;

public class TableMgr {

	public static ArrayList<Table>			tables = Restaurant.tables;
	
	// TODO: Find list of table for reservation given datetime and allow
	//		 user to select the table to reserve
	public static Table findReservationTable(Calendar reserveDateTime){
		return null;
	}
	
	// TODO: print list of table available for given date, and numPax
	public static void showTableAvailability(Calendar reserveDateTime, int numPax){
		
	}
	
	
}
