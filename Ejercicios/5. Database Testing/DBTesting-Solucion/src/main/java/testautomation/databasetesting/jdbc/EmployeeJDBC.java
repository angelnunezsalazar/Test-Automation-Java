package testautomation.databasetesting.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import testautomation.databasetesting.Employee;


@Repository
public class EmployeeJDBC {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Employee> find(String lastName, Date startHireDate, Date endHireDate) throws Exception {
		String queryBase = "select Id, LastName, HireDate from Employees";
		String queryFilters = "";
		if (lastName != null) {
			queryFilters += " where LastName='" + lastName + "'";
		}
		if (startHireDate != null && endHireDate != null) {
			queryFilters += queryFilters.equals("") ? " where " : " and ";
			queryFilters += "HireDate between '" + startHireDate + "' and '" + endHireDate + "'";
		}

		List<Employee> employees = jdbcTemplate.query(queryBase + queryFilters, new BeanPropertyRowMapper<Employee>(Employee.class));
		return employees;
	}

	public Employee get(int id) {
		List<Employee> employees = jdbcTemplate.query("select Id, LastName, HireDate from Employees where Id=?", new Object[] { id },
				new BeanPropertyRowMapper<Employee>(Employee.class));
		if (employees.isEmpty())
			return null;
		else
			return employees.get(0);
	}

	public void create(final Employee employee) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "insert into Employees(LastName,HireDate) values(?, ?)";
				PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, employee.getLastName());
				statement.setDate(2, employee.getHireDate());
				return statement;
			}
		};
		jdbcTemplate.update(psc, keyHolder);
		employee.setId(keyHolder.getKey().intValue());
	}

	public void delete(int id) {
		jdbcTemplate.update("delete from Employees where Id=?", new Object[] { id });
	}
}