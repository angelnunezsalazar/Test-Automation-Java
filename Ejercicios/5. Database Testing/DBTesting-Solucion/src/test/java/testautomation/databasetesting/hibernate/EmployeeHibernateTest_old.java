package testautomation.databasetesting.hibernate;

import java.sql.Date;
import java.util.List;

import testautomation.databasetesting.Employee;

import junit.framework.Assert;

//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//import org.springframework.transaction.annotation.Transactional;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:examples/databasetesting/hibernate/hibernate-ctx.xml" })
//@TransactionConfiguration(defaultRollback=true)
//@Transactional
public class EmployeeHibernateTest_old {

//	@Autowired
//	EmployeeHibernate employeeHibernate;
//	
//	@Autowired
//	SessionFactory sessionFactory;
//	
//	@Test
//	public void find_withLastNameFilter_returnTheEmployeesWithTheExactLastName() throws Exception {
//		Employee employee1=new Employee("Luis", "Pacheco", Date.valueOf("2010-12-30"));
//		loadData(employee1);
//		Employee employee2=new Employee("Luis", "Quispe", Date.valueOf("2010-12-29"));
//		loadData(employee2);
//		flushAndClear();
//		
//		String lastName = "Pacheco";
//
//		List<Employee> employees = employeeHibernate.find(lastName, null, null);
//
//		Assert.assertEquals(1, employees.size());
//	}
//
//	@Test
//	public void find_withHireDateFilters_returnTheEmployeesBetweenHireDates() throws Exception {
//		Employee employee1=new Employee("Luis", "Pacheco", Date.valueOf("2010-12-30"));
//		loadData(employee1);
//		Employee employee2=new Employee("Luis", "Quispe", Date.valueOf("2010-12-29"));
//		loadData(employee2);
//		Employee employee3=new Employee("Luis", "Tovar", Date.valueOf("2010-12-28"));
//		loadData(employee3);
//		flushAndClear();
//		
//		Date startHireDate = Date.valueOf("2010-12-29");
//		Date endHireDate = Date.valueOf("2010-12-30");
//
//		List<Employee> employees = employeeHibernate.find(null, startHireDate, endHireDate);
//
//		Assert.assertEquals(2, employees.size());
//	}
//
//	@Test
//	public void find_withAllFilters_returnTheEmployeesWithTheExactLastNameAndBetweenTheHiredDates() throws Exception {
//		Employee employee1=new Employee("Luis", "Pacheco", Date.valueOf("2010-12-30"));
//		loadData(employee1);
//		Employee employee2=new Employee("Luis", "Quispe", Date.valueOf("2010-12-29"));
//		loadData(employee2);
//		Employee employee3=new Employee("Luis", "Tovar", Date.valueOf("2010-12-28"));
//		loadData(employee3);		
//		flushAndClear();
//		
//		String lastName = "Pacheco";
//		Date startHireDate = Date.valueOf("2010-12-29");
//		Date endHireDate = Date.valueOf("2010-12-30");
//
//		List<Employee> employees = employeeHibernate.find(lastName, startHireDate,endHireDate);
//
//		Assert.assertEquals(1, employees.size());
//	}
//	
//	@Test
//	public void create_theEmployeeIsPersisted() throws Exception {
//		Employee employee = new Employee("Luis", "Carranza", Date.valueOf("2010-10-15"));
//
//		employeeHibernate.create(employee);
//		flushAndClear();
//
//		Employee employeePersisted=employeeHibernate.get(employee.getId());
//		Assert.assertNotNull(employeePersisted);
//	}
//	
//	@Test
//	public void delete_theEmployeeIsDeleted() throws Exception {
//		Employee employee=new Employee("Luis", "Pacheco", Date.valueOf("2010-12-30"));
//		loadData(employee);
//		flushAndClear();
//		
//		employeeHibernate.delete(employee.getId());
//		flushAndClear();
//
//		Employee employeeDeleted=employeeHibernate.get(employee.getId());
//		Assert.assertNull(employeeDeleted);
//	}
//	
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
