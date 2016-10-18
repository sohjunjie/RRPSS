package classes;

import java.io.Serializable;

public abstract class MenuItem implements Serializable{
	
	private static final long serialVersionUID = 1991173970229115074L;
	private String 		menuName;
	private String 		desc;
	private double		price;
	
	public MenuItem(String menuName, String desc, double price){
		this.menuName = menuName;
		this.desc = desc;
		this.price = price;
	}
	
	public void setMenuName(String menuName){ this.menuName = menuName; }
	public void setDesc(String desc){ this.desc = desc; }
	public void setPrice(double price){ this.price = price; }
	
	public String getMenuName(){ return this.menuName; }
	public String getDesc(){ return this.desc; }
	public double getPrice(){ return this.price; }

}
