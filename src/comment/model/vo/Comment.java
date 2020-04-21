package comment.model.vo;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int boardNo; 
	private String id;
	private String rName; 
	private String location;
	private String boarding; //
	private int grade; //
	private Date bDate; 
	private int boardCommentLevel; 
	private int boardCommentRef;
	private String originalFileName; //
	private String renamedFileName; //
	
	
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(int boardNo, String id, String rName, String location, String boarding, int grade, Date bDate,
			int boardCommentLevel, int boardCommentRef, String originalFileName, String renamedFileName) {
		super();
		this.boardNo = boardNo;
		this.id = id;
		this.rName = rName;
		this.location = location;
		this.boarding = boarding;
		this.grade = grade;
		this.bDate = bDate;
		this.boardCommentLevel = boardCommentLevel;
		this.boardCommentRef = boardCommentRef;
		this.originalFileName = originalFileName;
		this.renamedFileName = renamedFileName;
	}

	
	
	
	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getBoarding() {
		return boarding;
	}

	public void setBoarding(String boarding) {
		this.boarding = boarding;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Date getbDate() {
		return bDate;
	}

	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}

	public int getBoardCommentLevel() {
		return boardCommentLevel;
	}

	public void setBoardCommentLevel(int boardCommentLevel) {
		this.boardCommentLevel = boardCommentLevel;
	}

	public int getBoardCommentRef() {
		return boardCommentRef;
	}

	public void setBoardCommentRef(int boardCommentRef) {
		this.boardCommentRef = boardCommentRef;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getRenamedFileName() {
		return renamedFileName;
	}

	public void setRenamedFileName(String renamedFileName) {
		this.renamedFileName = renamedFileName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Comment [boardNo=" + boardNo + ", id=" + id + ", rName=" + rName + ", location=" + location
				+ ", boarding=" + boarding + ", grade=" + grade + ", bDate=" + bDate + ", boardCommentLevel="
				+ boardCommentLevel + ", boardCommentRef=" + boardCommentRef + ", originalFileName=" + originalFileName
				+ ", renamedFileName=" + renamedFileName + "]";
	}
	
	
	
}
