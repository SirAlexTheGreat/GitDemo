package cucumberOptions;


import io.cucumber.testng.CucumberOptions;

import org.testng.annotations.Listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
@CucumberOptions(

//		stepNotifications = true,
		features = "src/test/java/features",
		glue = "stepDefinitions")

public class TestRunner extends AbstractTestNGCucumberTests{
	
}
