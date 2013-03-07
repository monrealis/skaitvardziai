package eu.vytenis.skaitvardziai.app.main;

import org.junit.Test;

public class TemplateTest extends AppTest {

	@Test
	public void testTemplate() {
		assertOutByIn("test", "test", "-t");
		assertOutByIn("Skaičius vienas", "Skaičius ${sveikasis(1)}", "-t");
	}

}
