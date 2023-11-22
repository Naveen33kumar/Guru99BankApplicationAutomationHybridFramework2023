package com.bank.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.bank.qa.base.TestBase;
import com.bank.qa.pages.EditAccountPage;
import com.bank.qa.pages.HomePage;
import com.bank.qa.pages.LoginPage;
import com.bank.qa.listener.CustomListener;
import com.bank.qa.utils.TestUtils;


//@Listeners(CustomListener.class)
public class EditAccountPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	EditAccountPage editAccountPage;
	
	public EditAccountPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		initializeBrowserRemote();
	}

	@Test(priority = 1)
	public void verifyInvalidAccountNumber()
	{
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		editAccountPage = homePage.clickEditAccountLink();
		TestUtils testUtils = editAccountPage.setCustomerId("87555555");
		editAccountPage.clickSubmit();
		System.out.println(testUtils.getAlertText());
		testUtils.acceptAlert();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}