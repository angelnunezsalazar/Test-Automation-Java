package examples.testdoubles;

import java.util.Arrays;
import java.util.List;

public class LogManager {
	
	private List<Level> orderedLevels = Arrays.asList(Level.ERROR, Level.INFO,Level.DEBUG);
	private Configuration configuration;
	private EmailSender emailsender;
	private Appender appender;
	
	public LogManager(Configuration configuration, EmailSender emailSender, Appender appender) {
		this.configuration = configuration;
		this.emailsender = emailSender;
		this.appender = appender;
	}

	public void write(String message, Level level) throws Exception {
		if (level == Level.ERROR) {
			emailsender.sendToAdmin(message);
		}
		if (isEnabled(level)) {
			appender.write(message);
		}
	}

	public boolean isEnabled(Level messageLevel) throws Exception {
		Level loggerLevel = levelFromConfiguration();
		return messageLevelIsBeforeOrEqualThanLoggerLevel(messageLevel,loggerLevel);
	}

	private Level levelFromConfiguration() throws Exception {
		Level loggerLevel = configuration.loggerLevel();
		return loggerLevel;
	}
	
	private boolean messageLevelIsBeforeOrEqualThanLoggerLevel(Level messageLevel, Level loggerLevel) {
		return orderedLevels.indexOf(messageLevel) <= orderedLevels.indexOf(loggerLevel);
	}
}
