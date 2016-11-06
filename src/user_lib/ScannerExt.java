package user_lib;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerExt {

	private static Scanner sc = new Scanner(System.in);
	
	public static int nextInt(String promptMsg){
		
		int choice = 0;
		boolean valid = true;
		
		do{
			try{
				valid = true;
				System.out.print(promptMsg);
				choice = sc.nextInt();
			}catch(InputMismatchException e){
				valid = false;
				sc.nextLine();	// get dummy line
				System.out.println("Error, expected a integer number value");
			}
		}while(!valid);
		
		sc.nextLine();	// get dummy line
		return choice;
	}
	
	public static double nextDouble(String promptMsg){
		
		double choice = 0;
		boolean valid = true;
		
		do{
			try{
				valid = true;
				System.out.print(promptMsg);
				choice = sc.nextDouble();
			}catch(InputMismatchException e){
				valid = false;
				sc.nextLine();	// get dummy line
				System.out.println("Error, expected a integer number value");
			}
		}while(!valid);
		
		sc.nextLine();	// get dummy line
		return choice;
		
	}
	
}
