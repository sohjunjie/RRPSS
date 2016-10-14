import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import classes.Staff;
import classes.Table;

public class RRPSS implements Serializable{

	private static final long serialVersionUID = 7526471155622776147L;
	
	public ArrayList<Table> 				tables;
	public ArrayList<Staff> 				staffs;
	public ArrayList<Invoice> 				invoices;
	public ArrayList<MenuItem> 				menuItems;
	public ArrayList<SetPromotion> 			setPromotions;
	public ArrayList<Order> 				orders;
	public ArrayList<ReservationBooking>	bookings;

	public RRPSS(){
		// Initialize restaurant
        this.tables 	= initTables();
        this.staffs 	= initStaff();
	}
	
	public ArrayList<SetPromotion> initSetPromotion(){
		ArrayList<SetPromotion> setPromotions = new ArrayList<SetPromotion>();
		
		
		return setPromotions;
	}

	public ArrayList<MenuItem> initMenu(){
		ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
		menuItems.add(new MenuItem());
		menuItems.add(new MenuItem());
		menuItems.add(new MenuItem());
		return menuItems;
	}

	public ArrayList<Staff> initStaff(){
		ArrayList<Staff> retStaff = new ArrayList<Staff>();
		retStaff.add(new Staff("John", 1, true, "Chef"));
		retStaff.add(new Staff("May", 2, false, "Cashier"));
		retStaff.add(new Staff("Nitro", 3, true, "Waiter"));
		retStaff.add(new Staff("Miki", 4, false, "Cashier"));
		return retStaff;
	}

	public ArrayList<Table> initTables(){
		ArrayList<Table> retTables = new ArrayList<Table>();
		int i;
		for(i=0; i<5; i++)						// 5 x 10 seats
			retTables.add(new Table(i, 10));	
		for(i=5; i<10; i++)						// 5 x 8 seats
			retTables.add(new Table(i, 8));
		for(i=10; i<20; i++)					// 10 x 4 seats
			retTables.add(new Table(i, 4));
		for(i=20; i<30; i++)					// 10 x 2 seats
			retTables.add(new Table(i, 2));
		return retTables;
	}

	public class Invoice{ }
	public class MenuItem{ }
	public class SetPromotion{ }
	public class Order{ }
	public class ReservationBooking{ }

	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException{
	
	}
	 
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
	
	}
	

}
