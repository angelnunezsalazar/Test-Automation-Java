package examples.databasetesting.hibernate;

import java.sql.Date;
import java.util.List;

import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import examples.databasetesting.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:examples/databasetesting/hibernate/hibernate-ctx.xml" })
public class EmployeeHibernateTest {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	EmployeeHibernate employeeHibernate;

	@Test
	public void find_withoutFilters_returnAllEmployees() throws Exception {
		List<Employee> employees = employeeHibernate.find(null, null, null);

		Assert.assertEquals(3, employees.size());
	}
	
	@Test
	public void find_withLastNameFilter_returnTheEmployeesWithTheExactLastName() throws Exception {
		String lastName = "Pacheco";

		List<Employee> employees = employeeHibernate.find(lastName, null, null);

		Assert.assertEquals(1, employees.size());
	}

	@Test
	public void find_withHireDateFilters_returnTheEmployeesBetweenHireDates() throws Exception {
		Date startHireDate = Date.valueOf("2010-12-29");
		Date endHireDate = Date.valueOf("2010-12-30");

		List<Employee> employees = employeeHibernate.find(null, startHireDate, endHireDate);

		Assert.assertEquals(2, employees.size());
	}

	@Test
	public void find_withAllFilters_returnTheEmployeesWithTheExactLastNameAndBetweenTheHiredDates() throws Exception {
		String lastName = "Pacheco";
		Date startHireDate = Date.valueOf("2010-12-29");
		Date endHireDate = Date.valueOf("2010-12-30");

		List<Employee> employees = employeeHibernate.find(lastName, startHireDate,endHireDate);

		Assert.assertEquals(1, employees.size());
	}
	
	@Test
	public void create_theEmployeeIsPersisted() throws Exception {
		Employee employee = new Employee("Luis", "Carranza", Date.valueOf("2010-10-15"));

		employeeHibernate.create(employee);

		Employee employeePersisted=employeeHibernate.get(employee.getId());
		Assert.assertNotNull(employeePersisted);
	}
	
	@Test
	public void delete_theEmployeeIsDeleted() throws Exception {
		employeeHibernate.delete(1);

		Employee employeeDeleted=employeeHibernate.get(1);
		Assert.assertNull(employeeDeleted);
	}
	
//	private void loadData(Object object){
//		Session session=sessionFactory.getCurrentSession();
//		session.save(object);
//	}
//	
//	private void flushAndClear(){
//		Session session=sessionFactory.getCurrentSession();
//		session.flush();
//		session.clear();
//	}
}
