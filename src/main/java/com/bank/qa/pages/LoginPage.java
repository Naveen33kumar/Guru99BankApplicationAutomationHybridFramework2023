package com.bank.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bank.qa.base.TestBase;

public class LoginPage extends TestBase{

	// Page Factory - OR(Object Repo)
	@FindBy(name="uid")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(name="btnLogin")
	WebElement loginButton;
	
	@FindBy(name="btnReset")
	WebElement resetButton;
	
	@FindBy(xpath="/html/body/div[1]/div[1]/div[1]/div[1]/a")
	WebElement siteLogo;
	
	// Initializing the page objects
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public HomePage login(String un, String pw) {
		
		username.clear();
		username.sendKeys(un);
		
		password.clear();
		password.sendKeys(pw);
		
		loginButton.click();
		
		return new HomePage();
	}
	
	public String getHomePageTitle()
	{
		String homePageTitle = driver.getTitle();
		return homePageTitle;
	}
	
	public boolean validateSiteLogo()
	{
		return siteLogo.isDisplayed();
		
	}
	
	
}