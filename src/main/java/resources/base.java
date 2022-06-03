package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class base {

	public WebDriver driver;
	public Properties prop;
	public static Logger log = LogManager.getLogger(base.class.getName());
	
	public WebDriver initializeDriver() throws IOException {
		
		//System.getProperty("user.dir")+
		FileInputStream fis=new FileInputStream(".\\src\\main\\java\\resources\\data.properties");
		prop = new Properties();
		
		prop.load(fis);
		
		//Run on Jenkins
//		String browserName = System.getProperty("browser");
		
		//Run from Eclipse
		String browserName = prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			//execute in chromedriver
			System.setProperty("webdriver.chrome.driver",".\\src\\main\\java\\resources\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}

			driver = new ChromeDriver(options);
			
		}
		else if(browserName.contains("firefox"))
		{
			//execute in geckodriver
			System.setProperty("webdriver.gecko.driver",".\\src\\main\\java\\resources\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.setBinary("D:\\Programs\\FireFox\\firefox.exe");  
			if(browserName.contains("headless"))
			{
				options.setHeadless(true);;
			}
			driver = new FirefoxDriver(options);
		}
		else if(browserName.equals("IE"))
		{
			//execute in IEdriver
			System.setProperty("webdriver.ie.driver",".\\src\\main\\java\\resources\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
		
		return driver;
	}
	
	
	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException
	{
//		TakesScreenshot ts=(TakesScreenshot) driver;
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationFile = ".\\reports\\"+testCaseName+".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}
	
}
