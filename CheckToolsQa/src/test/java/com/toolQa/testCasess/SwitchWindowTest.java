package com.toolQa.testCasess;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.toolsQa.baseClass.InvokeBrowser;
import com.toolsQa.pageClasses.Registration;
import com.toolsQa.pageClasses.SwitchWindow;
import com.toolsQa.reportLib.ExtentFactory;
import com.toolsQa.screenShotlib.ScreenShot;

public class SwitchWindowTest {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	Properties pro;
	String url;
	String browserName;
	SwitchWindow sw;
	@BeforeClass
	public void setUp() throws IOException{
		System.out.println("setting up the things");
		report=ExtentFactory.generateReport();
		test=report.startTest("filling up the registration");
		File f=new File("PropertyFiles");
		File fs=new File(f,"toolsqa.properties");
		FileInputStream fis=new FileInputStream(fs.getAbsolutePath());
		pro=new Properties();
		pro.load(fis);
		url=pro.getProperty("URLForSwitchWindow");
		System.out.println(url);
		browserName=pro.getProperty("browser");
		System.out.println(browserName);

	}
	@Parameters({"browser"})
	@BeforeMethod
	public void excuteBeforemethod(String browser) throws IOException{
		driver=InvokeBrowser.opneBrowser(browser);
		test.log(LogStatus.INFO, "browser opened");
		driver.get(url);
		//	driver.get("http://demoqa.com/registration/");
		test.log(LogStatus.INFO, "url is entered");
		driver.manage().window().maximize();
		sw=new SwitchWindow(driver, test);
	}

	@Test
	public void handelingBrowserWindow() throws InterruptedException {

		sw.handelBroserWindow();
		driver.close();
	}

	@Test
	public void handelMessageWindow(){
		sw.handelMessageWindow();
		driver.close();
	}

	@AfterMethod
	public void executeAfterMethod(ITestResult result) throws IOException, InterruptedException{
		if(result.getStatus()==result.SUCCESS){
			test.log(LogStatus.PASS, "testcase got pass");
		}else if(result.getStatus()==result.SKIP){
			test.log(LogStatus.SKIP, "testcase got skipped");
		}else if(result.getStatus()==result.FAILURE){
			String imagepath=ScreenShot.takeScreenShot(driver, result.getName());
			test.log(LogStatus.FAIL, "testcase got fail", test.addScreenCapture(imagepath));
		}
	}
	@AfterClass
	public void tearDown(){
		test.assignAuthor("suriya kishoore");
		report.endTest(test);
		report.flush();

	}
}
