package com.globantu.automation.marcos_irisarri.pageobjects;

import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FlightPlusHotelSearchForm extends SearchFormBase {
	
	@FindBy(id="partialHotelBooking")
	private WebElement chkPartialHotelBooking;
	
	public FlightPlusHotelSearchForm(WebDriver driver) {
        super(driver);
    }
	
	public void clickPartialHotelBooking() {
		click(chkPartialHotelBooking);
	}
	
	public FlightPlusHotelResultsPage doSearch(String origin, String destination, LocalDate departDate, LocalDate returnDate, 
			int adults, int children, int rooms, LocalDate hotelCheckinDate, LocalDate hotelCheckoutDate) {
		
		fillForm(origin, destination, departDate, returnDate, adults, children, rooms);
		if(hotelCheckinDate != null && hotelCheckoutDate != null) {
			
			clickPartialHotelBooking();
			getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.id("package-checkin"))));
			getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.id("package-checkout"))));
			WebElement txtCheckin = getDriver().findElement(By.id("package-checkin"));
			WebElement txtCheckout = getDriver().findElement(By.id("package-checkout"));
			selectDateInDatePicker(txtCheckin, hotelCheckinDate, departDate);
			selectDateInDatePicker(txtCheckout, hotelCheckoutDate, returnDate);
		}
		
		clickSearch();
        return new FlightPlusHotelResultsPage(getDriver());
    }
}
