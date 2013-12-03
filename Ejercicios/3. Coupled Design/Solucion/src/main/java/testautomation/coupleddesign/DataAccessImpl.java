package testautomation.coupleddesign;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameMappingStrategy;

public class DataAccessImpl implements DataAccess {

	public int getPromotionalDiscount(String couponCode) throws Exception {
		CSVReader reader = null;
		try {
			reader = createCSVReader("discounts.txt");
			HeaderColumnNameMappingStrategy<Discount> strategy = new HeaderColumnNameMappingStrategy<Discount>();
			strategy.setType(Discount.class);
			CsvToBean<Discount> csv = new CsvToBean<Discount>();
			List<Discount> discounts = csv.parse(strategy, reader);
			for (Discount discount : discounts) {
				if (discount.getCoupon().equals(couponCode)) {
					return discount.getPercentage();
				}
			}
			throw new Exception("Coupon not found");
		} finally {
			if (reader!=null) {
				reader.close();
			}
		}
	}

	public Order getOrder(int id) throws Exception {
		CSVReader reader = null;
		try {
			reader = createCSVReader("orders.txt");
			HeaderColumnNameMappingStrategy<Order> strategy = new HeaderColumnNameMappingStrategy<Order>();
			strategy.setType(Order.class);
			CsvToBean<Order> csv = new CsvToBean<Order>();
			List<Order> orders = csv.parse(strategy, reader);
			for (Order order : orders) {
				if (order.getId() == id) {
					return order;
				}
			}
			return null;
		} finally {
			if (reader!=null) {
				reader.close();
			}
		}
	}

	public void saveOrder(Order order) throws Exception {
		List<Order> orders = new ArrayList<Order>();
		CSVReader reader = null;
		try {
			reader = createCSVReader("orders.txt");
			HeaderColumnNameMappingStrategy<Order> strategy = new HeaderColumnNameMappingStrategy<Order>();
			strategy.setType(Order.class);
			CsvToBean<Order> csv = new CsvToBean<Order>();
			orders = csv.parse(strategy, reader);
		} finally {
			if (reader!=null) {
				reader.close();
			}
		}

		Order orderSaved = null;
		for (Order or : orders) {
			if (or.getId() == order.getId())
				orderSaved = order;
		}
		if (orderSaved != null)
			throw new Exception(
					"Primary Constraint Exception: another object already exists with same Id");
		orders.add(order);

		CSVWriter writer = null;
		try {
			writer = createCSVWriter("orders.txt");
			writer.writeNext(new String[] { "Id", "ItemTotal", "Total",
					"Coupon" });
			for (Order or : orders) {
				writer.writeNext(new String[] { String.valueOf(or.getId()),
						String.valueOf(or.getItemTotal()),
						String.valueOf(or.getTotal()), or.getCoupon() });
			}
			writer.flush();
		} finally {
			if (writer!=null) {
				writer.close();
			}
		}
	}

	private CSVWriter createCSVWriter(String fileName) throws Exception {
		try {
			File file = new File(getDataDirectory() + "/" + fileName);
			OutputStream output = new FileOutputStream(file);
			return new CSVWriter(new OutputStreamWriter(output), ',', '\0');
		} catch (FileNotFoundException e) {
			throw new Exception(
					"'Data' directory not found, verify the path in 'src/test/resources/configuration.properties'");
		}
	}

	private CSVReader createCSVReader(String fileName) throws Exception {
		try {
			File file = new File(getDataDirectory() + "/" + fileName);
			InputStream input = new FileInputStream(file);
			return new CSVReader(new InputStreamReader(input), ',', '\0');
		} catch (FileNotFoundException e) {
			throw new Exception(
					"'Data' directory not found, verify the path in 'src/test/resources/configuration.properties'");
		}
	}

	private String getDataDirectory() throws Exception {
		Properties properties = new Properties();
		InputStream stream = getClass().getResourceAsStream(
				"/configuration.properties");
		properties.load(stream);
		return properties.getProperty("data-directory");
	}
}
