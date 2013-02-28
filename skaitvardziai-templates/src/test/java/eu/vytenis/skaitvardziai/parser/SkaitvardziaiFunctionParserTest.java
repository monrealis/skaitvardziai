package eu.vytenis.skaitvardziai.parser;

import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

import eu.vytenis.parser.ParseException;
import eu.vytenis.parser.SimpleNode;
import eu.vytenis.parser.SkaitvardziaiFunctionParser;

@SuppressWarnings("static-access")
public class SkaitvardziaiFunctionParserTest {
	
	@Test
	public void testStart() throws ParseException {
		SimpleNode n = new SkaitvardziaiFunctionParser(new StringReader("ff(1)")).start();
		Assert.assertNotNull(n);
		n.dump("");
		
	}


}
