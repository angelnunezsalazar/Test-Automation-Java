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

public class EmployeeJDBCTest {

	@Autowired
	EmployeeJDBC employeeJDBC;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void LoadData(Employee employee) throws Exception {
		employeeJDBC.create(employee);
	}
}
