package examples.databasetesting.jdbc;

import java.io.File;
import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mssql.InsertIdentityOperation;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import examples.databasetesting.Employee;
import examples.databasetesting.jdbc.EmployeeJDBC;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:examples/databasetesting/jdbc/jdbc-ctx.xml" })
public class EmployeeJDBCTest {

	@Autowired
	EmployeeJDBC employeeJDBC;

	@Autowired
	DataSource dataSource;

	@Before
	public void setup() throws Exception {
		File file = new File("src/test/resources/examples/databasetesting/jdbc/employeeDataSet.xml");
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(file);
		IDatabaseConnection connection = new DatabaseConnection(dataSource.getConnection());
		//DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
		DatabaseOperation operation = new InsertIdentityOperation(DatabaseOperation.CLEAN_INSERT);		
		operation.execute(connection, dataSet);
	}

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

	@Test
	public void find_withAllFilters_returnTheEmployeesWithTheExactLastNameAndBetweenTheHiredDates() throws Exception {
		String lastName = "Pacheco";
		Date startHireDate = Date.valueOf("2010-12-29");
		Date endHireDate = Date.valueOf("2010-12-30");

		List<Employee> employees = employeeJDBC.find(lastName, startHireDate,endHireDate);

		Assert.assertEquals(1, employees.size());
	}

	@Test
	public void create_theEmployeeIsPersisted() throws Exception {
		Employee employee = new Employee("Luis", "Carranza", Date.valueOf("2010-10-15"));

		employeeJDBC.create(employee);

		Employee employeePersisted=employeeJDBC.get(employee.getId());
		Assert.assertNotNull(employeePersisted);
	}
	
	@Test
	public void delete_theEmployeeIsDeleted() throws Exception {
		employeeJDBC.delete(1);

		Employee employeeDeleted=employeeJDBC.get(1);
		Assert.assertNull(employeeDeleted);
	}
}
