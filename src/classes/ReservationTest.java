package classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ReservationTest {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
		ArrayList<Reservation> bookings = new ArrayList<Reservation>();
		Staff s = new Staff("s", 5, true, "s");
		s.createReservation(bookings);
		s.acceptReservation(bookings);
		System.out.print(bookings.get(0).getArrivalTime().get(Calendar.DAY_OF_MONTH) + "/" + 
				bookings.get(0).getArrivalTime().get(Calendar.MONTH) + "/" + 
				bookings.get(0).getArrivalTime().get(Calendar.YEAR) + " " +
				bookings.get(0).getArrivalTime().get(Calendar.HOUR) + " hour " +
				bookings.get(0).getArrivalTime().get(Calendar.MINUTE) + " min");
	}

}
