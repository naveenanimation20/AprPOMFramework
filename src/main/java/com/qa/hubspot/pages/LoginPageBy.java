package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.hubspot.util.DriverUtil;
import com.qa.hubspot.util.ElementActions;

import io.qameta.allure.Step;

public class LoginPageBy extends BasePage {
	ElementActions elementActions;
	DriverUtil driverUtil;
	WebDriver driver;

	//Non Page Factory Pattern:
	//By Locators:
	By emailID = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");

	public LoginPageBy(WebDriver driver){
		this.driver = driver;
		elementActions = new ElementActions(driver);
		driverUtil = new DriverUtil(driver);
	}
	
	//Page Actions:
	@Step("getting login page title step....")
	public String getLoginPageTitle(){
		return driverUtil.getPageTitle();
	}
	
	@Step("checking sign up link is displayed with is displayed method step...")
	public boolean verifySignUpLink(){
		return elementActions.elementIsDisplayed(signUpLink);
	}
	
	@Step("login to app with username: {0} and password: {1}")
	public HomePage doLogin(String username, String pwd){
		elementActions.doSendKeys(emailID, username);
		elementActions.doSendKeys(password, pwd);
		elementActions.doClick(loginButton);

		return new HomePage(driver);		
	}
	
}
