package testautomation.coupleddesign;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class OrderServicesTest {
	@Test
	public void calculateTotal_WithoutCoupon_ReturnLineItemTotal()
			throws Exception {
		Order order = new Order(1, null, new BigDecimal(100));
		OrderServices orderServices = new OrderServices();

		BigDecimal total = orderServices.calculateTotal(order);

		assertTrue(total.compareTo(new BigDecimal(100))==0);
	}

	@Test
	public void calculateTotal_WithCoupon_ReturnLineItemWithDiscount()
			throws Exception {
		Order order = new Order(1, "CHRISTMAS", new BigDecimal(100));
		OrderServices orderServices = new OrderServices();

		BigDecimal total = orderServices.calculateTotal(order);

		assertTrue(total.compareTo(new BigDecimal(90))==0);
	}
	
	@Test
	public void Save_ValidOrder_TheOrderIsPersisted() throws Exception {
		Order order = new Order(1, null, new BigDecimal(100));
		order.setTotal(new BigDecimal(100));
		OrderServices orderProcessor = new OrderServices();

		orderProcessor.save(order);

		Order orderFromDb = orderProcessor.getOrder(order.getId());
		assertNotNull(orderFromDb);
	}
}
