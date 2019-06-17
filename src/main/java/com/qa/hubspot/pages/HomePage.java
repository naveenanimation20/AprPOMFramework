package com.qa.hubspot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.TimeUtil;

public class HomePage extends BasePage{
	WebDriver driver;
	
	@FindBy(id="nav-primary-contacts-branch")
	WebElement contactMainLink;
	
	@FindBy(id="nav-secondary-contacts")
	WebElement contactSubLink;
	
	@FindBy(xpath = "//h1[@class='private-page__title']")
	WebElement homePageHeader;
	
	@FindBy(xpath = "//span[@class='account-name']")
	WebElement accountName;
	
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public String getHomePageTitle(){
		WebDriverWait wait = new WebDriverWait(driver,Constants.DEFAULT_EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.titleContains(Constants.HOME_PAGE_TITLE));
		return driver.getTitle();
	}
	
	public String getHomePageHeaderValue(){
		WebDriverWait wait = new WebDriverWait(driver,Constants.DEFAULT_EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(homePageHeader));
		return homePageHeader.getText();
	}
	
	public String getLoggedInAccountValue(){
		WebDriverWait wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.elementToBeClickable(accountName));
		return accountName.getText();
	}
	
	public ContactsPage goToContactsPage(){
		clickOnContacts();
		return new ContactsPage(driver);
	}
	
	public void clickOnContacts(){
		contactMainLink.click();
		TimeUtil.shortWait();
		contactSubLink.click();
	}
	

}
