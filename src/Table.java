
public class Table {
	public enum KindofStatus {vacated, occupied, reserved};
	private int table_id;
	private int capacity;
	private KindofStatus status;
	
	public Table (int table_id, int capacity) {
		this.table_id=table_id;
		this.capacity=capacity;
		status=KindofStatus.vacated;
	}
	
	public int getTableId() {
		return table_id;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public KindofStatus getStatus() {
		return status;
	}
	
	public void setTableId(int table_id) {
		this.table_id=table_id;
	}
	
	public void setCapacity(int capacity) {
		this.capacity=capacity;
	}
	
	public void setStatus(KindofStatus status) {
		this.status=status;
	}
}
