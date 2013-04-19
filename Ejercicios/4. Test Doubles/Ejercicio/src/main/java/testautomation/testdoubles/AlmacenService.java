package testautomation.testdoubles;

public class AlmacenService {

	private AlmacenDAO almacenDAO;

	public AlmacenService() {
		almacenDAO = new AlmacenDAO();
	}

	public void ReservarInventario(int productoId, int cantidad)
			throws InventarioInsuficienteException {
		int inventario = almacenDAO.cantidadInventario(productoId);
		if (inventario < cantidad)
			throw new InventarioInsuficienteException();

		almacenDAO.disminuirInventario(productoId, cantidad);
	}
}
