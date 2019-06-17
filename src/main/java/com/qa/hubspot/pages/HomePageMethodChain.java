package com.qa.hubspot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.TimeUtil;

public class HomePageMethodChain extends BasePage{
	
WebDriver driver;
	
	@FindBy(id="nav-primary-contacts-branch")
	WebElement contactMainLink;
	
	@FindBy(id="nav-secondary-contacts")
	WebElement contactSubLink;
	
	@FindBy(xpath = "//h1[@class='private-page__title']")
	WebElement homePageHeader;
	
	@FindBy(xpath = "//span[@class='account-name']")
	WebElement accountName;
	
	
	public HomePageMethodChain(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public HomePageMethodChain getHomePageTitle(){
		WebDriverWait wait = new WebDriverWait(driver,Constants.DEFAULT_EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.titleContains(Constants.HOME_PAGE_TITLE));
		Assert.assertEquals(driver.getTitle(), Constants.HOME_PAGE_TITLE);
		return this;
	}
	
	public HomePageMethodChain getHomePageHeaderValue(){
		WebDriverWait wait = new WebDriverWait(driver,Constants.DEFAULT_EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(homePageHeader));
		Assert.assertEquals(homePageHeader.getText(), Constants.HOME_PAGE_HEADER);
		return this;
	}
	
	public HomePageMethodChain getLoggedInAccountValue(){
		WebDriverWait wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.elementToBeClickable(accountName));
		Assert.assertEquals(accountName.getText(), prop.getProperty("accountname"));
		return this;
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
