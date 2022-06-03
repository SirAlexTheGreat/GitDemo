package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@SuppressWarnings("deprecation")
public class CartpageObjects {

	public WebDriver driver;
	
	private By pageTitle = By.cssSelector("#page-wrapper");
	private By placeOrderBtn = By.xpath("//button[@type='button']");
	
	public CartpageObjects(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public WebElement getPageTitle()
	{
		return driver.findElement(pageTitle);
	}
}
