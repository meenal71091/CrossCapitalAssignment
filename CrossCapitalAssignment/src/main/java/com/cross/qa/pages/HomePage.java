package com.cross.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cross.qa.base.TestBase;

public class HomePage extends TestBase {

	
	     
	  
	  // Initializing the Page Objects: 
	  public HomePage() {
	  PageFactory.initElements(driver, this); }
	  
	    
	@FindBy(className = "login")
	public WebElement signInLink;
	
	public void clickOnSignInLink() {
		signInLink.click();
	}
	
	@FindBy(className = "logout")
	WebElement signOutLink;
	
	public void clickOnSignOutLink() {
		signOutLink.click();
	}
	
	

}
