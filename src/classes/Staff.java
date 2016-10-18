package classes;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Staff implements Serializable{
	private static final long serialVersionUID = -3686894603777756471L;
	private String name;
	private int emp_id;
	private boolean genderIsMale;
	private String job_title;
	
	public Staff(String name, int emp_id, boolean genderIsMale, String job_title) {
		this.name=name;
		this.emp_id=emp_id;
		this.genderIsMale=genderIsMale;
		this.job_title=job_title;
	}
	
	public String getName(){ return name; }
	public int getEmpId(){ return emp_id; }
	public String getJobTitle(){ return job_title; }
	public String getGender() {
		if (genderIsMale)
			return "Male";
		else
			return "Female";
	}
	
	public void setName(String name){ this.name=name; }
	public void setEmpId(int emp_id){ this.emp_id=emp_id; }
	public void setGender(boolean genderIsMale){ this.genderIsMale=genderIsMale; }
	public void setJobTitle(String job_title){ this.job_title=job_title; }
	
	public void makeReservationBooking(ArrayList<Reservation> reservations){

		String custName;
		int custContact;
		int numPax;
		int reservationID;

	}
	
	public void createNewOrder(ArrayList<Order> Orders, Table orderTable){
		Order order = new Order("", this, orderTable);
		Orders.add(order);
	}
	
	public void takeOrder(Order order, ArrayList<MenuItem> FoodMenu){
		order.addOrderItem(FoodMenu);
	}
	
	
}