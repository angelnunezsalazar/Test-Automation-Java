package testautomation.testdoubles;

public class Order {

	public Order() {}
	
	public Order(int id, String coupon, double itemTotal) {
		this.id = id;
		this.coupon=coupon;
		this.setItemTotal(itemTotal);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public double getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(double itemTotal) {
		this.itemTotal = itemTotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	private int id;
	private String coupon;
	private double itemTotal;
	private double total;
}
