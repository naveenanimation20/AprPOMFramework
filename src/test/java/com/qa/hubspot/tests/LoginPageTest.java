package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.pages.BasePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.TimeUtil;

public class LoginPageTest extends BaseTest{
	
	//WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	
	@BeforeMethod
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		TimeUtil.mediumWait();
	}
	
	@Test(priority=1)
	public void verifyLoginPageTitleTest(){
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page title is: "+ title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifySignUpLinkTest(){
		Assert.assertTrue(loginPage.verifySignUpLink());
	}
	
	@Test(priority=3)
	public void hubSpotLoginTest(){
		loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
		
	
//	@AfterMethod
//	public void tearDown(){
//		driver.quit();
//	}
//	
	

}
