package testautomation.testdoubles;

import static org.mockito.Mockito.*;

import org.junit.Test;

public class AlmacenServiceTest {

	@Test
	public void ReservaElInventarioCuandoLaCantidadEsSuficiente() throws Exception {
		AlmacenDAO almacenDAO = mock(AlmacenDAO.class);
		when(almacenDAO.cantidadInventario(1)).thenReturn(20);
		AlmacenService almacenService=new AlmacenService(almacenDAO);
		
		almacenService.ReservarInventario(1, 10);
		
		verify(almacenDAO).disminuirInventario(1, 10);
	}
	
	@Test(expected=InventarioInsuficienteException.class)
	public void LanzaExcepcionAlReservanInventarioCuandoLaCantidadEsInsuficiente() throws Exception{
		AlmacenDAO almacenDAO = mock(AlmacenDAO.class);
		when(almacenDAO.cantidadInventario(1)).thenReturn(9);
		AlmacenService almacenService=new AlmacenService(almacenDAO);
		
		almacenService.ReservarInventario(1, 10);
	}
}
