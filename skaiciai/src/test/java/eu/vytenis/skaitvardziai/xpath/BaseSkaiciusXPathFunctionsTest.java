package eu.vytenis.skaitvardziai.xpath;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.junit.Assert;
import org.junit.Test;


/**
 * Bazinis XPath funkcijų testas. Patikrina, ar suvykdžios XML'o <root /> transformaciją
 * gautas rezultatas yra toks, kokio tikimasi.
 *
 */
public abstract class BaseSkaiciusXPathFunctionsTest {
	
	/** XSLT bylos kelias (nuo {@link BaseSkaiciusXPathFunctionsTest}). */
	private String defaultXsltResourceName = "saxon-transform.xsl";
	/** {@link TransformerFactory} realizuojančios klasės pavadinimas. */
	private String transformerFactoryClass;
	
	/**
	 * Sukuria su pradinėmis reikšmėmis.
	 * @param xsltResourceName XSLT resurso pavadinimas
	 * @param transformerFactoryClass XSLT procesoriaus klasės pavadinimas
	 */
	public BaseSkaiciusXPathFunctionsTest(String transformerFactoryClass) {
		this.transformerFactoryClass = transformerFactoryClass;
	}

	
	/**
	 * Grąžina XSLT procesoriaus klasę pagal {@link #transformerFactoryClass} reikšmę.
	 * @return XSLT procesorius
	 * @throws Exception klaida
	 */
	protected TransformerFactory getTransformerFactory() throws Exception {
		return (TransformerFactory) Class.forName(transformerFactoryClass).newInstance();
	}
	
	
	protected String getXsltText() throws IOException {
		InputStream is = BaseSkaiciusXPathFunctionsTest.class.getResourceAsStream(defaultXsltResourceName);
		byte[] bytes = new byte[is.available()];
		is.read(bytes);
		is.close();
		return new String(bytes, "UTF-8");
	}
	
	/**
	 * Grąžina XSL transformacija {@link Source} formatu pagal lauką {@link #defaultXsltResourceName}.
	 * @return XSL transformacija
	 * @throws Exception klaida
	 */
	protected Source getXsltSource() throws Exception {
		String text = getXsltText();
		StreamSource transform = new StreamSource(new StringReader(text));
		return transform;
	}
	
	
	/**
	 * Patikrina, ar įvykdyta XSL transformaciją suformuoja tokį XML'ą, kokį reikia.
	 * Po transformacijos gautas tekstas turi būti toks: faktinis_tekstas_1 : reikalingas_tekstas_1 ; faktinis_tekstas_2 : reikalingas_tekstas_2 ; ... .
	 * Jei faktinis_tekstas_n nesutampa su reikalingu_tekstu_n - klaida. 
	 * {@link BaseSkaiciusXPathFunctionsTest} realizuojančios klasės metodą turėtų padaryti public ir uždėti anotacija {@link Test}.
	 * @throws Exception klaida
	 */
	protected void testXslt() throws Exception {		
		StreamSource input = new StreamSource(BaseSkaiciusXPathFunctionsTest.class.getResourceAsStream("input.xml"));
		StringWriter w = new StringWriter();
		getTransformerFactory().newTransformer(getXsltSource()).transform(input, new StreamResult(w));
		String output = w.toString().trim();
		System.out.println(output);
		
		String[] lines = output.split("\\s*;\\s*");
		List<String> invalidLines = new ArrayList<String>();
		for (String line : lines) {
			String[] actualExpected = line.split("\\s*:\\s*");
			if (actualExpected.length != 2) {
				invalidLines.add(line);
			} else {
				String actual = actualExpected[0];
				String expected = actualExpected[1];
				if (!actual.equals(expected)) {
					invalidLines.add(line);
				}
			}
		}
		Assert.assertEquals(new ArrayList<String>(), invalidLines);
	}
}
