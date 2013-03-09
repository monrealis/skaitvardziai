package eu.vytenis.skaitvardziai.parser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import eu.vytenis.skaitvardziai.parser.methods.InvokerTest;
import eu.vytenis.skaitvardziai.parser.methods.MethodsTest;
import eu.vytenis.skaitvardziai.parser.nodes.NodesTest;

@SuiteClasses({NodesTest.class, MethodsTest.class, InvokerTest.class})
@RunWith(Suite.class)
public class ParserTestSuite {

}
