package examples.unittesting;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

public class StackTest {

	private Stack stack;

	@Before
	public void setup() {
		stack = new Stack();
	}

	@Test
	//Está vacio si no tiene elementos
	public void isEmptyWhenNew() {
		boolean isEmpty = stack.isEmpty();

		assertTrue(isEmpty);
	}

	@Test
	//No está vacio si colocamos elemento
	public void notIsEmptyWhenPushingAnItem() {
		stack.push(1);

		boolean isEmpty = stack.isEmpty();

		assertFalse(isEmpty);
	}

	@Test
	//Elimina un elemento de la lista al obtenerlo
	public void removesTheItemWhenPopping() {
		stack.push(1);
		stack.pop();

		boolean isEmpty = stack.isEmpty();

		assertTrue(isEmpty);
	}

	@Test
	//Retorna el mismo elemento que se ha ingresado
	public void popsTheSameItemThatWasPushed() {
		stack.push(1);

		int element = stack.pop();

		assertEquals(1, element);
	}

	@Test
	//El primer elemento obtenido es último elemento que ha sido ingresado
	public void theFirstItemPoppedIsTheLastItemPushed() {
		stack.push(1);
		stack.push(2);

		stack.pop();
		int element = stack.pop();

		assertEquals(1, element);
	}

	@Test
	//Lanza una excepción al obtener un elemento que no ha sido ingresado
    public void throwsExceptionWhenPoppingAnItemItDoesntHold()
    {
        try
        {
            stack.pop();
            fail();
        }
        catch (EmptyStackException e) { }
    }
	
	@Test(expected=EmptyStackException.class)
	//Lanza una excepción al obtener un elemento que no ha sido ingresado
    public void throwsExceptionWhenPoppingAnItemItDoesntHold2()
    {
            stack.pop();
    }
}
