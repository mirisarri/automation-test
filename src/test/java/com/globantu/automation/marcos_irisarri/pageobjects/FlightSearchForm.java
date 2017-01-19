package com.globantu.automation.marcos_irisarri.pageobjects;

import java.time.LocalDate;

import org.openqa.selenium.WebDriver;

public class FlightSearchForm extends SearchFormBase {
	
	public FlightSearchForm(WebDriver driver) {
        super(driver);
    }
	
	public FlightResultsPage doSearch(String origin, String destination, LocalDate departDate, LocalDate returnDate, int adults, int children) {

        search(origin, destination, departDate, returnDate, adults, children);
        return new FlightResultsPage(getDriver());
    }
}
