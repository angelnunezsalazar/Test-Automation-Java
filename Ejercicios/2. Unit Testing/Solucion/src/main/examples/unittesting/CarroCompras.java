package examples.unittesting;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CarroCompras {

	private BigDecimal costoEnvio;

	private Set<LineaCarroCompras> detalle = new HashSet<LineaCarroCompras>();

	public void agregarLinea(Producto producto) {
		LineaCarroCompras linea = this.buscarLinea(producto.getId());

		if (linea == null) {
			this.detalle.add(new LineaCarroCompras(producto, 1));
		} else {
			linea.setCantidad(linea.getCantidad() + 1);
		}
	}

	public void actualizarLinea(int productoId, int cantidad) {
		LineaCarroCompras linea = buscarLinea(productoId);
		if (linea == null)
			throw new IllegalStateException("No existe el producto");

		if (cantidad == 0)
			this.removerLinea(productoId);

		linea.setCantidad(cantidad);
	}

	public void removerLinea(int productoId) {
		LineaCarroCompras linea = buscarLinea(productoId);
		if (linea == null)
			throw new IllegalStateException("No existe el producto");

		this.detalle.remove(linea);
	}

	public int cantidadProductos() {
		int sum = 0;
		for (LineaCarroCompras linea : detalle) {
			sum += linea.getCantidad();
		}
		return sum;
	}

	public BigDecimal total() {
		BigDecimal lineaTotal = new BigDecimal(0);
		for (LineaCarroCompras linea : detalle) {
			lineaTotal=lineaTotal.add(linea.total());
		}
		return lineaTotal.add(costoEnvio);
	}

	private LineaCarroCompras buscarLinea(int productoId) {
		for (LineaCarroCompras linea : detalle) {
			if (linea.getProducto().getId() == productoId) {
				return linea;
			}
		}
		return null;
	}

	public BigDecimal getCostoEnvio() {
		return costoEnvio;
	}

	public void setCostoEnvio(BigDecimal costoEnvio) {
		this.costoEnvio = costoEnvio;
	}

	public Set<LineaCarroCompras> Detalle() {
		return Collections.unmodifiableSet(this.detalle);
	}
}
