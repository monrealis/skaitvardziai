package eu.vytenis.skaiciai;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses({
	PagrindiniaiVyrTest.class,
	PagrindiniaiMotTest.class,
	DauginiaiVyrTest.class,
	DauginiaiMotTest.class,
	KuopiniaiTest.class,	
	KelintiniaiTest.class,
	KelintiniaiIvTest.class,
	PoskyrisTest.class,
	TrupmenosTest.class})
@RunWith(Suite.class)
public class SkaiciaiSuite {

}
