package com.cross.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cross.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	//Page Factory - OR:
	@FindBy(id="email")
	WebElement loginUsername;
	
	@FindBy(id="passwd")
	WebElement loginPassword;
	
	@FindBy(id="SubmitLogin")
	WebElement signInButton;
	
	@FindBy(xpath="//button[contains(text(),'Sign Up')]")
	WebElement signUpBtn;
	
	
	//Initializing the Page Objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
			
	public void login(String un, String pwd){
		loginUsername.sendKeys(un);
		loginPassword.sendKeys(pwd);
		    	JavascriptExecutor js = (JavascriptExecutor)driver;
		    	js.executeScript("arguments[0].click();", signInButton);
		    
	}
	
}
