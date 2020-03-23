package com.cross.qa.testcases;

import java.lang.reflect.Method;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cross.qa.base.TestBase;
import com.cross.qa.pages.AuthenticationPage;
import com.cross.qa.pages.CategoryCollectionPage;
import com.cross.qa.pages.HomePage;
import com.cross.qa.pages.LoginPage;
import com.cross.qa.pages.MyAccountPage;
import com.cross.qa.pages.SignUpPage;
import com.cross.qa.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class AssignmentTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	AuthenticationPage authenticationPage;
	SignUpPage signupPage ;
	MyAccountPage myAccountPage;
	CategoryCollectionPage collectionPage;
	TestUtil testUtil;

	public AssignmentTest() {
		super();
	}

	
	@BeforeMethod
	public void setUp(Method method) {
		logger = extent.startTest(method.getName());
		logger.assignCategory("OrderJourney");
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		authenticationPage = new AuthenticationPage();
		signupPage = new SignUpPage();
		myAccountPage = new MyAccountPage();
		collectionPage = new CategoryCollectionPage();
		homePage = new HomePage();
		
	}
	
	
	@Test
	public void verifyEndToEndOrderjourney() {
		try {
			assertTrue(true);
			homePage.clickOnSignInLink();
			String email = RandomStringUtils.randomAlphabetic(3)+"@"+RandomStringUtils.randomAlphabetic(2)+".com";
			tempDataMap.put("email", email);
			authenticationPage.enterIntocreateAccountEmailTextBox(email);
			authenticationPage.clickOncreateAccountButton();
			signupPage.createAccount();		
			homePage.clickOnSignOutLink();
			homePage.clickOnSignInLink();
			loginPage.login(email, tempDataMap.get("password"));
			myAccountPage.mouseHoverOnwomenTab();
			myAccountPage.clickOnsummerDressesLink();
			collectionPage.mouseHoverOnfirstProduct();
			collectionPage.clickOnquickViewLink();
			explicitWait = new WebDriverWait(driver,10);
			explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(collectionPage.quickViewFrame));
			driver.switchTo().frame(collectionPage.quickViewFrame);
			collectionPage.showShortDescription();
			collectionPage.saveUnitPrice();
			collectionPage.enterQuantityIntoquantityTextBox("2");
			collectionPage.clickOnaddToCartButton();
			collectionPage.verifyTotalPriceInCartSummary(2);
			collectionPage.clickOnorderSummaryProceedToCheckoutButton();
			collectionPage.clickOnCartSummartProceedToCheckoutButton();
			collectionPage.clickOnaddressConfirmationProceedToCheckoutButton();
			collectionPage.clickOntermsAndConditionsCheckBox();
			collectionPage.clickOncarrierConfirmationProceedToCheckoutButton();
			collectionPage.verifyTotalPriceInCheckoutSummary();
			collectionPage.clickOnpayByChequeLink();
			collectionPage.clickOnconfirmMyOrderButton();
			collectionPage.verifyOrderSuccess();
			myAccountPage.clickOnUserNameLink();
			myAccountPage.clickOnorderHistoryLink();
			myAccountPage.verifyLatestOrderPrice();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	@AfterMethod
	public void tearDown(){
		extent.endTest(logger);
		extent.flush();
		driver.quit();
		tempDataMap.clear();
	}
	
	

}
