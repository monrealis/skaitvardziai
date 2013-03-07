package eu.vytenis.skaitvardziai.app.main;

import org.junit.Test;

import eu.vytenis.skaitvardziai.app.template.TemplateProcessor.TemplateParseException;
import eu.vytenis.skaitvardziai.parser.Invoker.MethodInvocationException;
import eu.vytenis.skaitvardziai.parser.Methods.SkaitvardziaiParseException;

public class TemplateTest extends AppTest {

	@Test
	public void testStatic() {
		assertOutByIn("test\na", "test\na", "-t");
	}
	
	@Test
	public void testSveikieji() {
		assertOutByIn("Skaičius: vienas", "Skaičius: ${sveikasis(1)}", "-t");
		assertOutByIn("Skaičius: trys šimtai tūkstančių keturi šimtai penkiasdešimt", "Skaičius: ${sveikasis(300450)}", "-t");		
		assertOutByIn("Skaičius: penki šimtai dvyliktąsias", "Skaičius: ${sveikasis(512, 'Iv,Kl,SD,G,MG')}", "-t");		
		assertOutByIn("Skaičius: minus vieno", "Skaičius: ${sveikasis(-1, \"K\")}", "-t");
	}
	
	@Test
	public void testTrupmenos() {
		assertOutByIn("viena antroji yra mažiau nei dvi trečiosios", "${trupmena(1/2)} yra mažiau nei ${trupmena(2/3)}", "-t");
		assertOutByIn("minus keturias šeštąsias", "${trupmena(-4/6, 'G')}", "-t");
	}
	
	@Test
	public void testKiti() {
		assertOutByIn("10 žmonių", "10 ${kiti(10, 'žmogus', 'žmonės', 'žmonių')}", "-t");
		assertOutByIn("1 žmogus", "1 ${kiti(1, 'žmogus', 'žmonės', 'žmonių')}", "-t");
		assertOutByIn("22 žmonės", "22 ${kiti(22, 'žmogus', 'žmonės', 'žmonių')}", "-t");
		
		assertOutByIn("10 žmonių", "10 ${kiti(10, 'N', 'žmogui', 'žmonėms', 'žmonių')}", "-t");
		assertOutByIn("1 žmogui", "1 ${kiti(1, 'N', 'žmogui', 'žmonėms', 'žmonių')}", "-t");
		assertOutByIn("22 žmonėms", "22 ${kiti(22, 'N', 'žmogui', 'žmonėms', 'žmonių')}", "-t");
	}
	
	@Test(expected = SkaitvardziaiParseException.class)
	public void failIfManyStatements() {
		assertOutByIn("Skaičius: penki šimtai dvylika", "Skaičius: ${sveikasis(512), sveikasis(513)}", "-t");
	}

	@Test(expected = TemplateParseException.class)
	public void failIfTagNotClosed() {
		assertOutByIn("Skaičius: ${", "Skaičius: ${", "-t");
	}
	
	@Test(expected = MethodInvocationException.class)
	public void failIfInvalidArgType() {
		assertOutByIn("Skaičius: vienas", "Skaičius: ${sveikasis(1 / 1)}", "-t");
	}
}
