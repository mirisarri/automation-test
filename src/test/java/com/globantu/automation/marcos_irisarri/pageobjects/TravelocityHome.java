package com.globantu.automation.marcos_irisarri.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TravelocityHome {

	WebDriver driver;
	
	@FindBy(id="package-origin")
	WebElement origin;
	
	@FindBy(id="package-destination")
	WebElement destination;
	
	@FindBy(id="package-departing")
	WebElement departureDate;
	
	@FindBy(id="package-returning")
	WebElement returnDate;
	
	@FindBy(id="search-button")
	WebElement searchButton;
	
	public TravelocityHome(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setOrigin(String strOrigin){
		origin.sendKeys(strOrigin);
	}
	
	public void setDestination(String strDestination){
		origin.sendKeys(strDestination);
	}
	
	public void clickSearchButton(){
		searchButton.click();
	}
}
