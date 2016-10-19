package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Staff implements Serializable{
	private static final long serialVersionUID = -3686894603777756471L;
	private String name;
	private int empId;
	private boolean genderIsMale;
	private String jobTitle;
	
	Scanner sc = new Scanner (System.in);
	
	public String toString(){
		return "EmpId: " + this.empId + "     Name: " + this.name + "    title: " + this.jobTitle;
	}
	
	public Staff(String name, int empId, boolean genderIsMale, String jobTitle) {
		this.name=name;
		this.empId=empId;
		this.genderIsMale=genderIsMale;
		this.jobTitle=jobTitle;
	}
	
	public String getName(){ return name; }
	public int getEmpId(){ return empId; }
	public String getJobTitle(){ return jobTitle; }
	public String getGender() {
		if (genderIsMale)
			return "Male";
		else
			return "Female";
	}
	
	public void setName(String name){ this.name=name; }
	public void setEmpId(int empId){ this.empId=empId; }
	public void setGender(boolean genderIsMale){ this.genderIsMale=genderIsMale; }
	public void setJobTitle(String jobTitle){ this.jobTitle=jobTitle; }
	
	public void makeReservationBooking(ArrayList<Reservation> reservations){

		String custName;
		int custContact;
		int numPax;
		int reservationID;

	}

	public void createNewOrder(ArrayList<Order> Orders, Table orderTable){
		Order order = new Order(this, orderTable);
		Orders.add(order);
	}
	
	public void takeOrder(Order order, ArrayList<MenuItem> FoodMenu){
		order.addOrderItem(FoodMenu);
	}
	
	public void createReservation(ArrayList<Reservation> bookings) {
		System.out.print("Enter customer name: ");
		String customerName = sc.next();
		System.out.print("Enter customer contact number: ");
		int customerContact = sc.nextInt();
		System.out.print("Enter number of people: ");
		int numPax = sc.nextInt();
		int reservationID = bookings.size() + 1; 
		System.out.print("Enter year: ");
		int year = sc.nextInt();
		System.out.print("Enter month (1 is Jan, 12 is Dec) :");
		int month =sc.nextInt()-1;
		System.out.print("Enter day of month: ");
		int dayOfMonth = sc.nextInt();
		System.out.print("Enter hour of day: ");
		int hourOfDay = sc.nextInt();
		System.out.print("Enter minute: ");
		int minute = sc.nextInt();
		Calendar arrivalTime = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute);
		bookings.add(new Reservation(customerName, customerContact, numPax, reservationID, arrivalTime));
	}

	public void acceptReservation(ArrayList<Reservation> bookings) {
		System.out.print("Enter reservation ID of reservation to accept: ");
		int res_id = sc.nextInt();
		bookings.get(res_id).acceptReservation();
	}
}