
public class RRPSS {

	public static void main(String[] args) {
		Staff s = new Staff("John", 1, true, "Chef");
		System.out.println(s.getName());
		System.out.println(s.getEmpId());
		System.out.println(s.getGender());
		System.out.println(s.getJobTitle());
		
		Table t = new Table (1,10);
		System.out.println(t.getTableId());
		System.out.println(t.getCapacity());
		System.out.println(t.getStatus());
		t.setStatus(Table.KindofStatus.reserved);
		System.out.println(t.getStatus());
	}

}
