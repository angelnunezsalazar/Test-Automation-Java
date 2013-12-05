package webtestingframework.infraestructure;

import java.util.ArrayList;
import java.util.List;

import webtestingframework.data.DataAccess;
import webtestingframework.model.Product;

public class DataLoader {
	private List<Product> savedObjects = new ArrayList<Product>();

	public Product loadData(Product obj) throws Exception {
		DataAccess dataAccess = new DataAccess();
		dataAccess.saveProduct(obj);
		savedObjects.add(obj);
		return obj;
	}

	public void clean() throws Exception {
		for (Product product : savedObjects) {
			DataAccess dataAccess = new DataAccess();
			dataAccess.deleteProduct(product);
		}
	}
}
