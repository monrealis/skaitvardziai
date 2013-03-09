package eu.vytenis.skaitvardziai.app.main;

import org.junit.Test;



public class MainTest extends AppTest {

	@Test	
	public void testHelp() {
		Out p = new Out("(?ms)^usage:.*Prints text that represents given number[\n\r]+$");
		
		assertOutMatches(p, "-h");
		assertOutMatches(p, "--help");
		assertOutMatches(p, "--help", "-h");
	}
	
	@Test
	public void testUsage() {
		Out p = new Out("(?ms)^usage:.*\n$");
		assertOutMatches(p, "-X", 1);
	}

	@Test
	public void testOneArg() {
		assertOut(new Out("vienas\n"), 1);
		assertOut(new Out("šimtas dešimt\n"), 110);
		
		assertOut(new Out("vienas"), "-n", 1);
		assertOut(new Out("šimtas dešimt"), "--no-newline", 110);
		
		assertOut(new Out("vieno"), "-f", "K", "-n", 1);		
	}

}
