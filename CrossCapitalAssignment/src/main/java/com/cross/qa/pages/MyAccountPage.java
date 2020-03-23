package com.cross.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.cross.qa.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class MyAccountPage extends TestBase{
	 
	// Initializing the Page Objects: 
	  public MyAccountPage() {
	  PageFactory.initElements(driver, this); 
	  }
	  
	@FindBy(xpath = "//a[text()='Women']")
	WebElement womenTab;
	
	public void mouseHoverOnwomenTab() {
		Actions action = new Actions(driver);
		action.moveToElement(womenTab).perform();
	}
	
	@FindBy(xpath = "//a[@title='Summer Dresses']")
	WebElement summerDressesLink;
	
	public void clickOnsummerDressesLink() {
		summerDressesLink.click();
	}
	
	@FindBy(className = "account")
	WebElement userNameLink;
	
	public void clickOnUserNameLink() {
		userNameLink.click();
	}
	
	
	@FindBy(xpath = "//span[text()='Order history and details']//parent::a[@title='Orders']")
	WebElement orderHistoryLink;
	
	public void clickOnorderHistoryLink() {
		orderHistoryLink.click();
	}
	
	@FindBy(xpath = "//td[@class='history_price']")
	WebElement latestOrderPriceElement;
	
	public void verifyLatestOrderPrice() {
		String latestOrderPriceText = latestOrderPriceElement.getText().substring(1);
		float latestOrderPrice = Float.parseFloat(latestOrderPriceText);
		assertEquals(latestOrderPrice, Float.parseFloat(tempDataMap.get("totalAmountToBePaid")),"Total Amount paid On Order summary page was expected as : "+tempDataMap.get("totalAmountToBePaid")+" while found as: "+latestOrderPrice);
		logger.log(LogStatus.PASS, "Total Amount paid on Order History page is as expected: "+latestOrderPrice);
	}
	
	
}
