package com.globantu.automation.marcos_irisarri.testng;

import org.testng.annotations.Test;

import com.globantu.automation.marcos_irisarri.framework.runner.WebAutomationTestNGSuite;
import com.globantu.automation.marcos_irisarri.pageobjects.FlightResultsPage;
import com.globantu.automation.marcos_irisarri.pageobjects.FlightSearchForm;
import com.globantu.automation.marcos_irisarri.pageobjects.TravelocityHome;

public class TravelocityTests extends WebAutomationTestNGSuite<TravelocityHome> {
	
	@Test
    public void flightSearch() {
        TravelocityHome home = getStartingPage();

        FlightSearchForm searchForm = home.clickOnFlightTab();

        FlightResultsPage result = searchForm.doSearch("LAS", "LAX", 7, 7, 3, 0);

        /*result.selectDepartureFlight(6)
                .selectReturnFlight(3)
                .continueBooking();*/
    }
}
