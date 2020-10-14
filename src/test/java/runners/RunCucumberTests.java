package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		glue = {"steps"},
		features = "src/test/resources/features/CreateUser.feature",
		plugin = "json:target/cucumber-report.json")
public class RunCucumberTests extends AbstractTestNGCucumberTests {}