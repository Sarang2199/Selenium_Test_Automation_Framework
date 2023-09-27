package mmt.ui.frameworksetup;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseSetup implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports report;
	public ExtentTest test;
	
	DriverSetup drsetup = new DriverSetup();
	
	@BeforeMethod
	public void setup(ITestContext context) {
		String url="https://www.makemytrip.com/";
		ThreadPackage.getInstance().setDriver(drsetup.getWebDriver("Chrome"));
		WebDriver driver = ThreadPackage.getInstance().getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
		driver.get(url);
		
	}


	@AfterMethod
	public void teardown(ITestResult result) {
		
		ThreadPackage.getInstance().closeDriver();
		
	}

	
	public ExtentReports  reportInitialization() {
		
		final String dateTimeStamp = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss").format(new Date());
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+File.separator+"DetailReports"+File.separator+"AutomationTestResult_"+dateTimeStamp+".html");
		report = new ExtentReports();
		report.attachReporter(sparkReporter);
		sparkReporter.config().setTheme(Theme.DARK);
		report.setSystemInfo("Executed on Environment", "www.makemytrip.com");
		report.setSystemInfo("Executed on Browser", "chrome");
		report.setSystemInfo("Executed on OS", System.getProperty("os.name"));
		report.setSystemInfo("Executed by User", System.getProperty("user.name"));
		sparkReporter.config().setDocumentTitle("Automation Test Result");
		sparkReporter.config().setReportName("");
		
		return report;

	}
	
	
	
	   
public void onTestStart(ITestResult result) {
	//before each test case
	test = report.createTest(result.getMethod().getMethodName());
	ThreadPackage.getInstance().setExtent(test);
}

public void onTestSuccess(ITestResult result) {
	ThreadPackage.getInstance().getExtent().log(Status.PASS, "Test Case: "+result.getMethod().getMethodName()+ " is Passed.");
	ThreadPackage.getInstance().removeExtentObject();
}

public void onTestFailure(ITestResult result) {
	ThreadPackage.getInstance().getExtent().log(Status.FAIL, "Test Case: "+result.getMethod().getMethodName()+ " is Failed.");
	ThreadPackage.getInstance().getExtent().log(Status.FAIL, result.getThrowable());
	
	//add screenshot for failed test.
	File src = ((TakesScreenshot)ThreadPackage.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
	Date date = new Date();
	String actualDate = format.format(date);
	
	String screenshotPath = System.getProperty("user.dir")+
			"/Reports/Screenshots/"+actualDate+".jpeg";
	File dest = new File(screenshotPath);
	
	try {
		FileUtils.copyFile(src, dest);
	} catch (IOException e) {
		e.printStackTrace();
	}
	ThreadPackage.getInstance().getExtent().addScreenCaptureFromPath(screenshotPath, "Test case failure screenshot");
	ThreadPackage.getInstance().removeExtentObject();
	
}
public void onTestSkipped(ITestResult result) {
	ThreadPackage.getInstance().getExtent().log(Status.SKIP, "Test Case: "+result.getMethod().getMethodName()+ " is skipped.");
	ThreadPackage.getInstance().removeExtentObject();
}

public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
}

public void onTestFailedWithTimeout(ITestResult result) {
}

public void onStart(ITestContext context) {
	try {
		 report = reportInitialization();
	} catch (Exception e) {
		System.out.println("Report not generating");
		e.printStackTrace();
	}
}

public void onFinish(ITestContext context) {
	//close extent
	report.flush();
}
}
