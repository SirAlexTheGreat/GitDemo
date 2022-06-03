package stepDefinitions;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import pageObjects.CartpageObjects;
import pageObjects.HomepageObjects;
import resources.base;

@RunWith(Cucumber.class)
public class StepDefinitions extends base{
	
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(base.class.getName());
	HomepageObjects ho;
	CartpageObjects cp;
	JavascriptExecutor jsExecutor;
	WebDriverWait wait; 

    @Given("^Initialized browser with chrome$")
    public void initialized_browser_with_chrome() throws Throwable {
    	driver = initializeDriver();
		jsExecutor = (JavascriptExecutor)driver;
    }
    
    @And("^Navigated to homepage \"([^\"]*)\"$")
    public void navigated_to_homepage_something(String strArg1) throws Throwable {
    	driver.get(strArg1);
    }
    
    @And("^User has clicked on Login$")
    public void user_has_clicked_on_login() throws Throwable {
    	
		ho = new HomepageObjects(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		
		try {
			wait.until(ExpectedConditions.elementToBeClickable(ho.getLoginBtn()));
			ho.getLoginBtn().click();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

    @When("^User enters (.+) and (.+)$")
    public void user_enters_something_and_something(String username, String password) throws Throwable {
		jsExecutor.executeScript("arguments[0].value='"+username+"'", ho.getUsername());
		jsExecutor.executeScript("arguments[0].value='"+password+"'", ho.getPassword());
    }
    
    @And("^Cicks on Login button$")
    public void cicks_on_login_button() throws Throwable {
    	jsExecutor.executeScript("arguments[0].click();", ho.getlogin());
    }

    @Then("^Alert is displayed$")
    public void alert_is_displayed() throws Throwable {
    	
    	wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		try {
		    wait.until(ExpectedConditions.alertIsPresent());
		    driver.switchTo().alert().accept();
		} catch (Exception e) {
		    //exception handling
		}
		log.info("Homepage Test done");
		System.out.println("GitTest");
		System.out.println("GitTest2");
    }
    
    @And("^Close browsers$")
    public void close_browsers() throws Throwable {
    	driver.close();
    }
    
    @When("^User checks that the title of the page is \"([^\"]*)\"$")
    public void user_checks_that_the_title_of_the_page_is_something(String strArg1) throws Throwable {
    	Assert.assertTrue(cp.getPageTitle().getText().contains(strArg1));
    }

    @Then("^Close the drivers$")
    public void close_the_drivers() throws Throwable {
        driver.close();
    }

    @And("^User has clicked on Cart$")
    public void user_has_clicked_on_cart() throws Throwable {
    	ho.getCartLink().click();
    }

}