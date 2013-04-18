package diapositivas.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

public class PassFail {

	@Test
	public void pass() {
		Integer.parseInt("12345");
	}
	
	@Test
	public void fail() {
		Integer.parseInt("not a number");
	}
}
