package testautomation.testdoubles;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class CostoEnvioServiceTest {
	private static final double DELTA = 1e-15; //Delta muy pequeño o 0

	@Test
	public void costoDeEnvioGratuitoParaUSA() {
		CostoEnvioService costoEnvioService = new CostoEnvioService(null);

		double costo = costoEnvioService.calcular("USA");

		// Los tipos doubles tienen problemas de precisión por lo tanto se recomienda
		// usar las siguientes alternativas
		assertEquals(0, costo, DELTA); 		// Se produce error si la diferencia entre esperado y actual es mayor al delta
		assertEquals(Double.doubleToLongBits(0), Double.doubleToLongBits(costo));
		assertEquals(0, Double.compare(0, costo));
	}

	@Test
	public void calculaElCostoDeEnvioParaPaisesDiferentesUSA() {
		CostoEnvioDAO costoEnvioDAO = mock(CostoEnvioDAO.class);
		when(costoEnvioDAO.obtener("PERU")).thenReturn(10.5);
		CostoEnvioService costoEnvioService = new CostoEnvioService(costoEnvioDAO);

		double costo = costoEnvioService.calcular("PERU");

		assertEquals(10.5, costo, DELTA);
	}

	@Test
	public void actualizaElCostoDeEnvioPorPais() {
		CostoEnvioDAO costoEnvioDAO = mock(CostoEnvioDAO.class);
		CostoEnvioService costoEnvioService = new CostoEnvioService(costoEnvioDAO);
		
		costoEnvioService.actualizarCosto("PERU", 10);
		
		verify(costoEnvioDAO).actualizar("PERU", 10);
	}
	
}
