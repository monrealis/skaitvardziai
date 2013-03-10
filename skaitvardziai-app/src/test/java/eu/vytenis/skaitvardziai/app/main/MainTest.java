package eu.vytenis.skaitvardziai.app.main;

import org.junit.Test;

import eu.vytenis.skaitvardziai.app.AppTest;
import eu.vytenis.skaitvardziai.app.io.ExpectedOut;



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



}
