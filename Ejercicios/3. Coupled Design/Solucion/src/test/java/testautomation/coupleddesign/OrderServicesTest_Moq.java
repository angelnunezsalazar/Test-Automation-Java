package testautomation.coupleddesign;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class OrderServicesTest_Moq {
	
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
		DataAccess dataAccess=mock(DataAccessImpl.class);
		when(dataAccess.getPromotionalDiscount("christmas")).thenReturn(10);
		OrderServices orderServices = new OrderServices(dataAccess);

		double total = orderServices.calculateTotal(order);

		assertEquals(90,total,0);
	}
	
	@Test
	public void Save_ValidOrder_TheOrderIsPersisted() throws Exception {
		Order order = new Order(1, null, 100);
		order.setTotal(100);
		DataAccessImpl dataAccess=mock(DataAccessImpl.class);
		OrderServices orderProcessor = new OrderServices(dataAccess);

		orderProcessor.save(order);

		verify(dataAccess).saveOrder(order);
	}
}
