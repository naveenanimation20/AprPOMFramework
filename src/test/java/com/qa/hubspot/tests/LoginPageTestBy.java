package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.hubspot.listeners.TestAllureListener;
import com.qa.hubspot.pages.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPageBy;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.TimeUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners({TestAllureListener.class})
public class LoginPageTestBy {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPageBy loginPageBy;
	HomePage homePage;

	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		driver.get(prop.getProperty("url"));
		TimeUtil.mediumWait();
		loginPageBy = new LoginPageBy(driver);
	}

	@Test(priority = 1, description="verifying login page title test case")
	@Severity(SeverityLevel.NORMAL)
	@Description("Check login page title is correct or not")
	public void verifyLoginPageTitleTest() {
		String title = loginPageBy.getLoginPageTitle();
		System.out.println("Login page title is: "+ title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	
	@Test(priority=2)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Check sign up link on login page")
	public void verifySignUpLinkTest(){
		Assert.assertTrue(loginPageBy.verifySignUpLink());
	}
	
	@Test(priority=3)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify login feature with correct credentials")
	public void hubSpotLoginTest(){
		homePage = loginPageBy.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homePage.getHomePageTitle(), Constants.HOME_PAGE_TITLE);
		Assert.assertEquals(homePage.getHomePageHeaderValue(), Constants.HOME_PAGE_HEADER);
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
