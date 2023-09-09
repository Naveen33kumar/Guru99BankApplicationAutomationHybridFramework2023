package com.bank.qa.testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bank.qa.base.TestBase;
import com.bank.qa.pages.HomePage;
import com.bank.qa.pages.LoginPage;

//@Listeners(CustomListener.class)
public class LoginTest extends TestBase{

	LoginPage loginPage;
	
	public LoginTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		initialization();
		loginPage = new LoginPage();
	}
	
	@Test(priority = 1)
	public void loginPageTitleTest()
	{
		String homePageActualTitle = loginPage.getHomePageTitle();
		assertEquals(homePageActualTitle, "Guru99 Bank Home Page");
	}
	
	@Test(priority = 2)
	public void verifySiteLogo()
	{
		boolean flag = loginPage.validateSiteLogo();
		assertTrue(flag);
	}
	
	@Test(priority = 3)
	public void verifyLogin()
	{
		HomePage homepage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		String homePageTitle = homepage.getHomePageTitle();
		assertEquals(homePageTitle, "Guru99 Bank Manager HomePage", "Title Not Matched");
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}