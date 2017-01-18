package com.globantu.automation.marcos_irisarri.testng;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.globantu.automation.marcos_irisarri.framework.runner.WebAutomationTestNGSuite;
import com.globantu.automation.marcos_irisarri.pageobjects.FlightResultsPage;
import com.globantu.automation.marcos_irisarri.pageobjects.FlightSearchForm;
import com.globantu.automation.marcos_irisarri.pageobjects.TravelocityHome;

public class TravelocityTests extends WebAutomationTestNGSuite<TravelocityHome> {
	
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	
	@Test
    public void flightSearch() {
        
		//Part 1
		TravelocityHome home = getStartingPage();

        FlightSearchForm searchForm = home.clickOnFlightTab();
 
        LocalDate departDate = LocalDate.of(2017, 8, 3);
        LocalDate returnDate = LocalDate.of(2017, 10, 19);
        FlightResultsPage result = searchForm.doSearch("LAS", "LAX", departDate, returnDate, 1, 0);

        //Part 2
        Assert.assertEquals("Las Vegas (LAS)", result.getDeparture());
        Assert.assertEquals("Los Angeles, CA, United States (LAX)", result.getArrival());
        Assert.assertEquals(departDate.format(DATE_FORMAT), result.getDepartureDate());
        Assert.assertEquals(returnDate.format(DATE_FORMAT), result.getReturnDate());
        Assert.assertEquals("ROUND_TRIP", result.getRouteType());
        
        //Part 3
        result.sortResults("Duration (Shortest)");
        Assert.assertTrue(result.isSortedByDurationAsc());
        
        
        //Part 4
        result.selectFlight(1);
        
        //Part 5
        result.selectFlight(3);
        
        //Part 6
        
    }
}
