package restaurant.model.vo;

import java.io.Serializable;

public class RestaurantMap implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int mapNo;
	private String rName;
	private String lat;
	private String lng;
	
	public RestaurantMap() {}

	public RestaurantMap(int mapNo, String rName, String lat, String lng) {
		super();
		this.mapNo = mapNo;
		this.rName = rName;
		this.lat = lat;
		this.lng = lng;
	}

	public int getMapNo() {
		return mapNo;
	}

	public void setMapNo(int mapNo) {
		this.mapNo = mapNo;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "RestaurantMap [mapNo=" + mapNo + ", rName=" + rName + ", lat=" + lat + ", lng=" + lng + "]";
	}
	
	
	
	
}
