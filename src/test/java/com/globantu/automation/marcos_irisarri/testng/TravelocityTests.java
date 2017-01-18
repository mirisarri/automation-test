package com.globantu.automation.marcos_irisarri.testng;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.globantu.automation.marcos_irisarri.framework.runner.WebAutomationTestNGSuite;
import com.globantu.automation.marcos_irisarri.pageobjects.FlightPlusHotelSearchForm;
import com.globantu.automation.marcos_irisarri.pageobjects.FlightResultsPage;
import com.globantu.automation.marcos_irisarri.pageobjects.FlightSearchForm;
import com.globantu.automation.marcos_irisarri.pageobjects.HotelResultsPage;
import com.globantu.automation.marcos_irisarri.pageobjects.HotelSearchForm;
import com.globantu.automation.marcos_irisarri.pageobjects.TravelocityHome;
import com.globantu.automation.marcos_irisarri.pageobjects.TripDetails;

public class TravelocityTests extends WebAutomationTestNGSuite<TravelocityHome> {
	
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	
	@Test(enabled=false)
    public void flightBookingTest() {
        
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
        
        
        //Parts 4 & 5
        TripDetails details = result.selectFlights(1, 3);
        
        //Part 6
        /*Assert.assertEquals("McCarran Intl. (LAS)", details.getDepartureFrom());
        Assert.assertEquals("Los Angeles Intl. (LAX)", details.getDepartureTo());
        Assert.assertEquals("Los Angeles Intl. (LAX)", details.getReturnFrom());
        Assert.assertEquals("McCarran Intl. (LAS)", details.getReturnTo());*/
    }
	
	@Test(enabled=false)
	public void flightBookingWithHotelAndCarTest()
	{
		//Part 1
		TravelocityHome home = getStartingPage();
		
		FlightPlusHotelSearchForm searchForm = home.clickOnFlightPlusHotelTab();
		
		LocalDate departDate = LocalDate.of(2017, 8, 3);
        LocalDate returnDate = departDate.plusDays(13);
        FlightResultsPage result = searchForm.doSearch("LAS", "LAX", departDate, returnDate, 1, 0);
		
	}
	
	@Test(enabled=true)
	public void hotelSearchTest()
	{
		//Part 1
		TravelocityHome home = getStartingPage();
		
		HotelSearchForm searchForm = home.clickOnHotelsMenu();
		searchForm.clickOnHotelOnlyTab();
		
		LocalDate checkinDate = LocalDate.now();
        LocalDate checkoutDate = checkinDate.plusDays(3);
        HotelResultsPage result = searchForm.doSearch("Dunes Manor Hotel", checkinDate, checkoutDate, 1, 1, 0);
		
	}
}
