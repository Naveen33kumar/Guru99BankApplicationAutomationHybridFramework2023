package com.bank.qa.testcases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bank.qa.base.TestBase;
import com.bank.qa.pages.LoginPage;
import com.bank.qa.utils.TestUtils;

//@Listeners(CustomListener.class)
// This is invalid login Test
public class LoginPageInvalidLoginsTest extends TestBase{

	LoginPage loginPage;
	TestUtils testUtils;
	String sheetName = "InvalidLoginDatas";
	
	public LoginPageInvalidLoginsTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		initialization();
		loginPage = new LoginPage();
		testUtils = new TestUtils();
	}
	
	@DataProvider
	public Object[][] getTestData()
	{
		Object[][]data = TestUtils.getTestData(sheetName);
		return data;
	}
	
	@Test(dataProvider = "getTestData")
	public void verifyInvalidLogins(String username, String password)
	{
		loginPage.login(username, password);
		assertEquals(testUtils.getAlertText(), "User or Password is not valid");
		testUtils.acceptAlert();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}