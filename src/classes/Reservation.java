package classes;
import java.util.GregorianCalendar; 

class Reservation {
	
	private int reservationID;					
	private int numPax;
	private String customerName;				//only customerName, customerContact, acceptStatus and valid Status can be changed
	private int customerContact;
	private boolean acceptedStatus;				//true = accepted, false = not yet accepted
	private boolean validStatus;				//true = still valid, false = reservation is removed
	private GregorianCalendar reservationTime;	//reservationTime and numPax cannot be changed, create new reservation is necessary
	
	public Reservation(String customerName,int customerContact,int numPax,int reservationID, GregorianCalendar reservationTime)
	{
		this.customerName = customerName;
		this.customerContact = customerContact;
		this.numPax = numPax;
		this.acceptedStatus = false;
		this.validStatus = true;
		this.reservationID = reservationID;
		this.reservationTime = reservationTime;
	}
	
	public void removeReservation()
	{
		this.validStatus = false;
	}
	
	public void acceptReservation()
	{
		this.acceptedStatus = true;
	}
	
	public boolean getAcceptedStatus()
	{
		return this.acceptedStatus;
	}
	
	public void setCustomerName(String newName)
	{
		this.customerName = newName;
	}
	
	public String getCustomerName()
	{
		return this.customerName;
	}
	
	public void setCustomerContact(int newContact)
	{
		this.customerContact = newContact;
	}
	public int getCustomerContact()
	{
		return this.customerContact;
	}
	
	public int getNumPax()
	{
		return this.numPax;
	}
	
	public int getReservationID()
	{
		return this.reservationID;
	}
	
	public GregorianCalendar getReservationTime()
	{
		return this.reservationTime;
	}

}

