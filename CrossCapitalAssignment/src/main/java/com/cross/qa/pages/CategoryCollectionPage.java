package com.cross.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cross.qa.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class CategoryCollectionPage extends TestBase{
	 
	// Initializing the Page Objects: 
	  public CategoryCollectionPage() {
	  PageFactory.initElements(driver, this); 
	  }
	  
	@FindBy(xpath = "(//div[@class='product-container'])[position()='1']")
	WebElement firstProduct;
	
	public void mouseHoverOnfirstProduct() {
		Actions action = new Actions(driver);
		action.moveToElement(firstProduct).perform();
	}
	
	@FindBy(className = "quick-view")
	WebElement quickViewLink;
	
	public void clickOnquickViewLink() {
		quickViewLink.click();
	}
	
	@FindBy(xpath  = "//input[@id='quantity_wanted']")
	WebElement quantityTextBox;
	
	public void enterQuantityIntoquantityTextBox(String quantity) {		
		explicitWait = new WebDriverWait(driver,10);
		explicitWait.until(ExpectedConditions.visibilityOf(quantityTextBox));
		quantityTextBox.clear();
		quantityTextBox.sendKeys(quantity);
	}
	
	@FindBy(name = "Submit")
	WebElement addToCartButton;
	
	public void clickOnaddToCartButton() {
		addToCartButton.click();
	}
	
	@FindBy(id = "our_price_display")
	WebElement unitPriceElement;
	
	@FindBy(xpath   = "//span[@class='ajax_cart_shipping_cost']")
	WebElement shippingPriceElement;
	
	@FindBy(id = "total_tax")
	WebElement taxPriceElement;
	
	@FindBy(xpath = "//span[@class='ajax_block_cart_total']")
	WebElement totalPriceElementOnCartSummaryPage;
	
	@FindBy(id = "total_price")
	WebElement totalPriceElementOnPaymentPage;
	
	public void showShortDescription() {
		try {
			Thread.sleep(2000);
		System.out.println("===Item Description:"+driver.findElement(By.xpath("//div[@id='short_description_content']/p")).getText());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FindBy(xpath = "//a[@title='Proceed to checkout']")
	WebElement quickViewProceedToCheckoutButton;
	
	public void clickOnquickViewProceedToCheckoutButton() {
		quickViewProceedToCheckoutButton.click();
	}
	
	@FindBy(xpath = "//iframe[contains(@id,'fancybox-frame')]")
	public WebElement quickViewFrame;
	
	
	public void verifyTotalPriceInCartSummary(int quantity) {
		try {

		float actual = Float.parseFloat(totalPriceElementOnCartSummaryPage.getText().substring(1));
		float ItemPrice = (Float.parseFloat(tempDataMap.get("unitPrice"))*quantity);
		tempDataMap.put("ItemGrossValue", String.valueOf(ItemPrice));
		float shippingPrice = Float.parseFloat(shippingPriceElement.getText().substring(1));

				float totalPrice = ItemPrice + shippingPrice ;
				tempDataMap.put("shippingPrice",String.valueOf(shippingPrice));

				tempDataMap.put("totalPriceWithoutTax",String.valueOf(totalPrice));
		assertEquals(actual	,totalPrice,"Expected Price on cart Summary page was:"+totalPrice+" but found: "+actual );
		logger.log(LogStatus.PASS, "Total Price is "+totalPrice+ " as expected");

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void verifyTotalPriceInCheckoutSummary() {
		try {
		float taxPrice = Float.parseFloat(taxPriceElement.getText().substring(1));
		float totalPriceWithoutTax = Float.parseFloat(tempDataMap.get("totalPriceWithoutTax"));
				float totalPrice =  totalPriceWithoutTax + taxPrice ;
				tempDataMap.put("totalAmountToBePaid",String.valueOf(totalPrice));
				String actual = totalPriceElementOnPaymentPage.getText().substring(1);
		assertEquals(actual	,String.valueOf(totalPrice),"On checkout summary page, Expected Total price was: "+tempDataMap.get("totalAmountToBePaid")+" while found: "+actual);
		logger.log(LogStatus.PASS, "Total Price on Checkout Sumaary Page is "+totalPrice+ " as expected");
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FindBy(xpath = "(//a[@title='Proceed to checkout'])[last()]")
	WebElement orderSummaryProceedToCheckoutButton;
	
	public void clickOnorderSummaryProceedToCheckoutButton() {
		orderSummaryProceedToCheckoutButton.click();
	}
	
	@FindBy(name = "processAddress")
	WebElement addressConfirmationProceedToCheckoutButton;
	
	
	public void clickOnaddressConfirmationProceedToCheckoutButton() {
		addressConfirmationProceedToCheckoutButton.click();
	}
	
	
	
	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement termsAndConditionsCheckBox;
	
	
	public void clickOntermsAndConditionsCheckBox() {
		termsAndConditionsCheckBox.click();
	}
	
	@FindBy(name = "processCarrier")
	WebElement carrierConfirmationProceedToCheckoutButton;
	
	
	public void clickOncarrierConfirmationProceedToCheckoutButton() {
		carrierConfirmationProceedToCheckoutButton.click();
	}
	
	@FindBy(className = "cheque")
	WebElement payByChequeLink;
	
	
	public void clickOnpayByChequeLink() {
		payByChequeLink.click();
	}
	
	@FindBy(xpath = "//span[text()='I confirm my order']//parent::button[@type='submit']")
	WebElement confirmMyOrderButton;
	
	
	public void clickOnconfirmMyOrderButton() {
		confirmMyOrderButton.click();
	}
	
	@FindBy(xpath = "//p[@class='alert alert-success']")
	WebElement orderSucessMessage;

	public void saveUnitPrice() {
		String unitPrice = unitPriceElement.getText();	
		unitPrice = unitPrice.substring(1);
		tempDataMap.put("unitPrice",unitPrice);
	}
	
	@FindBy(xpath = "//h1[contains(text(),'Shopping-cart summary')]//following::a[@title='Proceed to checkout']")
	WebElement CartSummartProceedToCheckoutButton;
	
	public void clickOnCartSummartProceedToCheckoutButton() {
		CartSummartProceedToCheckoutButton.click();		
	}
	
	@FindBy(xpath  = "//p[contains(@class,'alert alert-success')]")
	WebElement orderCompletionSuccessMessage;
	
	public void verifyOrderSuccess() {
		try {
			Thread.sleep(2000);
		assertTrue(orderCompletionSuccessMessage.isDisplayed(),"Order Success Message not being displayed");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
