package examples.unittesting;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DataSourceCarroCompras {

	private int costoEnvio;
	private int precioProducto;
	private int totalEsperado;

	@Parameters
	public static Collection data() {
		return Arrays.asList(new Object[][] 
				{ { 2, 3, 5 }, 
				  { 1, 2, 3} });
	}

	public DataSourceCarroCompras(int costoEnvio, int precioProducto, int totalEsperado) {
		this.costoEnvio = costoEnvio;
		this.precioProducto = precioProducto;
		this.totalEsperado = totalEsperado;
	}

	@Test
	public void calculaElTotalDeLaOrden() {
		CarroCompras carroCompras=new CarroCompras();
		carroCompras.setCostoEnvio(new BigDecimal(costoEnvio));
		Producto producto = new Producto(1);
		producto.setPrecio(new BigDecimal(precioProducto));
		carroCompras.agregarLinea(producto);
		
		BigDecimal total = carroCompras.total();
		
		assertEquals(new BigDecimal(totalEsperado),total);
	}
}