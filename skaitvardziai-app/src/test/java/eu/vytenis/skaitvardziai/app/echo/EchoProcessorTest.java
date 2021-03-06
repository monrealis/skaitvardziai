package eu.vytenis.skaitvardziai.app.echo;

import org.junit.Test;

import eu.vytenis.skaitvardziai.app.AppTest;
import eu.vytenis.skaitvardziai.app.io.ExpectedOut;

public class EchoProcessorTest extends AppTest {

	@Test
	public void testEchoParameters() {
		assertOut(new ExpectedOut("vienas\n"), 1);
		assertOut(new ExpectedOut("minus vienas\n"), " -1");
		assertOut(new ExpectedOut("viena trečioji\n"), "1 / 3");
		assertOut(new ExpectedOut("minus viena minus trečioji\n"), " -1 / - 3");
		assertOut(new ExpectedOut("šimtas dešimt\n"), 110);
		assertOut(new ExpectedOut("vienas"), "-n", 1);
		assertOut(new ExpectedOut("šimtas dešimt"), "--no-newline", 110);
		assertOut(new ExpectedOut("vieno"), "-f", "K", "-n", 1);
	}

	@Test
	public void testEchoParametersWithCharset() {
		assertOut(new ExpectedOut("dvidešimt", WIN1257), "-n", 20, "-oe", WIN1257);
		assertOut(new ExpectedOut("dvidešimt", UTF8), "-n", 20, "-oe", UTF8);
	}

	@Test
	public void testEchoSystemIn() {
		assertOutByIn(new ExpectedOut("vienas\n"), "1");
		assertOutByIn(new ExpectedOut("šimtas dešimt\n"), "110");
		assertOutByIn(new ExpectedOut("pirmam\n"), "1", "-f", "N,Kl");
		assertOutByIn(new ExpectedOut("dviejų\ntrijų\n"), "2\n3", "-f", "K");
	}

	@Test
	public void testEchoSystemInCharset() {
		assertOutByIn(new ExpectedOut("dešimt\n", WIN1257), "10", "-ie", WIN1257, "-oe", WIN1257);
		assertOutByIn(new ExpectedOut("dešimt\n", UTF8), "10", "-ie", UTF8, "-oe", UTF8);
	}

}
