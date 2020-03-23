package com.cross.qa.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.cross.qa.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class SignUpPage extends TestBase{
	 // Initializing the Page Objects: 
	  public SignUpPage() {
	  PageFactory.initElements(driver, this); }

	@FindBy(id = "id_gender2")
	WebElement MrsSalutation;
	
	@FindBy(id = "customer_firstname")
	WebElement firstNameTextBox;
	
	@FindBy(id = "customer_lastname")
	WebElement lastNameTextBox;
	
	@FindBy(id="passwd")
	WebElement passwordTextBox;
	
	@FindBy(id="address1")
	WebElement addressLine1TextBox;
	
	@FindBy(id = "city")
	WebElement cityTextBox;
	
	public void selectState(String stateName) {
		Select stateDropdown = new Select(driver.findElement(By.id("id_state")));
		stateDropdown.selectByVisibleText(stateName);
	}
	
	@FindBy(how = How.ID, using = "postcode")
	WebElement zipCodeTextBox;
	
	@FindBy(how = How.ID, using = "phone_mobile")
	WebElement mobileNumberTextBox;
	
	@FindBy(how = How.NAME , using ="submitAccount")
	 WebElement registerButton;
	
	@FindBy(how = How.CLASS_NAME ,using ="info-account")
	public WebElement accountCreationSuccessMessage;

	public void createAccount() {
		try {
		MrsSalutation.click();
		String fname = RandomStringUtils.randomAlphabetic(7);
		firstNameTextBox.sendKeys(fname);
		String lname = RandomStringUtils.randomAlphabetic(3);
		lastNameTextBox.sendKeys(lname);
		String pwd = "Test@"+RandomStringUtils.randomAlphanumeric(5);
		tempDataMap.put("password",pwd);
		passwordTextBox.sendKeys(pwd);
		
		
		
		String addressLine1 = RandomStringUtils.randomAlphabetic(11);
		
		addressLine1TextBox.sendKeys(addressLine1);
String city = RandomStringUtils.randomAlphabetic(10);
		
cityTextBox.sendKeys(city);
selectState("Alabama");
String zipCode = RandomStringUtils.randomNumeric(5);

zipCodeTextBox.sendKeys(zipCode);

String mobileNumber = RandomStringUtils.randomNumeric(10);
mobileNumberTextBox.sendKeys(mobileNumber);

registerButton.click();

assertTrue(accountCreationSuccessMessage.isDisplayed(),"Account Creation success Message is not being displayed");
logger.log(LogStatus.PASS, "Account Creation success Message is being displayed");


		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
