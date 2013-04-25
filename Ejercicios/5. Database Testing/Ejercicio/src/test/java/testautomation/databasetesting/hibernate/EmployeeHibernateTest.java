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
public class EmployeeHibernateTest {

	@Autowired
	EmployeeHibernate employeeHibernate;

	
}
