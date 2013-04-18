package testautomation.coupleddesign;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DataAccessImpl implements DataAccess {

	public int getPromotionalDiscount(String couponCode) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("select discount from CouponDiscount where coupon=?");
			statement.setString(1, couponCode);

			resultSet = statement.executeQuery();
			int discount = 0;
			if (resultSet.next()) {
				discount = resultSet.getInt(1);
			}
			return discount;
		} catch (Exception e) {
			throw e;
		} finally {
			if(resultSet!=null) {resultSet.close();}
			if(statement!=null) {statement.close();}
			if(connection!=null) {connection.close();}
		}
	}

	public Order getOrder(int id) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("select Id, CouponCode, ItemTotal, Total from \"ORDER\" where Id=?");
			statement.setInt(1, id);

			resultSet = statement.executeQuery();
			Order order = null;
			if (resultSet.next()) {
				order = new Order(resultSet.getInt(1), resultSet.getString(2),resultSet.getBigDecimal(3));
				order.setTotal(resultSet.getBigDecimal(4));
			}
			return order;
		} catch (Exception e) {
			throw e;
		} finally {
			if(resultSet!=null) {resultSet.close();}
			if(statement!=null) {statement.close();}
			if(connection!=null) {connection.close();}
		}
	}

	public void saveOrder(Order order) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("insert into \"ORDER\" values(?, ?, ?, ?)");
			statement.setInt(1, order.getId());
			statement.setString(2, order.getCouponCode());
			statement.setBigDecimal(3, order.getItemTotal());
			statement.setBigDecimal(4, order.getTotal());

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected == 0)
				throw new Exception("Order not saved");
		} catch (Exception e) {
			throw e;
		} finally {
			if(statement!=null) {statement.close();}
			if(connection!=null) {connection.close();}
		}
	}

	private Connection getConnection() throws Exception {
		Class.forName("org.h2.Driver");
		Properties properties = new Properties();
		InputStream stream = getClass().getResourceAsStream("/connection.properties");
		properties.load(stream);
		Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"),"");
		return connection;
	}
}
