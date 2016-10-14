package entity;
// TODO: implements comparable, search vacated, capacity
public class Table {
	public enum TableStatus {VACATED, OCCUPIED, RESERVED};
	private int table_id;
	private int capacity;
	private TableStatus status;
	
	public Table (int table_id, int capacity) {
		this.table_id=table_id;
		this.capacity=capacity;
		status=TableStatus.VACATED;
	}
	
	public int getTableId() {
		return table_id;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public TableStatus getStatus() {
		return status;
	}
	
	public void setTableId(int table_id) {
		this.table_id=table_id;
	}
	
	public void setCapacity(int capacity) {
		this.capacity=capacity;
	}
	
	public void setStatus(TableStatus status) {
		this.status=status;
	}
}
