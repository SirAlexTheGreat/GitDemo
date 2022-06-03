package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@SuppressWarnings("deprecation")
public class HomepageObjects {

	public WebDriver driver;
	
	private By loginBtn = By.cssSelector("#login2");
	private By username = By.xpath("//input[@id='loginusername']");
	private By password = By.xpath("//input[@id='loginpassword']");
	private By login	= By.xpath("//button[contains(text(),'Log in')]");
	private By cartLink = By.xpath("//a[@id='cartur']");
	private By aboutUs = By.xpath("//a[contains(text(),'About us')]");
	
	public HomepageObjects(WebDriver driver) {
		this.driver=driver;
	}

	public WebElement getLoginBtn()
	{
		return driver.findElement(loginBtn);
	}

	public WebElement getUsername()
	{
		return driver.findElement(username);
	}
	
	public WebElement getPassword()
	{
		return driver.findElement(password);
	}
	
	public WebElement getlogin()
	{
		return driver.findElement(login);
	}
	
	public WebElement getCartLink()
	{
		return driver.findElement(cartLink);
	}
	
	public WebElement getAboutUsLink()
	{
		return driver.findElement(aboutUs);
	}
}
