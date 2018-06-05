package com.toolQa.testCasess;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ClickOnFilpkart {
	String url1="https://www.flipkart.com/gaming/games/ps4-games/pr?otracker=categorytree&page=";
	String url2="&sid=4rr%2Cfa6%2C27p&viewType=grid";
	WebDriver driver;
	String URL="https://www.flipkart.com/gaming/games/ps4-games/pr?"
			+ "otracker=categorytree&page=1&sid=4rr%2Cfa6%2C27p&viewType=grid";
	int count=1;
	boolean flag=true;
	@Test
	public void clcikOnLink() throws InterruptedException {
		driver=new FirefoxDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		WebElement next=driver.findElement(By.xpath("//a[contains(@href,'viewType=grid')]//span[text()='Next']"));
//		int xcord=next.getLocation().getX();
//		int ycord=next.getLocation().getY();
//		Actions act=new Actions(driver);
//		JavascriptExecutor je = (JavascriptExecutor) driver;
//		je.executeScript("arguments[0].scrollIntoView(true);",next);
//		je.executeScript("window.scrollBy(0,-250)", "");
//		act.moveToElement(next, xcord, ycord).click().perform();
//		System.out.println("clciked on the next");
		WebElement parentElementt=driver.findElement(By.xpath("//div[@class='_2SxMvQ']"));
		////	  while(count>=0){
		////	  try{
		//////		  WebDriverWait wait=new WebDriverWait(driver, 10);
		//////		  wait.until(ExpectedConditions.visibilityOf( parentElementt.findElement(By.xpath("Dishonored 2"))));
		////		  JavascriptExecutor je = (JavascriptExecutor) driver;
		////		 WebElement element=parentElementt.findElement(By.xpath("Dishonored 2"));
		////		je.executeScript("arguments[0].scrollIntoView(true);",element);
		////		  int xcord=element.getLocation().getX();
		////		  int ycord=element.getLocation().getY();
		////		  Actions act=new Actions(driver);
		////		  act.moveToElement(element, xcord, ycord).click();
		////	     element.click();
		////	    break;
		////	  }catch(Throwable t){
		////		 
		////		  WebElement next=driver.findElement(By.xpath("//span[text()='Next']"));
		////		  int xcord=next.getLocation().getX();
		////		  int ycord=next.getLocation().getY();
		////		  Actions act=new Actions(driver);
		////		  act.moveToElement(next, xcord, ycord).click();
		////		  Thread.sleep(300);
		////		  count++;
		////	   }
		////	  }
		//	  
			  while(true){
				  List<WebElement> links=parentElementt.findElements(By.tagName("a"));
				  try{
					  for(int i=0;i<links.size();i++){
						  String textName=links.get(i).getText();
						  System.out.println(textName);
						  if(textName.contains("Dishonored 2")){
							 String actualtext= links.get(i).getText();
							 System.out.println(actualtext);
							 WebElement actuaElement=links.get(i);
							  int xcord=actuaElement.getLocation().getX();
							  int ycord=actuaElement.getLocation().getY();
							  Actions act=new Actions(driver);
							  act.moveToElement(actuaElement, xcord, ycord).click();
							 links.get(i).click();
							 break;
							 
						  }
						  if(i==links.size()-1){
							  count++;
							  System.out.println("total count "+count);
								 throw new NoSuchElementException("elemnt is not present") ;
								 
							 }
					  }  
					  
					 
				  }
				  catch(Throwable t){
		//				  WebElement next=driver.findElement(By.xpath(".//*[@id='container']/div/div[1]/div/div[2]/div/div[2]/div/div[3]/div[2]/div[2]/a/span"));
		//				  int xcord=next.getLocation().getX();
		//				  int ycord=next.getLocation().getY();
		//				  Actions act=new Actions(driver);
		//				  JavascriptExecutor je = (JavascriptExecutor) driver;
		//					je.executeScript("arguments[0].scrollIntoView(true);",next);
		//				  act.moveToElement(next, xcord, ycord).click();
					  driver.navigate().to(url1+count+url2);
						  System.out.println("naviagated to the "+count+"page");
						  Thread.sleep(3000);
						  retrying(parentElementt);
					   }
				 
			  }
			 
			
			  
	}
	public boolean retrying(WebElement element) {
	    boolean result = false;
	    int attempts = 0;
	    while(attempts < 2) {
	        try {
	            element.isDisplayed();
	            result = true;
	            break;
	        } catch(StaleElementReferenceException e) {
	        }
	        attempts++;
	    }
	    return result;
	}
}

