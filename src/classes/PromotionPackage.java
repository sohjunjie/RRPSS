package classes;

import java.util.ArrayList;
import java.util.Scanner;

public class PromotionPackage extends MenuItem {

	private ArrayList<Food> foodSet;
	private int index;
	
	public PromotionPackage(String menuName, String desc, double price) {
		super(menuName, desc, price);
		this.foodSet = new ArrayList<Food>();
	}	
	public PromotionPackage(String menuName, String desc, double price, ArrayList<Food> foodSet) {
		super(menuName, desc, price);
		this.foodSet = foodSet;
	}

	public ArrayList<Food> getFoodSet(){ return foodSet; }	
	public void setFoodSet(ArrayList<Food> foodSet){ this.foodSet = foodSet; }
	
	public void addFood(Food food){ foodSet.add(food); }
	
	public void removeFood() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("What food would you like to remove from the Promotion Package?");
		
		for (Food fd : foodSet)
			System.out.println(foodSet.indexOf(fd) + ": " + fd.getMenuName());

		index = sc.nextInt();
		sc.close();
		
		try {
			String foodNameRemoved = foodSet.get(index).getMenuName();
			foodSet.remove(index);
			System.out.println(foodNameRemoved + " removed from promotion package."); 
		}catch(IndexOutOfBoundsException e){
			System.out.println("Food item removal failed! (Invalid index provided");
		}
		
	}
	

}
