package com.bank.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bank.qa.base.TestBase;
import com.bank.qa.utils.TestUtils;

public class EditAccountPage extends TestBase{

	@FindBy(xpath="//p[contains(text(),'Edit Account Form')]")
	WebElement EditAccountFormLabel;
	
	@FindBy(xpath="//input[@name='accountno']")
	WebElement CustomerIdField;
	
	@FindBy(name="AccSubmit")
	WebElement SubmitBtn;
	
	public EditAccountPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean accountFormLabelDisplay()
	{
		return EditAccountFormLabel.isDisplayed();
	}
	
	public String getEditAccountPageTitle()
	{
		return driver.getTitle();
	}
	
	public TestUtils setCustomerId(String id)
	{
		CustomerIdField.sendKeys(id);
		return new TestUtils();
	}
	
	public void clickSubmit()
	{
		SubmitBtn.click();
	}
}
