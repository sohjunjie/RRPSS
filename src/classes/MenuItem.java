package classes;

public class MenuItem {

	public enum MenuType {MAIN_COURSE, DRINKS, DESSERT};
	
	private String 		menuName;
	private MenuType 	type;
	private String 		desc;
	
	public MenuItem(String menuName, MenuType type, String desc){
		this.menuName = menuName;
		this.desc = desc;
		this.type = type;
	}
	
	public void setMenuName(String menuName){ this.menuName = menuName; }
	public void setMenuType(MenuType type){ this.type = type; }
	public void setDesc(String desc){ this.desc = desc; }
	
	public String getMenuName(){ return this.menuName; }
	public MenuType getMenuType(){ return this.type; }
	public String getDesc(){ return this.desc; }
	
}
