package eu.vytenis.skaitvardziai.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import eu.vytenis.skaitvardziai.app.main.FilesTest;
import eu.vytenis.skaitvardziai.app.main.MainTest;
import eu.vytenis.skaitvardziai.app.template.TemplateProcessorTest;
import eu.vytenis.skaitvardziai.app.template.TemplateTest;

@RunWith(Suite.class)
@SuiteClasses({MainTest.class, TemplateTest.class, TemplateProcessorTest.class, FilesTest.class})
public class AppTestSuite {

}
