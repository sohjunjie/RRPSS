package classes;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: implements comparable, search vacated, capacity
public class Table implements Serializable{

	private static final long serialVersionUID = -696513068399675213L;
	public enum TableStatus {VACATED, OCCUPIED};
	private int table_id;
	private int capacity;
	private TableStatus status;
	private ArrayList<Reservation> reservedBy;
	
	public Table (int table_id, int capacity) {
		this.table_id	= table_id;
		this.capacity	= capacity;
		this.reservedBy	= new ArrayList<Reservation>();
		this.status		= TableStatus.VACATED;
	}
	
	public int getTableId(){ return table_id; }
	public int getCapacity(){ return capacity; }
	public TableStatus getStatus(){ return status; }
	public ArrayList<Reservation> getReserveBy(){ return this.reservedBy; }
	
	public void setStatus(TableStatus status){ this.status=status; }
	
	public void addTableReservation(Reservation reservation){ this.reservedBy.add(reservation); }
	
	public void removeTableReservation(int index){ this.reservedBy.remove(index); }
	public void removeTableReservation(Reservation reservation){ this.reservedBy.remove(reservation); }
	
}
