package eu.vytenis.skaitvardziai.app.main;

import org.junit.Test;



public class MainTest extends AppTest {

	@Test	
	public void testHelp() {
		ExpectedOut p = new ExpectedOut("(?ms)^usage:.*Prints text that represents given number[\n\r]+$");
		
		assertOutMatches(p, "-h");
		assertOutMatches(p, "--help");
		assertOutMatches(p, "--help", "-h");
	}
	
	@Test
	public void testUsage() {
		ExpectedOut p = new ExpectedOut("(?ms)^usage:.*\n$");
		assertOutMatches(p, "-X", 1);
	}

	@Test
	public void testOneArg() {
		assertOut(new ExpectedOut("vienas\n"), 1);
		assertOut(new ExpectedOut("šimtas dešimt\n"), 110);
		
		assertOut(new ExpectedOut("vienas"), "-n", 1);
		assertOut(new ExpectedOut("šimtas dešimt"), "--no-newline", 110);
		
		assertOut(new ExpectedOut("vieno"), "-f", "K", "-n", 1);		
	}

}
