package examples.testdoubles;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class LogManagerTest {

	private Configuration configuration;
	private LogManager logManager;
	private EmailSender emailSender;
	private Appender appender;

	@Before
	public void setup(){
		configuration = mock(Configuration.class);
		emailSender = mock(EmailSender.class);
		appender = mock(Appender.class);
		logManager = new LogManager(configuration, emailSender, appender);
	}
	
	@Test
	public void isEnabled_messageLevelBeforeLoggerLevel_returnTrue() throws Exception {
		when(configuration.loggerLevel()).thenReturn(Level.INFO);
		
		boolean isEnabled = logManager.isEnabled(Level.ERROR);
		
		assertTrue(isEnabled);
	}
	
	@Test
	public void isEnabled_messageLevelAfterLoggerLevel_returnFalse() throws Exception {
		when(configuration.loggerLevel()).thenReturn(Level.INFO);
		
		boolean isEnabled = logManager.isEnabled(Level.DEBUG);
		
		assertFalse(isEnabled);
	}
	
	@Test
	public void write_levelError_sendMailtoAdmin() throws Exception {
		logManager.write("message",Level.ERROR);
		
		verify(emailSender).sendToAdmin("message");
	}
	
	@Test
	public void write_isEnabled_writeToAppender() throws Exception {
		when(configuration.loggerLevel()).thenReturn(Level.INFO);
		
		logManager.write("message", Level.INFO);
		
		verify(appender).write("message");
	}
}
