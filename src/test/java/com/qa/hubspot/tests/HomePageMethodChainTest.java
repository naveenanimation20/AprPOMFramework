package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.pages.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.HomePageMethodChain;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.pages.LoginPageMethodChain;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.TimeUtil;

public class HomePageMethodChainTest {
	
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPageMethodChain loginPage;
	HomePageMethodChain homePage;
	
	@BeforeMethod
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPageMethodChain(driver);
		TimeUtil.mediumWait();
	}
	
	@Test(priority=1)
	public void verifyHomePageTitle(){
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"))
				.getHomePageTitle();
	}
	
	@Test(priority=2)
	public void verifyHomePageHeaderTest(){
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"))
				.getHomePageHeaderValue();

	}
	
	@Test(priority=3)
	public void verifyLoggedInUserAccountNameTest(){
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"))
				.getLoggedInAccountValue();
	}
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
	
	
	
	
	

}
