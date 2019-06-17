package com.qa.hubspot.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.pages.BasePage;

public class ElementActions extends BasePage {
	WebDriver driver;

	public ElementActions(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By selector) {
		waitForElement(selector);
		WebElement element = driver.findElement(selector);
		return element;
	}

	public void waitForElement(By selector) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.presenceOfElementLocated(selector));
	}

	public void doClick(By selector) {
		try {
			getElement(selector).click();
		} catch (Exception e) {
			System.out.println("exception occured with locator: " + selector);
		}
	}

	public void doSendKeys(By selector, String value) {
		try {
			getElement(selector).sendKeys(value);
		} catch (Exception e) {
			System.out.println("exception occured with locator: " + selector);

		}
	}
	
	public boolean elementIsDisplayed(By selector){
		return getElement(selector).isDisplayed();
	}

}
