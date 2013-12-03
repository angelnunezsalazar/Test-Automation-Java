package testautomation.coupleddesign;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OrderServicesTest_ManualTestDoubles {
	@Test
	public void calculateTotal_WithoutCoupon_ReturnLineItemTotal()
			throws Exception {
		Order order = new Order(1, null, 100);
		OrderServices orderServices = new OrderServices(new DataAccessImpl());

		double total = orderServices.calculateTotal(order);

		assertEquals(100,total,0);
	}

	@Test
	public void calculateTotal_WithCoupon_ReturnLineItemWithDiscount() throws Exception {
		Order order = new Order(1, "christmas", 100);
		SimpleDataAccess dataAccess=new SimpleDataAccess();
		dataAccess.discountPercentage=10;
		OrderServices orderServices = new OrderServices(dataAccess);

		double total = orderServices.calculateTotal(order);

		assertEquals(90,total,0);
	}
	
	@Test
	public void Save_ValidOrder_TheOrderIsPersisted() throws Exception {
		Order order = new Order(1, null, 100);
		order.setTotal(100);
		SimpleDataAccess dataAccess=new SimpleDataAccess();
		OrderServices orderProcessor = new OrderServices(dataAccess);

		orderProcessor.save(order);

		assertEquals(order,dataAccess.orderSaved);
	}
	
	public class SimpleDataAccess implements DataAccess{

		public int discountPercentage;
		public int getPromotionalDiscount(String couponCode) throws Exception {
			return discountPercentage;
		}

		public Order getOrder(int id) throws Exception {
			return null;
		}

		public Order orderSaved;
		public void saveOrder(Order order) throws Exception {
			orderSaved=order;
		}
	}
}
