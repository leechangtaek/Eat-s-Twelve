package Photo.model.vo;

import java.io.Serializable;

public class Upload implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int upNo;
	private String rName;
	private String location;
	private String originalFileName;
	private String reNamedFileName;
	
	public Upload() {}

	public Upload(int upNo, String rName, String location, String originalFileName, String reNamedFileName) {
		super();
		this.upNo = upNo;
		this.rName = rName;
		this.location = location;
		this.originalFileName = originalFileName;
		this.reNamedFileName = reNamedFileName;
	}

	public int getUpNo() {
		return upNo;
	}

	public void setUpNo(int upNo) {
		this.upNo = upNo;
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

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getReNamedFileName() {
		return reNamedFileName;
	}

	public void setReNamedFileName(String reNamedFileName) {
		this.reNamedFileName = reNamedFileName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return upNo + "," + rName + "," + location + ","
				+ originalFileName + "," + reNamedFileName;
	}
	
	
	
}
