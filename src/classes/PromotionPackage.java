package classes;

import java.util.ArrayList;
import java.util.Scanner;

public class PromotionPackage extends MenuItem {
	public ArrayList<Food> foodSet;
	private int index;
	private Scanner sc = new Scanner(System.in);
	
	public PromotionPackage(String menuName, String desc, double price) {
		super(menuName, desc, price);
		this.foodSet = new ArrayList<Food>();
	}
	
	public PromotionPackage(String menuName, String desc, double price, ArrayList<Food> foodSet) {
		super(menuName, desc, price);
		this.foodSet = foodSet;
		
	}
	
	public void addFood(Food food) {
		foodSet.add(food);
	}
	
	public void removeFood() {
		System.out.println("What food would you like to remove from the Promotion Package?");
		for (Food s : foodSet)
			System.out.println(foodSet.indexOf(s) + ": " + s.getMenuName());
		index = sc.nextInt();
		if (index > -1) {
			String foodNameRemoved = foodSet.get(index).getMenuName();
			foodSet.remove(index);
			System.out.println(foodNameRemoved + " removed from promotion package."); 
		}
	}
	
	public ArrayList<Food> getFoodSet() {
		return foodSet;
	}
	
	public void setFoodSet(ArrayList<Food> foodSet) { 
		this.foodSet = foodSet;
	}
}
