package mmt.ui.utility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.*;

import mmt.ui.frameworksetup.BaseSetup;
import mmt.ui.frameworksetup.ThreadPackage;

public class BasePage extends BaseSetup{
	
	 WebDriver driver = ThreadPackage.getInstance().getDriver();
	 Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
	 Utility utils = new Utility();
	 protected boolean elementExist(By locator) {
		 WebElement element=null;
		 try {
			 wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			 if(element != null) {
				 return true;
			 }
			 else {
				 
			 }
		 }catch(Exception e) {
			 
		 }
		 return false;
	 }
	 public void verifyExists(By locator) {
		 try {
			 if(elementExist(locator)) {
				 System.out.println("WebElement exists");
				 utils.testpass(locator.toString()+" locator is visible");
			 }
		 }catch(Exception e) {
			 System.out.println(e);
			 System.out.println("WebElement not exists");
		 }
	 }

	public synchronized WebElement getWebElementLocator(String locator, String value) {
			
			return driver.findElement(By.xpath(String.format(locator, value)));
		}
	public synchronized WebElement getWebElementLocator(By locator) {
			
			return driver.findElement(locator);
		}
		
	public synchronized WebElement getWebElementLocator(String locator) {
		
		return driver.findElement(By.xpath(locator));
	}
		
	public synchronized WebElement getWebElementLocator(String locator, String value1, String value2) {
		
		return driver.findElement(By.xpath(String.format(locator, value1,value2)));
	}
}

