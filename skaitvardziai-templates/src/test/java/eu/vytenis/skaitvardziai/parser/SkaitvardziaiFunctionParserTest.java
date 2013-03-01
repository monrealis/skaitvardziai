package eu.vytenis.skaitvardziai.parser;

import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

import eu.vytenis.parser.ParseException;
import eu.vytenis.parser.SimpleNode;
import eu.vytenis.parser.SkaitvardziaiFunctionParser;

public class SkaitvardziaiFunctionParserTest {
	
	@Test
	public void testStart() throws ParseException {
		SimpleNode n;
		n = parse("ff(1)");
		n = parse(" ff(1)");
		n = parse("ff(1) ");
		n = parse(" ff(1) ");
		n.dump("");
		n = parse("ff(1, 1)");
		n = parse("ff('a')");
		n = parse("ff(\"b\")");
		n.dump("");
		Assert.assertNotNull(n);
		
		
	}

	private SimpleNode parse(String text) throws ParseException {
		return new SkaitvardziaiFunctionParser(new StringReader(text)).start();
	}


}
