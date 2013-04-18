package examples.databasetesting.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import examples.databasetesting.Employee;


@Repository
public class EmployeeJDBC {

	@Autowired
	DataSource datasource;

	public List<Employee> find(String lastName, Date startHireDate,
			Date endHireDate) throws Exception {
		Connection connection = datasource.getConnection();
		String sql = "select Id, FirstName, LastName, HireDate from Employees";
		String filters = "";
		if (lastName != null) {
			filters += " where LastName='" + lastName + "'";
		}
		if (startHireDate != null && endHireDate != null) {
			filters += filters.equals("") ? " where " : " and ";
			filters += "HireDate between '" + startHireDate + "' and '"
					+ endHireDate + "'";
		}
		PreparedStatement statement = connection
				.prepareStatement(sql + filters);

		ResultSet resultSet = statement.executeQuery();
		List<Employee> employees = new ArrayList<Employee>();
		while (resultSet.next()) {
			Employee employee = new Employee(resultSet.getString(2),
					resultSet.getString(3), resultSet.getDate(4));
			employee.setId(resultSet.getInt(1));
			employees.add(employee);
		}

		resultSet.close();
		statement.close();
		connection.close();
		return employees;
	}

	public Employee get(int id) throws Exception {
		Connection connection = datasource.getConnection();
		PreparedStatement statement = connection
				.prepareStatement("select Id, FirstName, LastName, HireDate from Employees where Id=?");
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();
		Employee employee = null;
		if (resultSet.next()) {
			employee = new Employee(resultSet.getString(2),
					resultSet.getString(3), resultSet.getDate(4));
			employee.setId(resultSet.getInt(1));
		}

		resultSet.close();
		statement.close();
		connection.close();
		return employee;
	}

	public void create(Employee employee) throws Exception {
		Connection connection = datasource.getConnection();
		PreparedStatement statement = connection.prepareStatement(
				"insert into Employees(FirstName,LastName,HireDate) values(?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, employee.getFirstName());
		statement.setString(2, employee.getLastName());
		statement.setDate(3, employee.getHireDate());

		int rowsAffected = statement.executeUpdate();
		if (rowsAffected != 0) {
			ResultSet keys = statement.getGeneratedKeys();
			keys.next();
			employee.setId(keys.getInt(1));
			keys.close();
		} else {
			throw new Exception("Employee not created");
		}
		
		statement.close();
		connection.close();
	}

	public void delete(int id) throws Exception {
		Connection connection = datasource.getConnection();
		PreparedStatement statement = connection
				.prepareStatement("delete from Employees where Id=?");
		statement.setInt(1, id);

		int rowsAffected = statement.executeUpdate();
		if (rowsAffected == 0)
			throw new Exception("Employee not deleted");
		statement.close();
		connection.close();
	}
}
