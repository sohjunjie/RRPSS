import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import classes.MenuItem;
import classes.Staff;
import classes.Table;

public class application {

	public static final Path 	dataPath 			= Paths.get(System.getProperty("user.dir"), "data");
	public static final String 	saveDataFileName	= "RRPSS.dat";

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

	public static RRPSS loadRestuarant(){

		Path saveData 			= Paths.get(dataPath.toString(), saveDataFileName);
		RRPSS retRRPSS 			= null;
		FileInputStream fis 	= null;
		ObjectInputStream ois 	= null;

		try {
			fis = new FileInputStream(saveDataFileName.toString());
			ois = new ObjectInputStream(fis);
			retRRPSS = (RRPSS) ois.readObject();
			ois.close();
		} catch (IOException ex) {
//			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
//			ex.printStackTrace();
		}

		if(retRRPSS == null){
			System.out.println("test");
			retRRPSS = new RRPSS();
		}

		
		return retRRPSS;
	}
	
}
