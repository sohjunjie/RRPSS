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

	public void createNewOrder(Reservation fromReservation){
		Order order = new Order(this, fromReservation);
		Restaurant.orders.add(order);
	}
	
	public void takeOrder(Order order){
		order.addOrderItem();
	}
	
	public void createReservation() {
		
		Scanner sc = new Scanner (System.in);
		ArrayList<Reservation> reservations = Restaurant.settledReservations;
		int year, month, dayOfMonth, hourOfDay, minute;
		boolean invalidDay, invalidHour;

		int reservationID = Calendar.getInstance().hashCode();
		
		System.out.print("Enter customer name: ");
		String customerName = sc.next();
		
		System.out.print("Enter customer contact number: ");
		int customerContact = sc.nextInt();
		
		System.out.print("Enter number of people: ");
		int numPax = sc.nextInt();

		System.out.print("Enter year: ");
		do {
			year = sc.nextInt();
			if (year < 2010)
				System.out.print("Invalid year, please enter year again: ");
		} while (year < 2010);
		System.out.print("Enter month (1 is Jan, 12 is Dec): ");
		do {
			month = sc.nextInt() - 1 ;
			if (month > 12 || month < 0)
				System.out.print("Invalid month, please enter month again: ");
		} while (month> 12 || month < 0);
		System.out.print("Enter day of month: ");
		do {
			dayOfMonth = sc.nextInt();
			//account for leap year every 4 years, and number of days in each month)
			invalidDay = (month == 2 && year % 4 == 0 && dayOfMonth > 29 ||
					month == 2 && year % 4 !=0 && dayOfMonth > 28 ||
					(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month ==  10 || month == 12) && dayOfMonth > 31 ||
					(month == 4 || month == 6 || month == 9 || month == 11) && dayOfMonth > 30);
			if (invalidDay)
				System.out.print("Invalid day of month, please enter day of month again: ");
		} while (invalidDay);
		System.out.print("Enter hour of day (in 24 hr format): ");
		do {
			hourOfDay = sc.nextInt();
			invalidHour = (hourOfDay < 11 || hourOfDay > 15 && hourOfDay < 18 || hourOfDay > 22);
			if (invalidHour)
				System.out.print("Invalid reservation time, please enter hour again: ");
		} while (invalidHour);
		System.out.print("Enter minute: ");
		do {
			minute = sc.nextInt();
			if (minute < 0 || minute > 59)
				System.out.print("Invalid reservation time, please enter minute again: ");
		} while (minute < 0 || minute > 59);
		
		sc.close();
		
		Calendar arrivalTime = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute);
		reservations.add(new Reservation(customerName, customerContact, numPax, reservationID, arrivalTime));
	}

	
	public void acceptReservation(){

		//check expired reservation ?? reservation.getArrivalTime().getTime()
		int index = 0;
		Scanner sc = new Scanner (System.in);

		ArrayList<Reservation> reservations = Restaurant.reservations;
		
		System.out.print("Select which reservation to accept: ");
		for(Reservation r : reservations){
			System.out.println("(" + index++ + ") Customer Name:" + r.getCustomerName() + 
												"    Contact: " + r.getCustomerContact() +
												"    Arrival time: " + r.getArrivalTime().getTime());
		}
		int choice = sc.nextInt();
		sc.close();
		
		try {
			Reservation reservation = reservations.get(choice);
			reservation.acceptReservation();
			this.createNewOrder(reservation);
			System.out.println("Reservation accepted."); 
		}catch(IndexOutOfBoundsException e){
			System.out.println("Fail to accept reservation! (Invalid index provided");
		}		


	}

}