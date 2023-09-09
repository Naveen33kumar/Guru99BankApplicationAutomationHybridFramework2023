package com.bank.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bank.qa.base.TestBase;

public class HomePage extends TestBase{

	// Page Factory - OR(Object Repo)
		@FindBy(xpath="//a[contains(text(),'Manager')]")
		WebElement ManagerText;
		
		@FindBy(xpath="//a[contains(text(),'Edit Account')]")
		WebElement EditAccountLink;
		
		@FindBy(xpath="//a[contains(text(),'Fund Transfer')]")
		WebElement FundTransferLink;
		
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean checkManagerText()
	{
		return ManagerText.isDisplayed();
	}
	
	public String getHomePageTitle()
	{
		return driver.getTitle();
	}
	
	public EditAccountPage clickEditAccountLink()
	{
		EditAccountLink.click();
		return new EditAccountPage();
	}
	
	public FundTransferPage clickFunTranfertLink()
	{
		EditAccountLink.click();
		return new FundTransferPage();
	}
}