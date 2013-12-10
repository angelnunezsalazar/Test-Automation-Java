package testautomation.testdoubles;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import testautomation.testdoubles.DataAccessImpl;
import testautomation.testdoubles.Order;
import testautomation.testdoubles.OrderServices;

public class OrderServicesTest_WithoutTestDoubles {
	
	@Test
	public void calculateTotal_WithoutCoupon_ReturnLineItemTotal()
			throws Exception {
		Order order = new Order(1, null, 100);
		OrderServices orderServices = new OrderServices(new DataAccessImpl());

		double total = orderServices.calculateTotal(order);

		assertEquals(100,total,0);
	}

	@Test
	public void calculateTotal_WithCoupon_ReturnLineItemWithDiscount()
			throws Exception {
		Order order = new Order(1, "christmas", 100);
		OrderServices orderServices = new OrderServices(new DataAccessImpl());

		double total = orderServices.calculateTotal(order);

		assertEquals(90,total,0);
	}
	
	@Test
	public void Save_ValidOrder_TheOrderIsPersisted() throws Exception {
		Order order = new Order(1, null, 100);
		order.setTotal(100);
		OrderServices orderProcessor = new OrderServices(new DataAccessImpl());

		orderProcessor.save(order);

		Order orderFromDb = orderProcessor.getOrder(order.getId());
		assertNotNull(orderFromDb);
	}
}
