package eu.vytenis.skaitvardziai.app.main;

import org.junit.Test;

import eu.vytenis.skaitvardziai.app.template.TemplateProcessor.TemplateParseException;
import eu.vytenis.skaitvardziai.parser.methods.Invoker.MethodInvocationException;
import eu.vytenis.skaitvardziai.parser.methods.Methods.SkaitvardziaiParseException;

public class TemplateTest extends AppTest {

	@Test
	public void testStatic() {
		assertOutByIn(new Out("test\na"), "test\na", "-t");
	}
	
	@Test
	public void testSveikieji() {
		assertOutByIn(new Out("Skaičius: vienas"), "Skaičius: ${sveikasis(1)}", "-t");
		assertOutByIn(new Out("Skaičius: trys šimtai tūkstančių keturi šimtai penkiasdešimt"), "Skaičius: ${sveikasis(300450)}", "-t");		
		assertOutByIn(new Out("Skaičius: penki šimtai dvyliktąsias"), "Skaičius: ${sveikasis(512, 'Iv,Kl,SD,G,MG')}", "-t");		
		assertOutByIn(new Out("Skaičius: minus vieno"), "Skaičius: ${sveikasis(-1, \"K\")}", "-t");
	}
	
	@Test
	public void testTrupmenos() {
		assertOutByIn(new Out("viena antroji yra mažiau nei dvi trečiosios"), "${trupmena(1/2)} yra mažiau nei ${trupmena(2/3)}", "-t");
		assertOutByIn(new Out("minus keturias šeštąsias"), "${trupmena(-4/6, 'G')}", "-t");
	}
	
	@Test
	public void testKiti() {
		assertOutByIn(new Out("10 žmonių"), "10 ${kiti(10, 'žmogus', 'žmonės', 'žmonių')}", "-t");
		assertOutByIn(new Out("1 žmogus"), "1 ${kiti(1, 'žmogus', 'žmonės', 'žmonių')}", "-t");
		assertOutByIn(new Out("22 žmonės"), "22 ${kiti(22, 'žmogus', 'žmonės', 'žmonių')}", "-t");
		
		assertOutByIn(new Out("10 žmonių"), "10 ${kiti(10, 'N', 'žmogui', 'žmonėms', 'žmonių')}", "-t");
		assertOutByIn(new Out("1 žmogui"), "1 ${kiti(1, 'N', 'žmogui', 'žmonėms', 'žmonių')}", "-t");
		assertOutByIn(new Out("22 žmonėms"), "22 ${kiti(22, 'N', 'žmogui', 'žmonėms', 'žmonių')}", "-t");
	}
	
	@Test(expected = SkaitvardziaiParseException.class)
	public void failIfManyStatements() {
		assertOutByIn(new Out("Skaičius: penki šimtai dvylika"), "Skaičius: ${sveikasis(512), sveikasis(513)}", "-t");
	}

	@Test(expected = TemplateParseException.class)
	public void failIfTagNotClosed() {
		assertOutByIn(new Out("Skaičius: ${"), "Skaičius: ${", "-t");
	}
	
	@Test(expected = MethodInvocationException.class)
	public void failIfInvalidArgType() {
		assertOutByIn(new Out("Skaičius: vienas"), "Skaičius: ${sveikasis(1 / 1)}", "-t");
	}
	
	@Test
	public void testStartEndTags() {
		assertOutByIn(new Out("Skaičius: ${sveikasis(1)}"), "Skaičius: ${sveikasis(1)}", "-t", "-s", "#[", "-e", "]");
		assertOutByIn(new Out("Skaičius: vienas"), "Skaičius: #[sveikasis(1)]", "-t", "-s", "#[", "-e", "]");
		assertOutByIn(new Out("Skaičius: vienas"), "Skaičius: <? sveikasis(1) ?>", "-t", "-s", "<?", "-e", "?>");
	}
	
	@Test
	public void testOutputEncoding() {
		outputEncoding = "windows-1257";
		assertOutByIn(new Out("dešimt", "windows-1257"), "${sveikasis(10)}", "-t", "-oe", "windows-1257");
		outputEncoding = "utf-8";
		assertOutByIn(new Out("dešimt", "utf-8"), "${sveikasis(10)}", "-t", "-oe", "utf-8");
	}
}
