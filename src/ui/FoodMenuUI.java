package ui;

import java.util.Scanner;

public class FoodMenuUI {
	
	public static void menuShowFoodMenuOptions(){
		
		int choice;
		Scanner sc = new Scanner(System.in);
		
        do {
            System.out.println("\nSelect a choice: ");
            System.out.println("(1) Add new food to food menu");
            System.out.println("(2) Add new promotion package to food menu");
            System.out.println("(3) Remove a menu item from food menu");
            System.out.println("(4) Back");
        	System.out.println();
        	System.out.print("    Enter the number of your choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1: 
                        break;
                case 2:
                        break;
                case 3:
                    	break;
                case 4:
            }

        } while (choice < 4);
		
        sc.close();
	}
	
}