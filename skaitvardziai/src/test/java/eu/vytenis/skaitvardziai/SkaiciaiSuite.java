package eu.vytenis.skaitvardziai;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import eu.vytenis.skaitvardziai.xpath.SkaiciusXPathFunctionsJreTest;
import eu.vytenis.skaitvardziai.xpath.SkaiciusXPathFunctionsSaxonTest;

@SuiteClasses({
	PagrindiniaiVyrTest.class,
	PagrindiniaiMotTest.class,
	DauginiaiVyrTest.class,
	DauginiaiMotTest.class,
	KuopiniaiTest.class,	
	KelintiniaiTest.class,
	KelintiniaiIvTest.class,
	PoskyrisTest.class,
	TrupmenosTest.class,
	
	SkaiciusXPathFunctionsJreTest.class,
	SkaiciusXPathFunctionsSaxonTest.class})
@RunWith(Suite.class)
public class SkaiciaiSuite {

}
