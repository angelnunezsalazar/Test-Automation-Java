package testautomation.testdoubles;

public interface DataAccess {

	public int getPromotionalDiscount(String couponCode) throws Exception;

	public Order getOrder(int id) throws Exception;

	public void saveOrder(Order order) throws Exception;

}