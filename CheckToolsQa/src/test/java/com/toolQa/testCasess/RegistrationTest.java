package com.toolQa.testCasess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.toolsQa.baseClass.InvokeBrowser;
import com.toolsQa.pageClasses.Registration;
import com.toolsQa.reportLib.ExtentFactory;
import com.toolsQa.screenShotlib.ScreenShot;

public class RegistrationTest {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	Properties pro;
	String url;
	String browserName;
	 Registration rg;
	 
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
		    url=pro.getProperty("URL");
		    System.out.println(url);
//		 browserName=pro.getProperty("browser");
//		 System.out.println(browserName);
	}
	@Parameters({"browser"})
	@BeforeMethod
	public void excuteBeformethod(String browser) throws IOException{
		driver=InvokeBrowser.opneBrowser(browser);
		test.log(LogStatus.INFO, "browser opened");
	    driver.get(url);
	//	driver.get("http://demoqa.com/registration/");
	  test.log(LogStatus.INFO, "url is entered");
	  driver.manage().window().maximize();
	  rg=new Registration(driver, test);
	}
	
  @Test(dataProvider="getdata")
  public void f(String fn,String ln,String country,String month,String day,
			String year,String phone,String email ) throws InterruptedException {
	  if(fn==null||ln==null||country==null||month==null||day==null||year==null||phone==null||email==null){
		  System.out.println("getting the null value");
		  driver.close();
	  }else{
		  rg.fillUpForm(fn, ln, country, month, day, year, phone, email);
		  driver.close();
	  }
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
  
  @DataProvider
  public Object[][] getdata() throws EncryptedDocumentException, InvalidFormatException, IOException{
	  DataFormatter formatter=new DataFormatter();
	  File f=new File("TestData");
	  File fs=new File(f,"dataSheet.xlsx");
	  FileInputStream fis=new FileInputStream(fs.getAbsolutePath());
	  Workbook wb=WorkbookFactory.create(fis);
	  Sheet sh=wb.getSheet("Details");
	  int rwno=sh.getLastRowNum();
	  System.out.println(rwno);
	  Object [][] obj =new Object[7][8];
	  for(int i=1;i<7;i++){
		  Row rw=sh.getRow(i);
		  if(rw==null){
			  
		  }else{
			  for(int j=0;j<8;j++){
				 String data=formatter.formatCellValue(rw.getCell(j));
					 obj[i][j]=data;
					 
				
			  }
		  }
	  }
	return obj;
	
	  
  }
}
