package com.bank.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import org.apache.poi.ss.formula.atp.Switch;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.bank.qa.utils.TestUtils;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase()
	{
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\navee\\eclipse-workspace\\BankApplicationTest\\src\\main\\java\\com\\bank\\qa\\config\\config.properties");
			prop.load(ip);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization()
	{
        String browserName =  prop.getProperty("browser");
		
		if(browserName.equals("chrome"))
		{
			 driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox"))
		{
			 driver = new FirefoxDriver();
		}
		else if(browserName.equals("Edge"))
		{
			 driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		
		//e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		//eventListener = new WebEventListener();
		//e_driver.register(eventListener);
		//driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtils.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtils.IMPLICITWAIT_TIMEOUT));
		
		driver.get(prop.getProperty("URL"));
	}
		
	public static WebDriver initializeBrowserRemote() 
	{
		String browserName =  prop.getProperty("browser");
		
		DesiredCapabilities dc = new DesiredCapabilities();
		
		if(browserName.equals("chrome"))
		{
			dc.setBrowserName("chrome");
		}
		else if(browserName.equals("firefox"))
		{
			dc.setBrowserName("firefox");
		}
		else if(browserName.equals("edge"))
		{
			dc.setBrowserName("MicrosoftEdge");
		}
		else if(browserName.equals("ie"))	
		{
			dc.setBrowserName("internet explorer");
		}
		else if(browserName.equals("opera"))
		{
			dc.setBrowserName("opera");
		}
		else if(browserName.equals("safari"))
		{
			dc.setBrowserName("safari");
		}
		try {
			driver = new RemoteWebDriver(new URL("http://localhost:4444"),dc);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.get(prop.getProperty("URL"));
		return driver;
	}
}