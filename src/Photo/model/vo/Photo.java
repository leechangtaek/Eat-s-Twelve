package Photo.model.vo;

import java.io.Serializable;

public class Photo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int imgNo;
	private String rName;
	private String location;
	private String imgName;
	
	public Photo() {}

	public Photo(int imgNo, String rName, String location, String imgName) {
		super();
		this.imgNo = imgNo;
		this.rName = rName;
		this.location = location;
		this.imgName = imgName;
	}

	public int getImgNo() {
		return imgNo;
	}

	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return imgNo + "," + rName + "," + location + "," + imgName;
	}
	
}
