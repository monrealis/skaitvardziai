package eu.vytenis.skaitvardziai.log;


// Panaudoti analogiškai http://grepcode.com/file/repo1.maven.org/maven2/org.apache.ibatis/ibatis-sqlmap/3.0-beta-6/org/apache/ibatis/logging/LogFactory.java
// TODO realizuoti
public class LogFactory {

	public static Log getLog(Class<?> type) {
		return new JdkLog(type);		
	}

}
