package main.entities;

import java.sql.Timestamp;

public class Photo {
	private int id;
	private int product_id;
	private String photo_01;
	private String photo_02;
	private String photo_03;
	private String photo_04;
	private String photo_05;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	public Photo(int id, int product_id, String photo_01, String photo_02, String photo_03, String photo_04, String photo_05, 
			Timestamp created_at,
			Timestamp updated_at) {
		super();
		this.id = id;
		this.product_id = product_id;
		this.photo_01 = photo_01;
		this.photo_02 = photo_02;
		this.photo_03 = photo_03;
		this.photo_04 = photo_04;
		this.photo_05 = photo_05;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public Photo(int id, int product_id, String photo_01, String photo_02, String photo_03, String photo_04, String photo_05) {
		super();
		this.id = id;
		this.product_id = product_id;
		this.photo_01 = photo_01;
		this.photo_02 = photo_02;
		this.photo_03 = photo_03;
		this.photo_04 = photo_04;
		this.photo_05 = photo_05;
	}
	
	public Photo(int product_id, String photo_01, String photo_02, String photo_03, String photo_04, String photo_05) {
		super();
		this.product_id = product_id;
		this.photo_01 = photo_01;
		this.photo_02 = photo_02;
		this.photo_03 = photo_03;
		this.photo_04 = photo_04;
		this.photo_05 = photo_05;
	}
	
	public Photo(int product_id, String photo_01) {
		super();
		this.product_id = product_id;
		this.photo_01 = photo_01;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getPhoto_01() {
		return photo_01;
	}

	public void setPhoto_01(String photo_01) {
		this.photo_01 = photo_01;
	}

	public String getPhoto_02() {
		return photo_02;
	}

	public void setPhoto_02(String photo_02) {
		this.photo_02 = photo_02;
	}

	public String getPhoto_03() {
		return photo_03;
	}

	public void setPhoto_03(String photo_03) {
		this.photo_03 = photo_03;
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

	public String getPhoto_04() {
		return photo_04;
	}

	public void setPhoto_04(String photo_04) {
		this.photo_04 = photo_04;
	}

	public String getPhoto_05() {
		return photo_05;
	}

	public void setPhoto_05(String photo_05) {
		this.photo_05 = photo_05;
	}

	@Override
	public String toString() {
		return "Photo [id=" + id + ", product_id=" + product_id + ", photo_01=" + photo_01 + ", photo_02=" + photo_02
				+ ", photo_03=" + photo_03 + ", photo_04=" + photo_04 + ", photo_05=" + photo_05 + ", created_at="
				+ created_at + ", updated_at=" + updated_at + "]";
	}
	
}
