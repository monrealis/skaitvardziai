package eu.vytenis.skaiciai;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses({
	PagrindiniaiVyrTest.class,
	PagrindiniaiMotTest.class,
	KuopiniaiTest.class,
	DauginiaiTest.class,
	KelintiniaiTest.class,
	KelintiniaiIvTest.class,
	PoskyrisTest.class})
@RunWith(Suite.class)
public class SkaiciaiSuite {

}
