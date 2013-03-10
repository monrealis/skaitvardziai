package eu.vytenis.skaitvardziai.app.template;

import org.junit.Test;

import eu.vytenis.skaitvardziai.app.AppTest;
import eu.vytenis.skaitvardziai.app.io.ExpectedOut;
import eu.vytenis.skaitvardziai.app.template.TemplateProcessor.TemplateParseException;
import eu.vytenis.skaitvardziai.parser.methods.Invoker.MethodInvocationException;
import eu.vytenis.skaitvardziai.parser.methods.Methods.SkaitvardziaiParseException;

public class TemplateTest extends AppTest {

	@Test
	public void testStatic() {
		assertOutByIn(new ExpectedOut("test\na"), "test\na", "-t");
	}
	
	@Test
	public void testSveikieji() {
		assertOutByIn(new ExpectedOut("Skaičius: vienas"), "Skaičius: ${sveikasis(1)}", "-t");
		assertOutByIn(new ExpectedOut("Skaičius: trys šimtai tūkstančių keturi šimtai penkiasdešimt"), "Skaičius: ${sveikasis(300450)}", "-t");		
		assertOutByIn(new ExpectedOut("Skaičius: penki šimtai dvyliktąsias"), "Skaičius: ${sveikasis(512, 'Iv,Kl,SD,G,MG')}", "-t");		
		assertOutByIn(new ExpectedOut("Skaičius: minus vieno"), "Skaičius: ${sveikasis(-1, \"K\")}", "-t");
	}
	
	@Test
	public void testTrupmenos() {
		assertOutByIn(new ExpectedOut("viena antroji yra mažiau nei dvi trečiosios"), "${trupmena(1/2)} yra mažiau nei ${trupmena(2/3)}", "-t");
		assertOutByIn(new ExpectedOut("minus keturias šeštąsias"), "${trupmena(-4/6, 'G')}", "-t");
	}
	
	@Test
	public void testKiti() {
		assertOutByIn(new ExpectedOut("10 žmonių"), "10 ${kiti(10, 'žmogus', 'žmonės', 'žmonių')}", "-t");
		assertOutByIn(new ExpectedOut("1 žmogus"), "1 ${kiti(1, 'žmogus', 'žmonės', 'žmonių')}", "-t");
		assertOutByIn(new ExpectedOut("22 žmonės"), "22 ${kiti(22, 'žmogus', 'žmonės', 'žmonių')}", "-t");
		
		assertOutByIn(new ExpectedOut("10 žmonių"), "10 ${kiti(10, 'N', 'žmogui', 'žmonėms', 'žmonių')}", "-t");
		assertOutByIn(new ExpectedOut("1 žmogui"), "1 ${kiti(1, 'N', 'žmogui', 'žmonėms', 'žmonių')}", "-t");
		assertOutByIn(new ExpectedOut("22 žmonėms"), "22 ${kiti(22, 'N', 'žmogui', 'žmonėms', 'žmonių')}", "-t");
	}
	
	@Test(expected = SkaitvardziaiParseException.class)
	public void failIfManyStatements() {
		assertOutByIn(new ExpectedOut("Skaičius: penki šimtai dvylika"), "Skaičius: ${sveikasis(512), sveikasis(513)}", "-t");
	}

	@Test(expected = TemplateParseException.class)
	public void failIfTagNotClosed() {
		assertOutByIn(new ExpectedOut("Skaičius: ${"), "Skaičius: ${", "-t");
	}
	
	@Test(expected = MethodInvocationException.class)
	public void failIfInvalidArgType() {
		assertOutByIn(new ExpectedOut("Skaičius: vienas"), "Skaičius: ${sveikasis(1 / 1)}", "-t");
	}
	
	@Test
	public void testStartEndTags() {
		assertOutByIn(new ExpectedOut("Skaičius: ${sveikasis(1)}"), "Skaičius: ${sveikasis(1)}", "-t", "-s", "#[", "-e", "]");
		assertOutByIn(new ExpectedOut("Skaičius: vienas"), "Skaičius: #[sveikasis(1)]", "-t", "-s", "#[", "-e", "]");
		assertOutByIn(new ExpectedOut("Skaičius: vienas"), "Skaičius: <? sveikasis(1) ?>", "-t", "-s", "<?", "-e", "?>");
	}
	
	@Test
	public void testOutputEncoding() {
		systemIo.setOutputCharsetName(WIN1257);
		assertOutByIn(new ExpectedOut("dešimt", WIN1257), "${sveikasis(10)}", "-t", "-oe", WIN1257);
		systemIo.setOutputCharsetName(UTF8);
		assertOutByIn(new ExpectedOut("dešimt", UTF8), "${sveikasis(10)}", "-t", "-oe", UTF8);
	}
	
	@Test
	public void testInputEncoding() {
		systemIo.setInputCharsetName(WIN1257);
		assertOutByIn(new ExpectedOut("dešimt"), "dešimt", "-t", "-ie", WIN1257);
		systemIo.setInputCharsetName(UTF8);
		assertOutByIn(new ExpectedOut("dešimt"), "dešimt", "-t", "-ie", UTF8);
	}
	
}
