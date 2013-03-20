package eu.vytenis.skaitvardziai.log;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;


// Žr. http://grepcode.com/file/repo1.maven.org/maven2/org.apache.ibatis/ibatis-sqlmap/3.0-beta-6/org/apache/ibatis/logging/LogFactory.java
public class LogFactory {

	public enum LogType {
		Jdk, Slf4j;		
		public static LogType[] TYPES_BY_PRIORITY_DESC = {Slf4j, Jdk};
	}
	
	private static LogType type;
	private static final Map<LogType, String> loggers = new HashMap<LogType, String>();
	
	static {
		loggers.put(LogType.Jdk, JdkLog.class.getName());
		loggers.put(LogType.Slf4j, "eu.vytenis.skaitvardziai.slf4j.Slf4jLog");
	}
	
	public static void setType(LogType type) {
		if (LogFactory.type != null) {
			throw new LoggerTypeAlreadySetException();
		}
		LogFactory.type = type;		
	}
	
	private static void determineLogType(Class<?> loggerName) {
		if (type != null) {
			return;
		}
		synchronized (LogFactory.class) {
			for (LogType t : LogType.TYPES_BY_PRIORITY_DESC) {
				if (buildLogger(t, loggerName) != null) {
					type = t;
					return;
				}				
			}
		}
	}
	
	private static Log buildLogger(LogType type, Class<?> loggerName) {
		try {
			return tryBuildLogger(type, loggerName);			
		} catch (Throwable t) {
			return null;			
		}
	}

	private static Log tryBuildLogger(LogType type, Class<?> loggerName) throws Exception {
		String className = loggers.get(type);
		Class<?> c = Class.forName(className);
		Constructor<?> ctr = c.getConstructor(Class.class);
		return (Log) ctr.newInstance(loggerName);
	}
	
	public static Log getLog(Class<?> loggerName) {
		determineLogType(loggerName);
		Log log = buildLogger(LogFactory.type, loggerName);
		if (log == null) {
			log = new JdkLog(loggerName);
		}
		return log;
	}
	
	public static class LoggerTypeAlreadySetException extends SkaitvardziaiRuntimeException {

		private static final long serialVersionUID = -1363444374238478430L;
		
		public LoggerTypeAlreadySetException() {
			super("Log type already set to " + type);			
		}
		
	}

}
