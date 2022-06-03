package Learning;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.CartpageObjects;
import pageObjects.HomepageObjects;
import resources.base;

@SuppressWarnings("deprecation")
public class CartpageTest extends base{
	
	public WebDriver driver;

	public static Logger log = LogManager.getLogger(base.class.getName());
	
	@BeforeTest
	public void initilize() throws IOException
	{
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
	}
	
	@Test
	public void cartPageElements() throws IOException
	{
		
		HomepageObjects ho = new HomepageObjects(driver);
		ho.getCartLink().click();
		CartpageObjects cp = new CartpageObjects(driver);
		Assert.assertTrue(cp.getPageTitle().getText().contains("Produc1ts"));
		log.info("Cartpage Test done");
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.close();
	}
	
}
