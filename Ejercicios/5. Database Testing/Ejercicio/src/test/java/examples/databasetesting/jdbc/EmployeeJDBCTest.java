package examples.databasetesting.jdbc;

import java.sql.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import examples.databasetesting.Employee;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:examples/databasetesting/jdbc/jdbc-ctx.xml" })
public class EmployeeJDBCTest {

	@Autowired
	EmployeeJDBC employeeJDBC;

	@Test
	public void find_withoutFilters_returnAllEmployees() throws Exception {
		List<Employee> employees = employeeJDBC.find(null, null, null);

		Assert.assertEquals(3, employees.size());
	}

	@Test
	public void find_withLastNameFilter_returnTheEmployeesWithTheExactLastName() throws Exception {
		String lastName = "Pacheco";

		List<Employee> employees = employeeJDBC.find(lastName, null, null);

		Assert.assertEquals(1, employees.size());
	}

	@Test
	public void find_withHireDateFilters_returnTheEmployeesBetweenHireDates() throws Exception {
		Date startHireDate = Date.valueOf("2010-12-29");
		Date endHireDate = Date.valueOf("2010-12-30");

		List<Employee> employees = employeeJDBC.find(null, startHireDate, endHireDate);

		Assert.assertEquals(2, employees.size());
	}
}
