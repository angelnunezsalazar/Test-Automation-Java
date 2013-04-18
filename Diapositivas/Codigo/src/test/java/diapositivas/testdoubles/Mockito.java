package diapositivas.testdoubles;

import static org.mockito.Mockito.*;

import org.junit.Test;

public class Mockito {
	@Test
	public void mockitoAPI() throws Exception {
		
		Foo foo = mock(Foo.class)	; 
		
		when(foo.getSomething("param")).thenReturn(true)	;
		
		when(foo.getSomething(anyString())).thenReturn(true)	;
		
		doThrow(new Exception()).when(foo).doSomething()	;
		
		verify(foo).execute()				;
		verify(foo, times(2)).execute()		;
	}
	
	interface Foo {

		boolean getSomething(String string);

		void doSomething();

		void execute();

	}
}
