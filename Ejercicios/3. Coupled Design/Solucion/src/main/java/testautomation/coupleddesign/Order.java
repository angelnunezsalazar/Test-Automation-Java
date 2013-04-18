package testautomation.coupleddesign;

import java.math.BigDecimal;

public class Order {

	public Order(int id, String couponCode, BigDecimal itemTotal) {
		this.id = id;
		this.couponCode = couponCode;
		this.itemTotal = itemTotal;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public BigDecimal getItemTotal() {
		return itemTotal;
	}
	public void setItemTotal(BigDecimal itemTotal) {
		this.itemTotal = itemTotal;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	private int id;
	private String couponCode;
	private BigDecimal itemTotal;
	private BigDecimal total;
}
