import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import classes.*;

public class application {

	public static final Path 	dataPath 			= Paths.get(System.getProperty("user.dir"), "data");
	public static final String 	saveDataFileName	= "RRPSS.dat";
	public Staff thisStaff = null;

	public static void main(String[] args) {
		RRPSS orrpss = loadRestuarant();
		
		for(MenuItem m : orrpss.menuItems)
			System.out.println(m.getMenuName());
		
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

	@SuppressWarnings("unchecked")
	public static RRPSS loadRestuarant(){

		Path saveData 			= Paths.get(dataPath.toString(), saveDataFileName);
		ArrayList<MenuItem> o 			= null;
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
	
	public void menuGetStaffIdentity(){
		//get this staff --> thisStaff
	}
	
	public void menuOption(){}
	
}
