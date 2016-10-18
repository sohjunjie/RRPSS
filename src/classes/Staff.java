package classes;

import java.io.Serializable;
import java.util.ArrayList;


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
	
	
}