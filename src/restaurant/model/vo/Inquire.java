package restaurant.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Inquire extends Restaurant implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int inquireNo;
	private String userId;
	private Date inquireDate;
	private String acceptYN;
	
	
	public Inquire() {}
	
	public Inquire(int inquireNo, String userId, String rName, String type, String location,String description,
			String category, String rPhone, String parkingYN, int openTime, int closeTime, String holiday, Date inquireDate, String acceptYN) {
	
		super(rName,type,location,description,category,rPhone,parkingYN,openTime,closeTime,holiday);
		this.inquireNo = inquireNo;
		this.userId = userId;
		this.inquireDate = inquireDate;
		this.acceptYN = acceptYN;
	}

	public int getInquireNo() {
		return inquireNo;
	}

	public void setInquireNo(int inquireNo) {
		this.inquireNo = inquireNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getInquireDate() {
		return inquireDate;
	}

	public void setInquireDate(Date inquireDate) {
		this.inquireDate = inquireDate;
	}

	public String getAcceptYN() {
		return acceptYN;
	}

	public void setAcceptYN(String acceptYN) {
		this.acceptYN = acceptYN;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return inquireNo + ","+ userId + ","+ super.toString()+"," + inquireDate + ","
				+ acceptYN;
	}
	
	
	
}
