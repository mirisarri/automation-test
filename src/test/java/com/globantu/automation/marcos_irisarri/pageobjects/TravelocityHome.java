package com.globantu.automation.marcos_irisarri.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.globantu.automation.marcos_irisarri.framework.web.PageObjectBase;

public class TravelocityHome extends PageObjectBase {

	private static final String TRAVELOCITY_URL = "https://www.travelocity.com";
	
	@FindBy(id="tab-flight-tab")
	private WebElement flightTab;
	
	@FindBy(id="tab-package-tab")
	private WebElement flightPlusHotelTab;
	
	@FindBy(id="primary-header-hotel")
	private WebElement hotelsMenu;
	
	@FindBy(id="tab-cruise-tab")
	private WebElement cruisesTab;
	
	public TravelocityHome(WebDriver driver){
		super(driver);
        driver.navigate().to(TRAVELOCITY_URL);
	}
	
	public FlightSearchForm clickOnFlightTab() {
        click(flightTab);
        return new FlightSearchForm(getDriver());
    }
	
	public FlightPlusHotelSearchForm clickOnFlightPlusHotelTab() {
        click(flightPlusHotelTab);
        return new FlightPlusHotelSearchForm(getDriver());
    }
	
	public HotelSearchForm clickOnHotelsMenu() {
		click(hotelsMenu);
		return new HotelSearchForm(getDriver());
	}
	
	public CruiseSearchForm clickOnCruisesTab() {
		click(cruisesTab);
		return new CruiseSearchForm(getDriver());
	}
}
