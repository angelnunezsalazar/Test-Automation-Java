package testautomation.databasetesting.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mssql.InsertIdentityOperation;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import testautomation.databasetesting.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("jdbc-ctx.xml")
public class EmployeeJDBCTest_ExternalDS_NukePave {

	@Autowired
	EmployeeJDBC employeeJDBC;

	@Autowired
	DataSource dataSource;
	
	@Before
	public void loadData() throws Exception{
		InputStream file = this.getClass().getResourceAsStream("employeeDataSet.xml");
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(file);
		IDatabaseConnection connection = new DatabaseConnection(dataSource.getConnection());
		new InsertIdentityOperation(DatabaseOperation.INSERT).execute(connection,dataSet);
	}
	
	@After
	public void cleanData() throws Exception{
		InputStream file = this.getClass().getResourceAsStream("employeeDataSet.xml");
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(file);
		IDatabaseConnection connection = new DatabaseConnection(dataSource.getConnection());
		DatabaseOperation.DELETE_ALL.execute(connection, dataSet);
	}
	
	@Test
	public void find_withLastNameFilter_returnTheEmployeesWithTheExactLastName() throws Exception {
		List<Employee> employees = employeeJDBC.find("Pacheco", null, null);

		assertEquals(1, employees.size());
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
		int EMPLOYEEID_FROM_DTS=1;
		
		employeeJDBC.delete(EMPLOYEEID_FROM_DTS);

		Employee employeeDeleted=employeeJDBC.get(EMPLOYEEID_FROM_DTS);
		assertNull(employeeDeleted);
	}
}
