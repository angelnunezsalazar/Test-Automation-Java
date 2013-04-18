package diapositivas.unittest;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import diapositivas.unittest.InvalidLoginException;

public class Exceptions {

	@Test
	public void exceptionTest1() {
		
		try {
			login("usuario", "password");
			fail();
		} catch (InvalidLoginException e) {
			// Si llego acá significa que se produjo la excepción adecuada
			
			// (Opcionalmene) Podemos realizar asertos sobre la excepción
			assertEquals("Usuario o password incorrecto", e.getMessage());
		}
	}

	@Test(expected = InvalidLoginException.class)
	public void exceptionTest2() throws InvalidLoginException {
		login("usuario", "password");
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	@Test
	public void exceptionTest() throws InvalidLoginException {
		thrown.expect(InvalidLoginException.class);
		thrown.expectMessage("Usuario o password incorrecto");
		login("usuario", "password");
	}

	private void login(String string, String string2)
			throws InvalidLoginException {
		// TODO Auto-generated method stub

	}
}
