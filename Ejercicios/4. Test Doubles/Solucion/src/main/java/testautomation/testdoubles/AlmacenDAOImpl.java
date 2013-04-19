package testautomation.testdoubles;

public class AlmacenDAOImpl implements AlmacenDAO {

	public int cantidadInventario(int productoId) {
		// ... Database Access Logic .....
		throw new UnsupportedOperationException("No debes consultar a la BD");
	}

	public void disminuirInventario(int productoId, int cantidad) {
		// .... Database Access Logic .....
		throw new UnsupportedOperationException("No debes consultar a la BD");
	}

}
