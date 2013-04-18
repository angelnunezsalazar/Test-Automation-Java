package testautomation.coupleddesign;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class OrderServicesTest_ManualTestDoubles {
	@Test
	public void calculateTotal_WithoutCoupon_ReturnLineItemTotal()
			throws Exception {
		Order order = new Order(1, null, new BigDecimal(100));
		OrderServices orderServices = new OrderServices(new DataAccessImpl());

		BigDecimal total = orderServices.calculateTotal(order);

		assertTrue(total.compareTo(new BigDecimal(100))==0);
	}

	@Test
	public void calculateTotal_WithCoupon_ReturnLineItemWithDiscount() throws Exception {
		Order order = new Order(1, "CHRISTMAS", new BigDecimal(100));
		SimpleDataAccess dataAccess=new SimpleDataAccess();
		dataAccess.discountPercentage=10;
		OrderServices orderServices = new OrderServices(dataAccess);

		BigDecimal total = orderServices.calculateTotal(order);

		assertTrue(total.compareTo(new BigDecimal(90))==0);
	}
	
	@Test
	public void Save_ValidOrder_TheOrderIsPersisted() throws Exception {
		Order order = new Order(1, null, new BigDecimal(100));
		order.setTotal(new BigDecimal(100));
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
