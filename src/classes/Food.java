package classes;

public class Food extends MenuItem {
	public enum Type {MAIN_COURSE, DESSERT, DRINKS};
	private Type type;

	public Food(String menuName, String desc, double price, Type type) {
		super(menuName, desc, price);
		this.type = type;		
	}

	public Type getType() { return this.type; }
	public void setType(Type type) { this.type = type; }

}
