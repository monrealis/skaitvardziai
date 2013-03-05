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
import eu.vytenis.skaitvardziai.skaiciai.KelintiniaiPaprTest;
import eu.vytenis.skaitvardziai.skaiciai.KuopiniaiTest;
import eu.vytenis.skaitvardziai.skaiciai.PagrindiniaiMotTest;
import eu.vytenis.skaitvardziai.skaiciai.PagrindiniaiVyrTest;
import eu.vytenis.skaitvardziai.skaiciai.PoskyrisTest;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaiciusTest;
import eu.vytenis.skaitvardziai.skaiciai.TrupmenosTest;
import eu.vytenis.skaitvardziai.xpath.SkaitvardziaiXPathFunctionsJreTest;
import eu.vytenis.skaitvardziai.xpath.SkaitvardziaiXPathFunctionsSaxonTest;
import eu.vytenis.skaitvardziai.xpath.SkaitvardziaiXPathFunctionsXalanTest;
import eu.vytenis.skaitvardziai.zodziai.ZodisTest;

@SuiteClasses({
	PagrindiniaiVyrTest.class,
	PagrindiniaiMotTest.class,
	DauginiaiVyrTest.class,
	DauginiaiMotTest.class,
	KuopiniaiTest.class,	
	KelintiniaiPaprTest.class,
	KelintiniaiIvTest.class,
	PoskyrisTest.class,
	TrupmenosTest.class,
	
	SkaitvardziaiXPathFunctionsJreTest.class,
	SkaitvardziaiXPathFunctionsSaxonTest.class,
	SkaitvardziaiXPathFunctionsXalanTest.class,

	SveikasisSkaiciusTest.class,
	RangeTest.class,
	RangeEndTest.class,
	ChecksTest.class,
	SkaiciusIrLinksnisTest.class,
	ZodisTest.class})
@RunWith(Suite.class)
public class SkaitvardziaiTestSuite {

}
