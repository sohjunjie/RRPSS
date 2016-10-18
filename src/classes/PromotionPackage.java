package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class PromotionPackage extends MenuItem implements Serializable{

	private static final long serialVersionUID = -5702646010331366934L;
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
	public void addFood(ArrayList<MenuItem> foodMenu){

		Scanner sc = new Scanner(System.in);
		
		System.out.println("What food would you like to add to the Promotion Package?");
		int index = 0;
		for (MenuItem menuItem : foodMenu){
			if(menuItem instanceof Food)
				System.out.println("(" + index + ") " + menuItem.getMenuName());
			index++;
		}

		int choice = sc.nextInt();
		sc.close();

		try {
			if(foodMenu.get(choice) instanceof Food){
				Food foodToAdd = (Food) foodMenu.get(choice);
				this.foodSet.add(foodToAdd);
				System.out.println(foodToAdd.getMenuName() + " added to promotion package.");				
			}else{
				System.out.println("selected item is not a food");
			}
		}catch(IndexOutOfBoundsException e){
			System.out.println("Add food item failed! (Invalid index provided");
		}
		
	}

	
	public void removeFood(Food food) { foodSet.remove(food); }
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
