package com.qa.hubspot.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	WebDriver driver;
	
	@BeforeTest
	public void beforeTest(){
		System.out.println("in before test");
	}
	
	@BeforeSuite
	public void beforeSuite(){
		System.out.println("in before suite");
	}
	
	
	@AfterMethod
	public void tearDown(){
		if(!(driver==null)){
			driver.quit();
		}
	}
	
	
	

}
