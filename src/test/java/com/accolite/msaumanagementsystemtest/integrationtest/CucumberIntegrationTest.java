package com.accolite.msaumanagementsystemtest.integrationtest;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/features"}
//		stepNotifications = true,
//		plugin = {"pretty", "html:target/cucumber"},
//		dryRun = true
		)
public class CucumberIntegrationTest  {

		
		
}
