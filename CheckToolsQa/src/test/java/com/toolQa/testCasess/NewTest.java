package com.toolQa.testCasess;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.toolsQa.baseClass.InvokeBrowser;
import com.toolsQa.screenShotlib.ScreenShot;

public class NewTest {
	WebDriver driver;
  @Test
  public void f() throws IOException {
	  driver=InvokeBrowser.opneBrowser("chrome");
	  driver.get("http://toolsqa.com/");
	  driver.manage().window().maximize();
	  ScreenShot.takeScreenShot(driver, "check");
  }
}
