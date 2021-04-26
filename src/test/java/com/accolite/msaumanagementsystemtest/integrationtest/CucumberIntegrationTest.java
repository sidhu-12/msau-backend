package com.accolite.msaumanagementsystemtest.integrationtest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features" , glue = {"LoginStepDefinition"})
public class CucumberIntegrationTest  {

		
		
}
