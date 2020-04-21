package restaurant.model.vo;

import java.io.Serializable;

public class RestaurantMenu implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int menuNo;
	private String rName;
	private String menu;
	private int price;
	private String recommend;
	private String singleYN;
	
	public RestaurantMenu() {}

	public RestaurantMenu(int menuNo, String rName, String menu, int price, String recommend, String singleYN) {
		super();
		this.menuNo = menuNo;
		this.rName = rName;
		this.menu = menu;
		this.price = price;
		this.recommend = recommend;
		this.singleYN = singleYN;
	}

	public int getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getSingleYN() {
		return singleYN;
	}

	public void setSingleYN(String singleYN) {
		this.singleYN = singleYN;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return menuNo + ","+ rName + ","+ menu + ","+ price
				+ ","+ recommend + ","+ singleYN;
	}
	
	
	
}
