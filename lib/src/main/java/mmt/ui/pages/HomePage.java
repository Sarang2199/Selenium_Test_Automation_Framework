package mmt.ui.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import mmt.ui.frameworksetup.BaseSetup;
import mmt.ui.frameworksetup.ThreadPackage;
import mmt.ui.utility.BaseLocators;
import mmt.ui.utility.BasePage;
import mmt.ui.utility.Utility;

public class HomePage extends BaseSetup {
	  WebDriver driver=null;
		public HomePage(WebDriver driver) {
			PageFactory.initElements(driver, this);
			this.driver = driver;
			
		}
		Utility utils = new Utility();
		 
		JavascriptExecutor js = (JavascriptExecutor)ThreadPackage.getInstance().getDriver();
	    BasePage bspage = new BasePage();
		public void verifyCloseBtn() throws InterruptedException {
			utils.info("Close the advertisement if visible");
			try {
			try {	
			bspage.verifyExists(BasePage.getLocator(BaseLocators.classContians,"close"));}
			//driver.switchTo().frame(bspage.getWebElementLocator(BaseLocators.frameName));}
			catch(Exception e) {
			boolean adv = bspage.getWebElementLocator(BaseLocators.classContians,"close").isDisplayed();
		
			
			if(adv) {
				utils.info("Close button visible");
				js.executeScript("arguments[0].click();",bspage.getWebElementLocator(BaseLocators.classContians,"close"));	
			}
			else {
				utils.info("Advertisement is not visible");
			}}}
			catch(Exception e){
				System.out.println("Exception is --- "+e);
			}
			finally {boolean adv = bspage.getWebElementLocator(BaseLocators.classContians,"close").isDisplayed();
		    verifyLogin();
			
			if(adv) {
				js.executeScript("arguments[0].click();",bspage.getWebElementLocator(BaseLocators.classContians,"close"));}
				/*
				 * boolean adv =
				 * bspage.getWebElementLocator(BaseLocators.clickanywhere).isDisplayed();
				 * if(adv) {js.executeScript("arguments[0].click();",
				 * bspage.getWebElementLocator(BaseLocators.clickanywhere));} else {
				 * System.out.println("Closed all popups"); }
				 */
			}
		}
		
		public void verifyLogin() {
			contiuneBtndisabled();
			bspage.getWebElementLocator(BaseLocators.inputPlaceholder,"Enter email or mobile number").isDisplayed();
			bspage.getWebElementLocator(BaseLocators.inputid,"username").click();
			bspage.getWebElementLocator(BaseLocators.inputid,"username").sendKeys("ssarangghayal@gmail.com");
			contiuneBtndisabled();
			
		}
		public void contiuneBtndisabled() {
			if(!bspage.getWebElementLocator(BaseLocators.continuebtn).isEnabled()) {
				utils.info("Continue btn is disabled");
			}
			else {
				utils.info("Continue btn is enabled");
			}
		}
		
	   public void verifyTabSelected() {
		   utils.info("Verify Flights tab is already selected");
		   if(bspage.getWebElementLocator(BaseLocators.tabSelected,"Flights").isDisplayed()) {
			   bspage.getWebElementLocator(BaseLocators.radiobutton,"oneWayTrip","selected").isDisplayed();
			   bspage.getWebElementLocator(BaseLocators.radiobutton,"roundTrip","").isDisplayed();
			   bspage.getWebElementLocator(BaseLocators.radiobutton,"mulitiCityTrip","").isDisplayed();


		   }
	   }
	}
