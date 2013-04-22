package testautomation.databasetesting.jdbc;

import static org.junit.Assert.*;

import java.io.File;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
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
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import testautomation.databasetesting.Employee;
import testautomation.databasetesting.jdbc.EmployeeJDBC;

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
}
