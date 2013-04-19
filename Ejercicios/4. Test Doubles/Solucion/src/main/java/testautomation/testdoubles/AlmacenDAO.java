package testautomation.testdoubles;

public interface AlmacenDAO {

	public int cantidadInventario(int productoId);

	public void disminuirInventario(int productoId, int cantidad);

}