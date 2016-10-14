package classes;

public class MenuItem {
	
	private String 		menuName;
	private String 		desc;
	
	public MenuItem(String menuName, MenuType type, String desc){
		this.menuName = menuName;
		this.desc = desc;
	}
	
	public void setMenuName(String menuName){ this.menuName = menuName; }
	public void setDesc(String desc){ this.desc = desc; }
	
	public String getMenuName(){ return this.menuName; }
	public String getDesc(){ return this.desc; }

}
