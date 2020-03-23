package com.cross.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cross.qa.base.TestBase;

public class AuthenticationPage extends TestBase {
	
	 // Initializing the Page Objects: 
	  public AuthenticationPage() {
	  PageFactory.initElements(driver, this); }
	/*
	 * Create an Account Fields
	 */
	@FindBy(id = "email_create")
	WebElement createAccountEmailTextBox;
	
	public void enterIntocreateAccountEmailTextBox(String email) {
		createAccountEmailTextBox.sendKeys(email);
		System.out.println("====Email entered for signup====="+email);
	}
	
	@FindBy(id = "SubmitCreate")
	WebElement createAccountButton;
	
	public void clickOncreateAccountButton() {
		createAccountButton.click();
	}
	
	/*
	 * Already Registered Fields
	 */
	@FindBy(id = "email")
	WebElement alreadyRegisteredEmailTextBox;
	
	public void enterIntoalreadyRegisteredEmailTextBox(String email) {
		alreadyRegisteredEmailTextBox.sendKeys(email);
	}
	
	@FindBy(id = "passwd")
	WebElement alreadyRegisteredPasswordTextBox;
	
	public void enterIntoalreadyRegisteredPasswordTextBox(String pwd) {
		alreadyRegisteredPasswordTextBox.sendKeys(pwd);
	}
	
	@FindBy(id = "SubmitLogin")
	WebElement alreadyRegisteredSignInButton;
	
	public void ClickalreadyRegisteredSignInButton() {
		alreadyRegisteredSignInButton.click();
		System.out.println("====Sign Up clicked===");
	}
}
