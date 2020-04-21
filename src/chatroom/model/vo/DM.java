package chatroom.model.vo;

import java.io.Serializable;

public class DM implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int dmNo;
	private String userId;
	private String msg;
	private String sender;
	private String time;
	private String check_yn;
	
	public DM () {}

	public DM(int dmNo, String userId, String msg, String sender, String time, String check_yn) {
		super();
		this.dmNo = dmNo;
		this.userId = userId;
		this.msg = msg;
		this.sender = sender;
		this.time = time;
		this.check_yn = check_yn;
	}

	public int getDmNo() {
		return dmNo;
	}

	public void setDmNo(int dmNo) {
		this.dmNo = dmNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCheck_yn() {
		return check_yn;
	}

	public void setCheck_yn(String check_yn) {
		this.check_yn = check_yn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "DM [dmNo=" + dmNo + ", userId=" + userId + ", msg=" + msg + ", sender=" + sender + ", time=" + time
				+ ", check_yn=" + check_yn + "]";
	}
	
}
