package com.globantu.automation.marcos_irisarri.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.globantu.automation.marcos_irisarri.framework.web.PageObjectBase;

public class TravelocityHome extends PageObjectBase {

	private static final String TRAVELOCITY_URL = "https://www.travelocity.com";
	
	@FindBy(id="tab-flight-tab")
	private WebElement flightTab;
	
	public TravelocityHome(WebDriver driver){
		super(driver);
        driver.navigate().to(TRAVELOCITY_URL);
	}
	
	public FlightSearchForm clickOnFlightTab() {
        click(flightTab);
        return new FlightSearchForm(getDriver());
    }
}
