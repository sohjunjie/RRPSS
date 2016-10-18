import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import classes.*;

public class application {

	public static final Path 	dataPath 			= Paths.get(System.getProperty("user.dir"), "data");
	public static final String 	saveDataFileName	= "RRPSS.dat";
	public static Staff thisStaff = null;
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		RRPSS orrpss = loadRestuarant();
		
		while(thisStaff == null)
			thisStaff = menuGetStaffIdentity(orrpss.staffs);		
		
		saveRestuarant(orrpss);
	}

	public static void saveRestuarant(RRPSS saveRPSS){

		Path 				saveFileName 	= Paths.get(dataPath.toString(), saveDataFileName);
		FileOutputStream   	fos 			= null;
		ObjectOutputStream 	oos 			= null;
		
		try {
			
			fos = new FileOutputStream(saveFileName.toString());
			oos = new ObjectOutputStream(fos);
			oos.writeObject(saveRPSS);
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static RRPSS loadRestuarant(){

		Path saveData 			= Paths.get(dataPath.toString(), saveDataFileName);
		FileInputStream fis 	= null;
		ObjectInputStream ois 	= null;
		RRPSS retRRPSS = null;
		
		try {
			fis = new FileInputStream(saveData.toString());
			ois = new ObjectInputStream(fis);			
			retRRPSS = (RRPSS) ois.readObject();
			ois.close();

		} catch (IOException ex) {
//			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
//			ex.printStackTrace();
		}

		if(retRRPSS == null)
			retRRPSS = new RRPSS();
		
		return retRRPSS;
	}
	
	public static Staff menuGetStaffIdentity(ArrayList<Staff> staffs){
		
		int index = 0;
		System.out.println("Who are you ?");
		for(Staff s : staffs){
			System.out.println("(" + index++ + ") " + s);
		}
		System.out.print("    Enter index: ");
		int choice = sc.nextInt();
		
		try {
			return staffs.get(choice);
		}catch(IndexOutOfBoundsException e){
			System.out.println("Invalid index entered!");
			return null;
		}

	}
	
	public void menuOption(){
		
		int choice;
		
        do {
            System.out.println("\nSelect a choice: ");
            System.out.println("(1) Make a reservation");
            System.out.println("(2) Take order");
            System.out.println("(3) Calculate volume of my shape");
            System.out.println("(4) Exit");
        	System.out.println();
        	System.out.print("    Enter the number of your choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1: // Define a list of shapes
	                    System.out.print("    Enter total number of shapes: ");
                        break;
                case 2: // Calculate area of all the shapes
                        break;
                case 3: // Show the list of customers with their seat numbers sorted by seat numbers
                    break;
                case 4:
            }

        } while (choice < 4);
		
	}
	
}
