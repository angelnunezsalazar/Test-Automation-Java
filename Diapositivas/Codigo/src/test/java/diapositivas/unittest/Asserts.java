package diapositivas.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

public class Asserts {

	@Test
	public void testName() {
		
		assertEquals(2, 1+1);
		assertNull(null);
		assertNotNull("string");
		assertTrue(true);
		assertFalse(false);
		
	}
}
