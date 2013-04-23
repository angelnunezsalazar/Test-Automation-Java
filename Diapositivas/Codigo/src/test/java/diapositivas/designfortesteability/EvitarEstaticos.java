package diapositivas.designfortesteability;

public class EvitarEstaticos {

	public void updateCustomer(int id){
		Customer customer=CustomerDAO.get(1);
		customer.setName("New Name");
		CustomerDAO.save(customer);
	}
	
	public static class CustomerDAO{

		public static void save(Customer customer) {
			// TODO Auto-generated method stub
			
		}

		public static Customer get(int i) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	public class Customer{

		public void setName(String string) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
