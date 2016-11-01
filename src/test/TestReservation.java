package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import classes.Reservation;
import classes.Staff;
import classes.Table;
import db.Restaurant;
import mgr.ReservationMgr;

public class TestReservation {
	
	private ArrayList<Table> testTables;
	private ArrayList<Reservation> testReservations;
	private Staff testStaff;
	
	@Before
	public void setUp() throws Exception {
		Restaurant.initTables();
		Restaurant.initReservations();
		Restaurant.initSettledReservations();
		Restaurant.initStaff();
		
		this.testTables = Restaurant.tables;
		this.testReservations = Restaurant.reservations;
		this.testStaff = Restaurant.staffs.get(0);
		
	}
	
	@Test
	public void testFullReservationScenario(){
		
		Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR_OF_DAY, Restaurant.AMStartTime);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		
		int index = 0;
		for(Table t : testTables){
			testReservations.add(new Reservation("cust"+index, index, t.getCapacity(), now, t));
			index++;
		}		
		assertEquals(testTables.size(), testReservations.size());	// confirm full reservation
		int fullReservationSize = testReservations.size();
		
// TODO		try create reservation with full reservation for current session
//		ReservationMgr.makeWalkInReservation(testStaff);
		
		assertEquals(fullReservationSize, testReservations.size());	// ensure reservation size still same
		
	}
	
//	@Test
//	public void testMakeReservation() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testMakeWalkInReservation() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCreateOrderFromReservation() {
//		fail("Not yet implemented");
//	}
//

	/**
	 * Test reservation made 30min before now
	 * and not yet accepted is removed.
	 */
	@Test
	public void testRemoveExpiredReservation() {
		
		Restaurant.initTables();
		Restaurant.initReservations();
		Restaurant.initSettledReservations();
		Restaurant.initStaff();
		
		ArrayList<Table> testTables = Restaurant.tables;
		ArrayList<Reservation> reservations = Restaurant.reservations;
		
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, -30);
		
		int index = 0;
		for(Table t : testTables){
			reservations.add(new Reservation("cust"+index, index, t.getCapacity(), now, t));
			index++;
		}
		
		ReservationMgr.removeExpiredReservation();
		assertEquals(0, reservations.size());
		
	}

}
