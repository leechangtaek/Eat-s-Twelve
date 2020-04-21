package board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	
	private int boardNo;
	private String userId;
	private String rName;
	private String location;
	private String boarding;
	private double grade;
	private Date bDate;
	private int boardCommentLevel;
	private int boardRef;
	private int boardCommentRef;
	
	
	public Board() {}


	public Board(int boardNo, String userId, String rName, String location, String boarding, double grade, Date bDate,
			int boardCommentLevel, int boardRef, int boardCommentRef) {
		super();
		this.boardNo = boardNo;
		this.userId = userId;
		this.rName = rName;
		this.location = location;
		this.boarding = boarding;
		this.grade = grade;
		this.bDate = bDate;
		this.boardCommentLevel = boardCommentLevel;
		this.boardRef = boardRef;
		this.boardCommentRef = boardCommentRef;
	}


	public int getBoardNo() {
		return boardNo;
	}


	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
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


	public double getGrade() {
		return grade;
	}


	public void setGrade(double grade) {
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


	public int getBoardRef() {
		return boardRef;
	}


	public void setBoardRef(int boardRef) {
		this.boardRef = boardRef;
	}


	public int getBoardCommentRef() {
		return boardCommentRef;
	}


	public void setBoardCommentRef(int boardCommentRef) {
		this.boardCommentRef = boardCommentRef;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return boardNo + "," + userId + "," + rName + "," + location
				+ "," + boarding + "," + grade + "," + bDate + ","
				+ boardCommentLevel + "," + boardRef + "," + boardCommentRef;
	}
	
	
	
}
