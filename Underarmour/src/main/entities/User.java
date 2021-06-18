package main.entities;

import java.sql.Date;
import java.sql.Timestamp;

public class User {
	private int id;
	private String name;
	private String email;
	private String password;
	private int status;
	private String status_name;
	private Date bod;
	private String address;
	private String phone_number;
	private String photo;
	private int isLogin;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	//use
	public User(String name, String email, String password, int status, Date bod, String photo, int isLogin) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status;
		this.bod = bod;
		this.photo = photo;
		this.isLogin = isLogin;
	}
	
	public User(String name, String email, String password, int status, Date bod, String phoneNumber, String address, int isLogin) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status;
		this.bod = bod;
		this.phone_number = phoneNumber;
		this.address = address;
		this.isLogin = isLogin;
	}
	
	public User(int id, String name, String email, String password, int status, Date bod, String phoneNumber, String address, int isLogin) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status;
		this.bod = bod;
		this.phone_number = phoneNumber;
		this.address = address;
		this.isLogin = isLogin;
	}
	
	public User(int id, String name, String email, int status, Date bod, String phoneNumber, String address, int isLogin) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.status = status;
		this.bod = bod;
		this.phone_number = phoneNumber;
		this.address = address;
		this.isLogin = isLogin;
	}
	
	public User(int id, String name, String email, String password, int status, Date bod, String address,
			String phone_number, String photo, int isLogin) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status;
		this.bod = bod;
		this.address = address;
		this.phone_number = phone_number;
		this.photo = photo;
		this.isLogin = isLogin;
	}
	
	public User(int id, String name, String email, Date bod, String address,
			String phone_number, String photo) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.bod = bod;
		this.address = address;
		this.phone_number = phone_number;
		this.photo = photo;
	}
	
	public User(int id, String name, String email, String password, int status, String phoneNumber, String address, String photo, int isLogin) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone_number = phoneNumber;
		this.address = address;
		this.status = status;
		this.photo = photo;
		this.isLogin = isLogin;
	}

	//all parameters
	public User(int id, String name, String email, String password, int status, Date bod, String address,
			String phone_number, String photo, int isLogin, Timestamp created_at, Timestamp updated_at) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status;
		this.bod = bod;
		this.address = address;
		this.phone_number = phone_number;
		this.photo = photo;
		this.isLogin = isLogin;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public User(int id, String name, String email, int status, String status_name, Date bod,
			String address, String phone_number, String photo, int isLogin,
			Timestamp created_at, Timestamp updated_at) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.status = status;
		this.status_name = status_name;
		this.bod = bod;
		this.address = address;
		this.phone_number = phone_number;
		this.photo = photo;
		this.isLogin = isLogin;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getBod() {
		return bod;
	}

	public void setBod(Date bod) {
		this.bod = bod;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(int isLogin) {
		this.isLogin = isLogin;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", status="
				+ status + ", bod=" + bod + ", address=" + address + ", phone_number=" + phone_number + ", photo="
				+ photo + ", isLogin=" + isLogin + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
	
}
