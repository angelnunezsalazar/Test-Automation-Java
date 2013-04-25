package testautomation.databasetesting.hibernate;

import java.sql.Date;
import java.util.List;

import testautomation.databasetesting.Employee;

import static junit.framework.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("hibernate-ctx.xml")
@Transactional
//@TransactionConfiguration(defaultRollback=true)
public class EmployeeHibernateTest {

	@Autowired
	EmployeeHibernate employeeHibernate;
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Test
	public void find_withLastNameFilter_returnTheEmployeesWithTheExactLastName() throws Exception {
		Employee employee1=new Employee("Pacheco", Date.valueOf("2012-12-29"));
		loadData(employee1);
		Employee employee2=new Employee("Quispe", Date.valueOf("2012-12-30"));
		loadData(employee2);
		flushAndClear();
		
		List<Employee> employees = employeeHibernate.find("Pacheco", null, null);

		assertEquals(1, employees.size());
	}

	@Test
	public void find_withHireDateFilters_returnTheEmployeesBetweenHireDates() throws Exception {
		Employee employee1=new Employee("Pacheco", Date.valueOf("2012-12-28"));
		loadData(employee1);
		Employee employee2=new Employee("Quispe", Date.valueOf("2012-12-29"));
		loadData(employee2);
		Employee employee3=new Employee("Tovar", Date.valueOf("2012-12-30"));
		loadData(employee3);
		flushAndClear();
		
		List<Employee> employees = employeeHibernate.find(null, Date.valueOf("2012-12-29"), Date.valueOf("2012-12-30"));

		assertEquals(2, employees.size());
	}

	@Test
	public void find_withAllFilters_returnTheEmployeesWithTheExactLastNameAndBetweenTheHiredDates() throws Exception {
		Employee employee1=new Employee("Pacheco", Date.valueOf("2012-12-28"));
		loadData(employee1);
		Employee employee2=new Employee("Quispe", Date.valueOf("2012-12-29"));
		loadData(employee2);
		Employee employee3=new Employee("Tovar", Date.valueOf("2012-12-30"));
		loadData(employee3);		
		flushAndClear();
		
		List<Employee> employees = employeeHibernate.find("Quispe", Date.valueOf("2012-12-29"),Date.valueOf("2012-12-30"));

		assertEquals(1, employees.size());
	}
	
	@Test
	public void create_theEmployeeIsPersisted() throws Exception {
		Employee employee = new Employee("Carranza", Date.valueOf("2012-10-15"));

		employeeHibernate.create(employee);
		flushAndClear();

		Employee employeePersisted=employeeHibernate.get(employee.getId());
		assertNotNull(employeePersisted);
	}
	
	@Test
	public void delete_theEmployeeIsDeleted() throws Exception {
		Employee employee=new Employee("Pacheco", Date.valueOf("2012-12-30"));
		loadData(employee);
		flushAndClear();
		
		employeeHibernate.delete(employee.getId());
		flushAndClear();

		Employee employeeDeleted=employeeHibernate.get(employee.getId());
		assertNull(employeeDeleted);
	}
	
	private void loadData(Object object){
		Session session=sessionFactory.getCurrentSession();
		session.save(object);
	}
	
	private void flushAndClear(){
		Session session=sessionFactory.getCurrentSession();
		session.flush();
		session.clear();
	}
}
