package main.entities;

public class ProductCart {
	//Merge Product and Cart - Entity
	
	private Product product;
	private String size;
	private int quantity;
	public ProductCart(Product product, String size, int quantity) {
		super();
		this.product = product;
		this.size = size;
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "ProductCart [product=" + product + ", size=" + size + ", quantity=" + quantity + "]";
	}
	
	
}
