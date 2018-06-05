package com.toolQa.testCasess;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.toolsQa.baseClass.InvokeBrowser;
import com.toolsQa.commonUtils.WaitForWebPage;
import com.toolsQa.screenShotlib.ScreenShot;

public class NewTest {
	WebDriver driver;
  @Test
  public void f() throws IOException {
	  driver=InvokeBrowser.opneBrowser("chrome");
	  driver.get("https://www.phptravels.net/");
	  driver.manage().window().maximize();
	  driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click();
	  WaitForWebPage.waitFortheElementToBeVisible(driver, driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")), 10);
	 driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("hello");
  }
}
