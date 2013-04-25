package testautomation.databasetesting.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import testautomation.databasetesting.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("jdbc-ctx.xml")
@Transactional
//@TransactionConfiguration(defaultRollback=true)
public class EmployeeJDBCTest_SelfContained_Transaction {

	@Autowired
	EmployeeJDBC employeeJDBC;
	
	@Test
	public void find_withoutFilters_returnAllEmployees() throws Exception {
		LoadData(new Employee("Pacheco", Date.valueOf("2012-12-28")));
		LoadData(new Employee("Carranza", Date.valueOf("2012-12-29")));
		LoadData(new Employee("Rodriduez", Date.valueOf("2012-12-30")));
		
		List<Employee> employees = employeeJDBC.find(null, null, null);

		assertEquals(3, employees.size());
	}

	@Test
	public void find_withLastNameFilter_returnTheEmployeesWithTheExactLastName() throws Exception {
		LoadData(new Employee("Pacheco", Date.valueOf("2012-12-28")));
		LoadData(new Employee("Carranza", Date.valueOf("2012-12-29")));
		
		List<Employee> employees = employeeJDBC.find("Pacheco", null, null);

		assertEquals(1, employees.size());
	}

	@Test
	public void find_withHireDateFilters_returnTheEmployeesBetweenHireDates() throws Exception {
		LoadData(new Employee("Pacheco", Date.valueOf("2012-12-28")));
		LoadData(new Employee("Carranza", Date.valueOf("2012-12-29")));
		LoadData(new Employee("Rodriduez", Date.valueOf("2012-12-30")));
		
		List<Employee> employees = employeeJDBC.find(null, Date.valueOf("2012-12-29"), Date.valueOf("2012-12-30"));

		assertEquals(2, employees.size());
	}
	
	@Test
	public void create_theEmployeeIsPersisted() throws Exception {
		Employee employee = new Employee("Carranza", Date.valueOf("2012-10-15"));

		employeeJDBC.create(employee);

		Employee employeePersisted=employeeJDBC.get(employee.getId());
		assertNotNull(employeePersisted);
	}

	@Test
	public void delete_theEmployeeIsDeleted() throws Exception {
		Employee employee = new Employee("Carranza", Date.valueOf("2012-12-29"));
		LoadData(employee);
		
		employeeJDBC.delete(employee.getId());

		Employee employeeDeleted=employeeJDBC.get(employee.getId());
		assertNull(employeeDeleted);
	}
	
	private void LoadData(Employee employee) throws Exception {
		employeeJDBC.create(employee);
	}
}
