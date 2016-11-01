package test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import classes.Order;
import classes.Reservation;
import classes.Staff;
import classes.Table;
import db.Restaurant;
import mgr.ReservationMgr;
import ui.OrderUI;


/**
 * 
 * @author soh jun jie
 *
 */
public class TestOrder {
	
	private ArrayList<Table> testTables;
	private ArrayList<Reservation> testReservations;
	private ArrayList<Order> testOrders;
	private Staff testStaff;
	
	@Before
	public void setUp() throws Exception {
		
		//SET UP A RESERVATION AND CREATE ORDER FROM IT
		Restaurant.initTables();
		Restaurant.initReservations();
		Restaurant.initSettledReservations();
		Restaurant.initStaff();
		Restaurant.initOrders();
		Restaurant.initSettledOrders();
		Restaurant.initInvoices();
		
		this.testTables = Restaurant.tables;
		this.testReservations = Restaurant.reservations;
		this.testStaff = Restaurant.staffs.get(0);
		this.testOrders = Restaurant.orders;
		
	}
	
	@Test
	public void testOrderInvoicePrint() {
		
		Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR_OF_DAY, Restaurant.AMStartTime);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		
		Table reserveTable = testTables.get(0);
		Reservation newReservation = new Reservation("TestCustomer", 98765432, reserveTable.getCapacity(), now, reserveTable);
		testReservations.add(newReservation);
		
		// Reservation for the table must be 1 due to being reserved
		assertEquals(1, reserveTable.getReservedBy().size());
		
		// Table status must switch to occupied once accepted, order must be created
		ReservationMgr.createOrderFromReservation(testStaff, newReservation);
		assertEquals(Table.TableStatus.OCCUPIED, reserveTable.getStatus());
		assertEquals(1, testOrders.size());
		
		Order order = testOrders.get(0);
		Class<OrderUI> c = OrderUI.class;

		@SuppressWarnings("rawtypes")
		Class[] cArg = new Class[1];
        cArg[0] = Order.class;
		
		Method method = null;
		
		try {
			method = c.getDeclaredMethod("printInvoice", cArg);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
		
		method.setAccessible(true);
		
		// Table reserved by must be removed, status must be vacated
		try {
			method.invoke(null, order);
			assertEquals(0, reserveTable.getReservedBy().size());
			assertEquals(Table.TableStatus.VACATED, reserveTable.getStatus());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}

}
