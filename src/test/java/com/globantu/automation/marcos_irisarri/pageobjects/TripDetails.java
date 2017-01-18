package com.globantu.automation.marcos_irisarri.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.globantu.automation.marcos_irisarri.framework.web.PageObjectBase;

public class TripDetails extends PageObjectBase {

	@FindBy(id="flightModule-0")
	private WebElement departureFlight;
	
	@FindBy(id="flightModule-1")
	private WebElement returnFlight;
	
	@FindBy(id="departure-date-0")
	private WebElement lblDepartureDate;
	
	@FindBy(id="departure-date-1")
	private WebElement lblReturnDate;
	
	@FindBy(className="fdp-direction")
	private List<WebElement> airports;
	
	public TripDetails(WebDriver driver){
		super(driver);
	}
	
	public String getDepartureFrom() {
		return departureFlight.findElements(By.cssSelector(".fdp-direction")).get(0).getText();
		//return airports.get(0).getText();
	}
	
	public String getDepartureTo() {
		return departureFlight.findElements(By.cssSelector(".fdp-direction")).get(1).getText();
		//return airports.get(1).getText();
	}
	
	public String getReturnFrom() {
		return returnFlight.findElements(By.className("fdp-direction")).get(0).getText();
	}
	
	public String getReturnTo() {
		return returnFlight.findElements(By.className("fdp-direction")).get(1).getText();
	}
}
