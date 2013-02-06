package eu.vytenis.skaitvardziai.log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JdkLog implements Log {

	private final Logger logger;
	
	public JdkLog(Class<?> type) {
		 logger = Logger.getLogger(type.getName());
	}
	
	public boolean isWarnEnabled() {
		return logger.isLoggable(Level.WARNING);
	}
	
	public void warn(String text) {
		logger.warning(text);
	}

}
