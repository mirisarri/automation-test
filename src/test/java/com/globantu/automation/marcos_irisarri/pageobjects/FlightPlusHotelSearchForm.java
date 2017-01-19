package com.globantu.automation.marcos_irisarri.pageobjects;

import java.time.LocalDate;

import org.openqa.selenium.WebDriver;

public class FlightPlusHotelSearchForm extends SearchFormBase {
	
	public FlightPlusHotelSearchForm(WebDriver driver) {
        super(driver);
    }
	
	public FlightPlusHotelResultsPage doSearch(String origin, String destination, LocalDate departDate, LocalDate returnDate, int adults, int children, int rooms) {
		
		search(origin, destination, departDate, returnDate, adults, children, rooms);
        return new FlightPlusHotelResultsPage(getDriver());
    }
}
