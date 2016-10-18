package classes;

import java.util.GregorianCalendar;


public class Reservation {
	
	private int reservationID;					
	private int numPax;
	private String customerName;				//only customerName, customerContact and  acceptStatus can be changed
	private int customerContact;
	private boolean acceptedStatus;				//true = accepted, false = not yet accepted
	private GregorianCalendar arrivalTime;		//reservationTime and numPax cannot be changed, create new reservation is necessary
	
	public Reservation(String customerName, int customerContact, int numPax, int reservationID, GregorianCalendar arrivalTime){
		this.customerName = customerName;
		this.customerContact = customerContact;
		this.numPax = numPax;
		this.acceptedStatus = false;
		this.reservationID = reservationID;
		this.arrivalTime = arrivalTime;
	}
	
	public void acceptReservation(){ this.acceptedStatus = true; }
	
	public boolean getAcceptedStatus(){ return this.acceptedStatus; }
	public String getCustomerName(){ return this.customerName; }
	public int getCustomerContact(){ return this.customerContact; }
	public int getNumPax(){ return this.numPax; }
	public int getReservationID(){ return this.reservationID; }	
	public GregorianCalendar getArrivalTime(){ return this.arrivalTime; }
	
	public void setCustomerName(String newName){ this.customerName = newName; }	
	public void setCustomerContact(int newContact) { this.customerContact = newContact; }

}