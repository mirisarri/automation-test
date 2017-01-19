package com.globantu.automation.marcos_irisarri.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.globantu.automation.marcos_irisarri.framework.web.PageObjectBase;

public class WhoIsTravellingPage extends PageObjectBase {
	
	@FindBy(className="faceoff-module-title")
	private WebElement lblModuleTitle;
	
	@FindBy(className="origin-destination-city")
	private WebElement lblOriginDestinationCity;
	
	@FindBy(className="number-of-tickets")
	private WebElement lblNumberOfTickets;
	
	@FindBy(className="trip-type")
	private WebElement lblTripType;
	
	public WhoIsTravellingPage(WebDriver driver){
		super(driver);
	}
	
	public String getTitle() {
		return getDriver().getTitle();
	}
	
	public String getModuleTitle() {
		return lblModuleTitle.getText();
	}
	
	public String getOriginDestinationCity() {
		return lblOriginDestinationCity.getText();
	}
	
	public int getNumberOfTickets() {
		return new Integer(lblNumberOfTickets.getText().split(" ")[0]);
	}
	
	public String getTripType() {
		return lblTripType.getText();
	}
}
