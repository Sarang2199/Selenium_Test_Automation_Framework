package mmt.ui.tests;

import org.openqa.selenium.WebDriver;

import mmt.ui.frameworksetup.BaseSetup;
import mmt.ui.frameworksetup.ThreadPackage;
import mmt.ui.pages.HomePage;
import org.testng.annotations.*;

public class MakeMyTripTest  extends BaseSetup {
	
	@Test
	public void flightSearchTest() throws InterruptedException {
		WebDriver driver = ThreadPackage.getInstance().getDriver();
		HomePage hmpage = new HomePage(driver);
		hmpage.verifyCloseBtn();
		//hmpage.verifyLogin();
		hmpage.verifyTabSelected();
	}
}