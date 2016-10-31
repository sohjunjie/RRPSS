package classes;

import java.io.Serializable;
import db.Restaurant;

//TODO: JAVADOC

public class Staff implements Serializable{
	private static final long serialVersionUID = -3686894603777756471L;
	private int empId;
	private String name;
	private char gender;
	private String jobTitle;
	
	public String toString(){
		return "EmpId: " + this.empId + "     Name: " + this.name + "    title: " + this.jobTitle;
	}
	
	public Staff(String name, int empId, char gender, String jobTitle) {
		this.empId=empId;
		this.name=name;
		this.gender=gender;
		this.jobTitle=jobTitle;
	}
	
	public int getEmpId(){ return empId; }
	public String getName(){ return name; }
	public String getJobTitle(){ return jobTitle; }
	public char getGender() {
		return this.gender;
	}
	
	public void setName(String name){ this.name = name; }
	public void setEmpId(int empId){ this.empId = empId; }
	public void setGender(char gender){ this.gender = gender; }
	public void setJobTitle(String jobTitle){ this.jobTitle = jobTitle; }
	
	//TODO: Remove createNewOrder method from class diagram
	
	// order automatically created upon accepting reservation
	public Order acceptReservation(Reservation reservation){
		reservation.setAccepted();
		return new Order(this, reservation);
	}

}