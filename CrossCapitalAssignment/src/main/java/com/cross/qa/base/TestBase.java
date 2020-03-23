package com.cross.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.asserts.IAssertLifecycle;

import com.cross.qa.util.TestUtil;
import com.cross.qa.util.WebEventListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase extends Assertion{
	
	public static WebDriver driver;
	public static Properties prop;
	public static HashMap<String,String> tempDataMap;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static WebDriverWait explicitWait;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	@BeforeSuite
	public void setUpSuite(){
		TestUtil.readConfig();
		extent = new ExtentReports("Reports\\MyReport.html");
	}
	
	
	public static void initialization(){
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			   options.addArguments("start-maximized");			
			driver = new ChromeDriver(options);
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");	
			driver = new FirefoxDriver(); 
		}
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(prop.getProperty("PAGE_LOAD_TIMEOUT")), TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Long.parseLong(prop.getProperty("IMPLICIT_WAIT")), TimeUnit.SECONDS);	
		driver.get(prop.getProperty("url"));
		logger.log(LogStatus.INFO, "App launched successfully",logger.addBase64ScreenShot(TestUtil.takeScreenshot()));
		tempDataMap = new HashMap<String, String>();
	}
	
	
	@Override
	public void onBeforeAssert(IAssert a) {
	System.out.println("-----------------------onBeforeAssert-----------------------------------");
	}

	@Override
	public void onAfterAssert(IAssert a) {
		System.out.println("-----------------------onAfterAssert-----------------------------------");
	}

	@Override
	public void onAssertSuccess(IAssert<?> assertCommand) {
		System.out.println("-----------------------onAssertSuccess-----------------------------------");
		logger.log(LogStatus.PASS,"Validation passed as Actual met with Expected value: "+assertCommand.getActual(),logger.addBase64ScreenShot(TestUtil.takeScreenshot()));
	}

	@Override
	public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
		System.out.println("-----------------------onAssertFailure-----------------------------------");
		logger.log(LogStatus.FAIL,"Validation failed because :- "+assertCommand.getMessage());
		logger.log(LogStatus.FAIL,"Validation failed as Actual is:"+assertCommand.getActual()+" \t while Expected was: "+assertCommand.getExpected(),logger.addBase64ScreenShot(TestUtil.takeScreenshot()));

	}

	@Override
	public void executeAssert(IAssert<?> assertCommand) {
		System.out.println("----On executeAssert----");	
		    try {
		    	assertCommand.doAssert();
		    } catch (AssertionError ex) {
		        throw ex;
		    }
	}

	
	
	
	
	
	
	

}
