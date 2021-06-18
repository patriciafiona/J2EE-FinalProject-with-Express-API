package main.entities;

import java.sql.Timestamp;

public class Product {
	private int id;
	private String name;
	private int category;
	private String category_name;
	private int tag;
	private String tag_name;
	private double rating;
	private double price;
	private int stock;
	private String color;
	private String description;
	private String photo_01;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	public Product(int id) {
		super();
		this.id = id;
	}
	
	public Product(int id, String name, int category, int tag, double rating,
			double price, int stock, String color, String description) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.tag = tag;
		this.rating = rating;
		this.price = price;
		this.stock = stock;
		this.color = color;
		this.description = description;
	}
	
	public Product(String name, int category, int tag, double rating, double price,
			int stock, String color, String description, Timestamp created_at, Timestamp updated_at) {
		super();
		this.name = name;
		this.category = category;
		this.tag = tag;
		this.rating = rating;
		this.price = price;
		this.stock = stock;
		this.color = color;
		this.description = description;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public Product(String name, int category, int tag, double rating, double price,
			int stock, String color, String description) {
		super();
		this.name = name;
		this.category = category;
		this.tag = tag;
		this.rating = rating;
		this.price = price;
		this.stock = stock;
		this.color = color;
		this.description = description;
	}

	public Product(int id, String name, int category, String category_name, int tag, String tag_name, double rating,
			double price, int stock, String color, String description, String photo_01, Timestamp created_at, Timestamp updated_at) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.category_name = category_name;
		this.tag = tag;
		this.tag_name = tag_name;
		this.rating = rating;
		this.price = price;
		this.stock = stock;
		this.color = color;
		this.description = description;
		this.photo_01 = photo_01;
		this.created_at = created_at;
		this.updated_at = updated_at;
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

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public String getTag_name() {
		return tag_name;
	}

	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", category_name=" + category_name
				+ ", tag=" + tag + ", tag_name=" + tag_name + ", rating=" + rating + ", price=" + price + ", stock="
				+ stock + ", color=" + color + ", description=" + description + ", created_at=" + created_at
				+ ", updated_at=" + updated_at + "]";
	}

	public String getPhoto_01() {
		return photo_01;
	}

	public void setPhoto_01(String photo_01) {
		this.photo_01 = photo_01;
	}
	
}
