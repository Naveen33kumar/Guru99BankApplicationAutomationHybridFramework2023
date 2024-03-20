package com.bank.qa.testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.bank.qa.base.TestBase;
import com.bank.qa.pages.EditAccountPage;
import com.bank.qa.pages.HomePage;
import com.bank.qa.pages.LoginPage;
import com.bank.qa.listener.CustomListener;

//@Listeners(CustomListener.class)
public class HomePageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	
	public HomePageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		//initializeBrowserRemote();
		initialization();
	}
	
	@Test(priority = 1)
	public void verifyHomePageTitle()
	{
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		String homePageTitle = homePage.getHomePageTitle();
		assertEquals(homePageTitle, "Guru99 Bank Manager HomePage", "Title Not Matched");
	}
	
	@Test(priority = 2)
	public void verifyManagerLabel()
	{
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		assertTrue(homePage.checkManagerText());
	}
	
	@Test(priority = 3)
	public void verifyEditLinkTest()
	{
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		EditAccountPage editAccountPage = homePage.clickEditAccountLink();
		assertTrue(editAccountPage.accountFormLabelDisplay());
		assertEquals(editAccountPage.getEditAccountPageTitle(), "Guru99 Edit Account Page", "Edit Account Page Title Not Matched");
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}