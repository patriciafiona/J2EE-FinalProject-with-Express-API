package main.entities;

public class Cart{
	private int productId;
	private String size;
	private int quantity;
	
	public Cart(int productId, String size, int quantity) {
		this.productId = productId;
		this.quantity = quantity;
		this.size = size;
	}
	
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Cart >>> Product ID: "+ productId
						+ "\n"
						+ "Quantity: "+ quantity;
	}
}
