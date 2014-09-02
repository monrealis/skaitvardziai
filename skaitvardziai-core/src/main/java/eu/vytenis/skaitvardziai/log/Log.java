package eu.vytenis.skaitvardziai.log;

public interface Log {

	boolean isWarnEnabled();

	void warn(String text);

}
