package diapositivas.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

public class XQNoPruebasGrandes {

	@Test
	public void test() {
		Stack stack =new Stack();
		assertTrue(stack.isEmpty());//Test comportamiento N°1
		
		stack.push(1);
		assertFalse(stack.isEmpty());//Test comportamiento N°2
		
		assertEquals(1,stack.pop());//Test comportamiento N°3
	}
}
