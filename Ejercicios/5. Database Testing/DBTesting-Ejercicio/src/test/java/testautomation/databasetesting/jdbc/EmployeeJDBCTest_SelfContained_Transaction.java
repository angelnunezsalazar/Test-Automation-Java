package testautomation.databasetesting.jdbc;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import testautomation.databasetesting.Employee;
import testautomation.databasetesting.jdbc.EmployeeJDBC;

public class EmployeeJDBCTest_SelfContained_Transaction {

	@Autowired
	EmployeeJDBC employeeJDBC;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void LoadData(Employee employee) throws Exception {
		employeeJDBC.create(employee);
	}
}
