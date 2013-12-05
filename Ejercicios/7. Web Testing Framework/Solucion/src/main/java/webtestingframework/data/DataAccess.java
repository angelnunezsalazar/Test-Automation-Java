package webtestingframework.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import webtestingframework.model.Entity;
import webtestingframework.model.Order;
import webtestingframework.model.Product;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameMappingStrategy;

public class DataAccess {

	public <T extends Entity> List<T> listAll(Class<T> clazz) throws Exception {
		CSVReader reader = null;
		try {
			reader = createCSVReader(clazz.getSimpleName() + ".txt");
			HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<T>();
			strategy.setType(clazz);
			CsvToBean<T> csv = new CsvToBean<T>();
			return csv.parse(strategy, reader);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	public Product getProduct(String id) throws Exception {
		List<Product> products = listAll(Product.class);
		for (Product product : products) {
			if (product.getId().equals(id)) {
				return product;
			}
		}
		return null;
	}

	public void saveOrder(Order order) throws Exception {
		List<Order> orders = (List<Order>) listAll(Order.class);
		order.setId(UUID.randomUUID().toString());
		orders.add(order);
		CSVWriter writer = null;
		try {
			writer = createCSVWriter("order.txt");
			writer.writeNext(new String[] { "Id", "Email", "Address",
					"Quantity", "ProductId" });
			for (Order or : orders) {
				writer.writeNext(new String[] { String.valueOf(or.getId()),
						or.getEmail(), or.getAddress(),
						String.valueOf(or.getQuantity()),
						String.valueOf(or.getProductId()) });
			}
			writer.flush();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public void saveProduct(Product product) throws Exception {
		List<Product> products = listAll(Product.class);
		product.setId(UUID.randomUUID().toString());
		products.add(product);
		CSVWriter writer = null;
		try {
			writer = createCSVWriter("product.txt");
			writer.writeNext(new String[] { "Id", "Name", "Description","Price", "ImageName" });
			for (Product or : products) {
				writer.writeNext(new String[] { String.valueOf(or.getId()),
						or.getName(), or.getDescription(),
						String.valueOf(or.getPrice()),
						String.valueOf(or.getImageName()) });
			}
			writer.flush();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
	public void deleteProduct(Product product) throws Exception{
		List<Product> products = listAll(Product.class);
		Product productFound=null;
		for (Product prod : products) {
			if (prod.getId().equals(product.getId())) {
				productFound=prod;
			}
		}
		if (productFound!=null) {
			products.remove(productFound);
		}
		
		CSVWriter writer = null;
		try {
			writer = createCSVWriter("product.txt");
			writer.writeNext(new String[] { "Id", "Name", "Description","Price", "ImageName" });
			for (Product or : products) {
				writer.writeNext(new String[] { String.valueOf(or.getId()),
						or.getName(), or.getDescription(),
						String.valueOf(or.getPrice()),
						String.valueOf(or.getImageName()) });
			}
			writer.flush();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
	private CSVReader createCSVReader(String fileName) throws Exception {
		File file = new File(getDataDirectory() + "/" + fileName);
		InputStream input = new FileInputStream(file);
		return new CSVReader(new InputStreamReader(input), ',', '\0');
	}

	private CSVWriter createCSVWriter(String fileName) throws Exception {
		File file = new File(getDataDirectory() + "/" + fileName);
		OutputStream output = new FileOutputStream(file);
		return new CSVWriter(new OutputStreamWriter(output), ',', '\0');
	}

	private String getDataDirectory() throws Exception {
		Properties properties = new Properties();
		InputStream stream = getClass().getResourceAsStream("configuration.properties");
		properties.load(stream);
		return properties.getProperty("data-directory");
	}
}
