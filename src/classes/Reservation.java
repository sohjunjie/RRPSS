package classes;

import java.io.Serializable;
import java.util.Calendar;

import db.Restaurant;

public class Reservation implements Serializable{
	
	private static final long serialVersionUID = 3558136592677236481L;
	private int 		reservationID;
	private int 		numPax;
	private String 		customerName;
	private int 		customerContact;
	private boolean 	accepted;
	private Calendar 	arrivalTime;
	
	public Reservation(String customerName, int customerContact, int numPax, int reservationID, Calendar arrivalTime){
		this.customerName = customerName;
		this.customerContact = customerContact;
		this.numPax = numPax;
		this.accepted = false;
		this.reservationID = reservationID;
		this.arrivalTime = arrivalTime;
	}

	public void setAccepted(){
		this.accepted = true;
		Restaurant.reservations.remove(this);
		Restaurant.settledReservations.add(this);
	}

	public boolean getAcceptedStatus(){ return this.accepted; }
	public String getCustomerName(){ return this.customerName; }
	public int getCustomerContact(){ return this.customerContact; }
	public int getNumPax(){ return this.numPax; }
	public int getReservationID(){ return this.reservationID; }
	public Calendar getArrivalTime(){ return this.arrivalTime; }
	
	public void setCustomerName(String newName){ this.customerName = newName; }
	public void setCustomerContact(int newContact) { this.customerContact = newContact; }

}