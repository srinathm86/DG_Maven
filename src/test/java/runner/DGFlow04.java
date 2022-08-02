package runner;

import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * @author Sharath
 * 
 */

@CucumberOptions(features = { "src/test/java/features" }, glue = { "stepdefinitions" }, plugin = { "pretty",
		"html:target/cucumber", "json:target/cucumber.json" }, tags = { "@DGFlow04" })
@Test
public class DGFlow04 extends AbstractTestNGCucumberTests {
}