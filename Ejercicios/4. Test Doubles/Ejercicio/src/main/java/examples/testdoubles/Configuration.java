package examples.testdoubles;

import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	
	public Level loggerLevel() throws Exception {
		Properties properties = new Properties();
		InputStream stream = getClass().getResourceAsStream("/configuration.properties");
		properties.load(stream);
		Level level = Level.valueOf(properties.getProperty("loggerLevel"));
		return level;
	}
}
