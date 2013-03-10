package eu.vytenis.skaitvardziai.app.echo;

import org.junit.Test;

import eu.vytenis.skaitvardziai.app.AppTest;
import eu.vytenis.skaitvardziai.app.io.ExpectedOut;

public class EchoProcessorTest extends AppTest {
	
	@Test
	public void testEchoParameters() {
		assertOut(new ExpectedOut("vienas\n"), 1);
		assertOut(new ExpectedOut("šimtas dešimt\n"), 110);
		
		assertOut(new ExpectedOut("vienas"), "-n", 1);
		assertOut(new ExpectedOut("šimtas dešimt"), "--no-newline", 110);
		
		assertOut(new ExpectedOut("vieno"), "-f", "K", "-n", 1);		
	}
	
	@Test
	public void testEchoSystemIn() {
		assertOutByIn(new ExpectedOut("vienas\n"), "1");
		assertOutByIn(new ExpectedOut("šimtas dešimt\n"), "110");
		
		assertOutByIn(new ExpectedOut("pirmam\n"), "1", "-f", "N,Kl");
		
		assertOutByIn(new ExpectedOut("dviejų\ntrijų\n"), "2\n3", "-f", "K");
	}

}
