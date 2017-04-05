package eu.vytenis.skaitvardziai.slf4j;

import org.junit.Test;

public class Slf4jLogTest {
	@Test
	public void testCreateSucceeds() {
		new Slf4jLog(Slf4jLogTest.class);
	}
}
