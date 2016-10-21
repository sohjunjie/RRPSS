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
		
		Restaurant orrpss = loadRestuarant();
		
		while(thisStaff == null)
			thisStaff = menuGetStaffIdentity(orrpss.staffs);

		// close shop - settle all pending orders before closing application
		
		saveRestuarant(orrpss);
		
		System.exit(0);
	}

	public static void saveRestuarant(Restaurant saveRPSS){

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

	public static Restaurant loadRestuarant(){

		Path saveData 			= Paths.get(dataPath.toString(), saveDataFileName);
		FileInputStream fis 	= null;
		ObjectInputStream ois 	= null;
		Restaurant retRRPSS = null;
		
		try {
			fis = new FileInputStream(saveData.toString());
			ois = new ObjectInputStream(fis);			
			retRRPSS = (Restaurant) ois.readObject();
			ois.close();

		} catch (IOException ex) {
//			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
//			ex.printStackTrace();
		}

		if(retRRPSS == null)
			retRRPSS = new Restaurant();
		
		return retRRPSS;
	}
	
	public static Staff menuGetStaffIdentity(ArrayList<Staff> staffs){
		
		int index = 0;
		System.out.println("Who are you ?");
		for(Staff s : staffs){
			System.out.println("(" + index++ + ") " + s);
		}
    	System.out.print("    Enter the number of your choice: ");
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
            System.out.println("(1) Show availability");
            System.out.println("(2) Make reservation");
            System.out.println("(3) Accept reservation");
            System.out.println("(4) View pending order");
            System.out.println("(5) Print sales revenue report");
            System.out.println("(6) Exit");
        	System.out.println();
        	System.out.print("    Enter the number of your choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1: 
                        break;
                case 2: // Calculate area of all the shapes
                        break;
                case 3: // Show the list of customers with their seat numbers sorted by seat numbers
                    break;
                case 4:
                	break;
                case 5:
                	break;
                case 6:
            }

        } while (choice < 6);
		
	}
	
}
