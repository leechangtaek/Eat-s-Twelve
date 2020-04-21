package user.model.vo;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String pwd;
	private String name;
	private String email;
	private String address;
	private String phone;
	private int age;
	private String favorite;
	private String gender;
	
	public User() {}

	public User(String userId, String pwd, String name, String email, String address, String phone, int age,
			String favorite, String gender) {
		super();
		this.userId = userId;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.age = age;
		this.favorite = favorite;
		this.gender = gender;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFavorite() {
		return favorite;
	}

	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return userId + ","+ pwd + ","+ name + ","+ email + ","
				+ address + ","+ phone + ","+ age + ","+ favorite + ","+ gender;
	}
	
	
	
	
}
