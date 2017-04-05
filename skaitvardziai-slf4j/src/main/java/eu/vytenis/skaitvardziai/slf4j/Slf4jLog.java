package eu.vytenis.skaitvardziai.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.vytenis.skaitvardziai.log.Log;

public class Slf4jLog implements Log {
	private final Logger logger;

	public Slf4jLog(Class<?> type) {
		logger = LoggerFactory.getLogger(type);
	}

	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	public void warn(String text) {
		logger.warn(text);
	}
}
