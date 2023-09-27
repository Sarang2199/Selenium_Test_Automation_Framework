package mmt.ui.frameworksetup;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class ThreadPackage {
private ThreadPackage(){
		
	}
	private static ThreadPackage instance = new ThreadPackage();  // making this as a static because we want only one copy of instance not the multiple instances
	
	public static ThreadPackage getInstance() {
		return instance;
	}
	
	// factory design pattern  - define the separate factory method for creating objects and create the objects by calling method
	ThreadLocal<WebDriver>driver = new ThreadLocal<WebDriver>();
	public WebDriver getDriver() {
		return driver.get();
	}
	public void setDriver(WebDriver driverParameter) {
	driver.set(driverParameter);	
	}
	public void closeDriver() {
		driver.get().close();
		driver.remove();
	}
	
	//factory design pattern --> define separate factory methods for creating objects and create objects by calling that methods
		ThreadLocal<ExtentTest> extent = new ThreadLocal<ExtentTest>();
		
		public ExtentTest getExtent() {
			return extent.get();
		}
		
		public void setExtent(ExtentTest test) {
			extent.set(test);
		}
		
		public void removeExtentObject() {
			extent.remove();
		}
}
