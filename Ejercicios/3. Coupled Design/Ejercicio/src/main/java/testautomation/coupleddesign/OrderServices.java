package testautomation.coupleddesign;

import java.math.BigDecimal;

public class OrderServices {
	private DataAccess dataAccess;

	public OrderServices() {
		dataAccess = new DataAccess();
	}

	public BigDecimal calculateTotal(Order order) throws Exception{
		BigDecimal itemTotal = order.getItemTotal();
		
		int discountPercentage = 0;
        if (order.getCouponCode()!=null){
            discountPercentage = dataAccess.getPromotionalDiscount(order.getCouponCode());
        }

        // Ejm: 50 * 10/100 = 45
        BigDecimal discount=itemTotal.multiply(new BigDecimal(discountPercentage)
        							 .divide(new BigDecimal(100)));
        return itemTotal.subtract(discount);
    }
	
    public Order getOrder(int id) throws Exception{
        return dataAccess.getOrder(id);
    }

    public void save(Order order) throws Exception{
        if (!isValid(order)){
            throw new Exception("Invalid Order");
        }
        dataAccess.saveOrder(order);
    }

    private boolean isValid(Order order){
        return order.getId() > 0 &&
        	   order.getItemTotal().compareTo(BigDecimal.ZERO) == 1 && 
        	   order.getTotal().compareTo(BigDecimal.ZERO)== 1;
    }
}
