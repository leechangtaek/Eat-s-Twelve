package restaurant.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Restaurant implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String rName;
	private String type;
	private String location;
	private String bestYN;
	private double grade;
	private String description;
	private String category;
	private String rPhone;
	private String parkingYN;
	private int openTime;
	private int closeTime;
	private String holiday;
	private Date enrollDate;
	
	public Restaurant() {}
	
	public Restaurant(String rName, String type, String location,String description,
			String category, String rPhone, String parkingYN, int openTime, int closeTime, String holiday) {
		this.rName = rName;
		this.type = type;
		this.location = location;
		this.description = description;
		this.category = category;
		this.rPhone = rPhone;
		this.parkingYN = parkingYN;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.holiday = holiday;
	}

	public Restaurant(String rName, String type, String location, String bestYN, double grade, String description,
			String category, String rPhone, String parkingYN, int openTime, int closeTime, String holiday) {
		this(rName,type,location,description,category,rPhone,parkingYN,openTime,closeTime,holiday);
		this.bestYN = bestYN;
		this.grade = grade;
	}
	
	public Restaurant(String rName, String type, String location, String bestYN, double grade, String description,
			String category, String rPhone, String parkingYN, int openTime, int closeTime, String holiday, Date enrollDate) {
		this(rName,type,location,description,category,rPhone,parkingYN,openTime,closeTime,holiday);
		this.bestYN = bestYN;
		this.grade = grade;
		this.enrollDate = enrollDate;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBestYN() {
		return bestYN;
	}

	public void setBestYN(String bestYN) {
		this.bestYN = bestYN;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getrPhone() {
		return rPhone;
	}

	public void setrPhone(String rPhone) {
		this.rPhone = rPhone;
	}

	public String getParkingYN() {
		return parkingYN;
	}

	public void setParkingYN(String parkingYN) {
		this.parkingYN = parkingYN;
	}

	public int getOpenTime() {
		return openTime;
	}

	public void setOpenTime(int openTime) {
		this.openTime = openTime;
	}

	public int getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(int closeTime) {
		this.closeTime = closeTime;
	}

	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return rName + ","+ type + ","+ location + ","+bestYN
				+ ","+ grade + ","+ description + ","+ category + ","+ rPhone
				+ ","+ parkingYN + ","+ openTime + ","+ closeTime + ","
				+ holiday+","+enrollDate;
	}

}
