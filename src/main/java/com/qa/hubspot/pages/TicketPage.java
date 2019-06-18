package com.qa.hubspot.pages;

import org.openqa.selenium.WebDriver;

public class TicketPage {

	WebDriver driver;
	public TicketPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void reaiseTicket(){
		System.out.println("raise a ticket");
	}
	
	
}
