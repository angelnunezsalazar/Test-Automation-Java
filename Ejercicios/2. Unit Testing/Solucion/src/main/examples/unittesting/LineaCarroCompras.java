package examples.unittesting;

import java.math.BigDecimal;

public class LineaCarroCompras {
	private Producto producto;
	private int cantidad;

	public LineaCarroCompras(Producto producto, int cantidad) {
		this.producto = producto;
		this.setCantidad(cantidad);
	}

	public BigDecimal total() {
		BigDecimal cantidad = new BigDecimal(this.getCantidad());
		BigDecimal precio = this.producto.getPrecio();
		return precio.multiply(cantidad);
	}

	public Producto getProducto() {
		return producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
