package classes;

import java.io.Serializable;

public class Food extends MenuItem implements Serializable{

	private static final long serialVersionUID = 4883217370046073402L;
	public enum CourseType {MAIN_COURSE, DESSERT, DRINKS};
	private CourseType type;

	public Food(String menuName, String desc, double price, CourseType type) {
		super(menuName, desc, price);
		this.type = type;
	}

	public CourseType getType() { return this.type; }
	public void setType(CourseType type) { this.type = type; }

}
