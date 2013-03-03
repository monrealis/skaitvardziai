package eu.vytenis.skaitvardziai.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import eu.vytenis.skaitvardziai.checks.ChecksTest;
import eu.vytenis.skaitvardziai.checks.RangeEndTest;
import eu.vytenis.skaitvardziai.checks.RangeTest;
import eu.vytenis.skaitvardziai.klasifikatoriai.SkaiciusIrLinksnisTest;
import eu.vytenis.skaitvardziai.skaiciai.DauginiaiMotTest;
import eu.vytenis.skaitvardziai.skaiciai.DauginiaiVyrTest;
import eu.vytenis.skaitvardziai.skaiciai.KelintiniaiIvTest;
import eu.vytenis.skaitvardziai.skaiciai.KelintiniaiTest;
import eu.vytenis.skaitvardziai.skaiciai.KuopiniaiTest;
import eu.vytenis.skaitvardziai.skaiciai.PagrindiniaiMotTest;
import eu.vytenis.skaitvardziai.skaiciai.PagrindiniaiVyrTest;
import eu.vytenis.skaitvardziai.skaiciai.PoskyrisTest;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaiciusTest;
import eu.vytenis.skaitvardziai.skaiciai.TrupmenosTest;
import eu.vytenis.skaitvardziai.xpath.SkaiciusXPathFunctionsJreTest;
import eu.vytenis.skaitvardziai.xpath.SkaiciusXPathFunctionsSaxonTest;
import eu.vytenis.skaitvardziai.xpath.SkaiciusXPathFunctionsXalanTest;
import eu.vytenis.skaitvardziai.zodziai.ZodisTest;

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
	SkaiciusXPathFunctionsSaxonTest.class,
	SkaiciusXPathFunctionsXalanTest.class,

	SveikasisSkaiciusTest.class,
	RangeTest.class,
	RangeEndTest.class,
	ChecksTest.class,
	SkaiciusIrLinksnisTest.class,
	ZodisTest.class})
@RunWith(Suite.class)
public class SkaitvardziaiTestSuite {

}
