package eu.vytenis.skaitvardziai.app.main;

import java.util.regex.Pattern;

import org.junit.Test;



// TODO parašyti testų
public class MainTest extends AppTest {

	@Test	
	public void testHelp() {
		Pattern p = Pattern.compile("(?ms)^usage:.*Prints text that represents given number[\n\r]+$");
		
		assertOutMatches(p, "-h");
		assertOutMatches(p, "--help");
		assertOutMatches(p, "--help", "-h");
	}
	
	@Test
	public void testUsage() {
		Pattern p = Pattern.compile("(?ms)^usage:.*\n$");
		assertOutMatches(p, "-X", 1);
	}

	@Test
	public void testOneArg() {
		assertOut("vienas\n", 1);
		assertOut("šimtas dešimt\n", 110);
		
		assertOut("vienas", "-n", 1);
		assertOut("šimtas dešimt", "--no-newline", 110);
		
		assertOut("vieno", "-f", "K", "-n", 1);		
	}

}
