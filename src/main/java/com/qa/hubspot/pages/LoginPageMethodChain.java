package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.util.DriverUtil;
import com.qa.hubspot.util.ElementActions;

public class LoginPageMethodChain extends BasePage{
	
	ElementActions elementActions;
	DriverUtil driverUtil;
	WebDriver driver;

	//Non Page Factory Pattern:
	//By Locators:
	By emailID = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");

	public LoginPageMethodChain(WebDriver driver){
		this.driver = driver;
		elementActions = new ElementActions(driver);
		driverUtil = new DriverUtil(driver);
	}
	
	//Page Actions:
	public String getLoginPageTitle(){
		return driverUtil.getPageTitle();
	}
	
	public boolean verifySignUpLink(){
		return elementActions.elementIsDisplayed(signUpLink);
	}
	
	public HomePageMethodChain doLogin(String username, String pwd){
		elementActions.doSendKeys(emailID, username);
		elementActions.doSendKeys(password, pwd);
		elementActions.doClick(loginButton);

		return new HomePageMethodChain(driver);		
	}
	
	
	
	
	

}
