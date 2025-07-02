package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(

		features = "src/test/resources/features",

		// There are steps definitions in steps package.
		glue = { "steps" },

		// pretty for console output report ,html for html report,json for advance level
		// report

		plugin = { "pretty", "html:target/HTMLReport/cucumber-reports.html", "json:target/JSONReport/cucumber.json" },

		monochrome = true,
		tags = "@smoke and not @negative"
)

public class TestRunner {

}
