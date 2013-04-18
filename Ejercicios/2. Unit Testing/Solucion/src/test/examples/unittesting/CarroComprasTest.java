package examples.unittesting;

import static org.junit.Assert.*;

import org.junit.Test;

public class CarroComprasTest {

	@Test
	public void AgregaUnaNuevaLineaCuandoElProductoNoExiste() {
		CarroCompras carroCompras = new CarroCompras();

		carroCompras.agregarLinea(new Producto(1));

		assertEquals(1, carroCompras.cantidadProductos());
	}

	@Test
	public void IncrementaLaCantidadAlAgregarUnaLineaCuandoElProductoExiste() {
		CarroCompras carroCompras = new CarroCompras();
		carroCompras.agregarLinea(new Producto(1));

		carroCompras.agregarLinea(new Producto(1));

		assertEquals(2, carroCompras.cantidadProductos());
	}

	@Test
	public void ActualizaLaCantidadCuandoElProductoExiste() {
		CarroCompras carroCompras = new CarroCompras();
		carroCompras.agregarLinea(new Producto(1));

		carroCompras.actualizarLinea(1, 3);

		assertEquals(3, carroCompras.cantidadProductos());
	}

	@Test
	public void RemueveLaLineaAlActualizarCuandoLaCantidadEsCero() {
		CarroCompras carroCompras = new CarroCompras();
		carroCompras.agregarLinea(new Producto(1));

		carroCompras.actualizarLinea(1, 0);

		assertEquals(0, carroCompras.cantidadProductos());
	}

	@Test(expected = IllegalStateException.class)
	public void SeProduceUnErrorAlActualizarCuandoElProductoNoExiste() {
		CarroCompras carroCompras = new CarroCompras();

		carroCompras.actualizarLinea(1, 1);
	}
}
