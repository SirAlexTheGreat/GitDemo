package Learning;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomepageObjects;
import resources.base;

@SuppressWarnings("deprecation")
public class HomepageTest extends base{

	public WebDriver driver;
	public static Logger log = LogManager.getLogger(base.class.getName());
	HomepageObjects ho;
	
	@BeforeTest
	public void initialize() throws IOException
	{
		driver = initializeDriver();
		
	}
	
	@Test(dataProvider="getData")
	public void basePageNavigation(String username, String password, String testText) throws IOException
	{
		driver.get(prop.getProperty("url"));
		ho = new HomepageObjects(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		
		try {
			wait.until(ExpectedConditions.elementToBeClickable(ho.getLoginBtn()));
			ho.getLoginBtn().click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//Javascript block
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].value='username'", ho.getUsername());
		jsExecutor.executeScript("arguments[0].value='password'", ho.getPassword());
		jsExecutor.executeScript("arguments[0].click();", ho.getlogin());
		
		//Selenium Block
//		wait.until(ExpectedConditions.visibilityOf(ho.getUsername()));
//		ho.getUsername().sendKeys(username);
//		ho.getPassword().sendKeys(password);
//		ho.getlogin().click();
		
		System.out.println(testText);
		
		try {
		    wait.until(ExpectedConditions.alertIsPresent());
		    driver.switchTo().alert().accept();
		} catch (Exception e) {
		    //exception handling
		}
		log.info("Homepage Test done");
	}
	
	@Test
	public void secondTest() throws IOException
	{
		driver.get(prop.getProperty("url"));
		ho.getAboutUsLink().click();
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] data = new Object[2][3];
		
		data[0][0] = "nonRestrictedUser";
		data[0][1] = "nonresPW";
		data[0][2] = "Good User";
		
		data[1][0] = "restrictedUser";
		data[1][1] = "resPW";
		data[1][2] = "Bad User";
		return data;
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.close();
	}
}
