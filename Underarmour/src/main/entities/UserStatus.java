package main.entities;

public class UserStatus {
	private int id;
	private String name;
	
	public UserStatus(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public UserStatus(String name) {
		super();
		this.name = name;
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
	
	@Override
	public String toString() {
		return "Status [id=" + id + ", name=" + name + "]";
	}
	
	
}
