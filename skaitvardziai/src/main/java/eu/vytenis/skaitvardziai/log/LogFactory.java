package eu.vytenis.skaitvardziai.log;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;


// Panaudoti analogiškai http://grepcode.com/file/repo1.maven.org/maven2/org.apache.ibatis/ibatis-sqlmap/3.0-beta-6/org/apache/ibatis/logging/LogFactory.java
// TODO realizuoti
public class LogFactory {

	
	private static LogType type;
	private static final Map<LogType, String> loggers = new HashMap<LogType, String>();
	static {
		loggers.put(LogType.Jdk, JdkLog.class.getName());
		loggers.put(LogType.Slf4j, "eu.vytenis.skaitvardziai.slf4j.Slf4jLog");
	}
	
	public static void setType(LogType type) {
		if (type != null) {
			throw new IllegalStateException("Log type already set to " + type);
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
			String className = loggers.get(type);
			Class<?> c = Class.forName(className);
			Constructor<?> ctr = c.getConstructor(Class.class);
			Log log = (Log) ctr.newInstance(loggerName);
			return log;			
		} catch (Throwable t) {
			//t.printStackTrace();
			return null;			
		}
	}
	
	public static Log getLog(Class<?> loggerName) {
		determineLogType(loggerName);
		Log log = buildLogger(LogFactory.type, loggerName);
		if (log == null) {
			log = new JdkLog(loggerName);
		}
		return log;
	}
	
	public enum LogType {
		Jdk, Slf4j;		
		public static LogType[] TYPES_BY_PRIORITY_DESC = {Slf4j, Jdk};
	}

}
