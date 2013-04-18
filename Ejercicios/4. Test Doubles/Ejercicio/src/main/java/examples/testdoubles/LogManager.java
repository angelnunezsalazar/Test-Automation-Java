package examples.testdoubles;

import java.util.Arrays;
import java.util.List;

public class LogManager {
	
	private List<Level> orderedLevels = Arrays.asList(Level.ERROR, Level.INFO,Level.DEBUG);
	
	public void write(String message, Level level) throws Exception {
		if (level == Level.ERROR) {
			EmailSender emailsender = new EmailSender();
			emailsender.sendToAdmin(message);
		}
		if (isEnabled(level)) {
			FileAppender appender = new FileAppender();
			appender.write(message);
		}
	}

	public boolean isEnabled(Level messageLevel) throws Exception {
		Level loggerLevel = levelFromConfiguration();
		return messageLevelIsBeforeOrEqualThanLoggerLevel(messageLevel,loggerLevel);
	}

	private Level levelFromConfiguration() throws Exception {
		Configuration configuration = new Configuration();
		Level loggerLevel = configuration.loggerLevel();
		return loggerLevel;
	}
	
	private boolean messageLevelIsBeforeOrEqualThanLoggerLevel(Level messageLevel, Level loggerLevel) {
		return orderedLevels.indexOf(messageLevel) <= orderedLevels.indexOf(loggerLevel);
	}
}
