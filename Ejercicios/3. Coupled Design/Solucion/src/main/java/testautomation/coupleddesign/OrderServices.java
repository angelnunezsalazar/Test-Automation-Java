package testautomation.coupleddesign;

public class OrderServices {

	private DataAccess dataAccess;

	public OrderServices(DataAccess dataAccess) {
		this.dataAccess = dataAccess;
	}

	public double calculateTotal(Order order) throws Exception {
		double itemTotal = order.getItemTotal();

		int discountPercentage = 0;
		if (order.getCoupon() != null) {
			discountPercentage = dataAccess.getPromotionalDiscount(order
					.getCoupon());
		}

		double discount = itemTotal * discountPercentage / 100;
		// Ejm: 50 * 10/100 = 45
		return itemTotal - discount;
	}

	public Order getOrder(int id) throws Exception {
		return dataAccess.getOrder(id);
	}

	public void save(Order order) throws Exception {
		if (!isValid(order)) {
			throw new Exception("Invalid Order");
		}
		dataAccess.saveOrder(order);
	}

	private boolean isValid(Order order) {
		return order.getId() > 0 && order.getItemTotal() != 0
				&& order.getTotal() != 0;
	}
}
