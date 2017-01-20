package com.globantu.automation.marcos_irisarri.testng;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.globantu.automation.marcos_irisarri.framework.runner.WebAutomationTestNGSuite;
import com.globantu.automation.marcos_irisarri.pageobjects.CruiseResultsPage;
import com.globantu.automation.marcos_irisarri.pageobjects.CruiseSearchForm;
import com.globantu.automation.marcos_irisarri.pageobjects.FlightPlusHotelResultsPage;
import com.globantu.automation.marcos_irisarri.pageobjects.FlightPlusHotelSearchForm;
import com.globantu.automation.marcos_irisarri.pageobjects.FlightResultsPage;
import com.globantu.automation.marcos_irisarri.pageobjects.FlightSearchForm;
import com.globantu.automation.marcos_irisarri.pageobjects.HotelResultsPage;
import com.globantu.automation.marcos_irisarri.pageobjects.HotelSearchForm;
import com.globantu.automation.marcos_irisarri.pageobjects.RoomSelectPage;
import com.globantu.automation.marcos_irisarri.pageobjects.TravelocityHome;
import com.globantu.automation.marcos_irisarri.pageobjects.TripDetails;
import com.globantu.automation.marcos_irisarri.pageobjects.WhoIsTravellingPage;

public class TravelocityTests extends WebAutomationTestNGSuite<TravelocityHome> {
	
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	
	@Test(enabled=true)
    public void flightBookingTest() {
        
		//Part 1
		TravelocityHome home = getStartingPage();

        FlightSearchForm searchForm = home.clickOnFlightTab();
 
        LocalDate departDate = LocalDate.of(2017, 7, 3);
        LocalDate returnDate = LocalDate.of(2017, 10, 19);
        FlightResultsPage result = searchForm.doSearch("LAS", "LAX", departDate, returnDate, 1, 0);

        //Part 2
        Assert.assertEquals(result.getDeparture(), "Las Vegas (LAS)");
        Assert.assertEquals(result.getArrival(), "Los Angeles, CA, United States (LAX)");
        Assert.assertEquals(result.getDepartureDate(), departDate.format(DATE_FORMAT));
        Assert.assertEquals(result.getReturnDate(), returnDate.format(DATE_FORMAT));
        Assert.assertEquals(result.getRouteType(), "ROUND_TRIP");
        
        //Part 3
        result.sortResults("Duration (Shortest)");
        Assert.assertTrue(result.isSortedByDurationAsc());
        
        
        //Parts 4 & 5
        TripDetails details = result.selectFlights(1, 3);
        
        //Part 6
        Assert.assertEquals(details.getDepartureFrom(), "McCarran Intl. (LAS)");
        Assert.assertEquals(details.getDepartureTo(), "Los Angeles Intl. (LAX)");
        Assert.assertEquals(details.getReturnFrom(), "Los Angeles Intl. (LAX)");
        Assert.assertEquals(details.getReturnTo(), "McCarran Intl. (LAS)");
        Assert.assertEquals(details.getDepartureDate(), departDate);
        Assert.assertEquals(details.getReturnDate(), returnDate);
        
        //Part 8
        WhoIsTravellingPage whoIsTravelling = details.continueBooking();
        
        //Part 9
        Assert.assertEquals(whoIsTravelling.getTitle(), "Travelocity: Payment");
        Assert.assertEquals(whoIsTravelling.getModuleTitle(), "Who's traveling?");
        Assert.assertEquals(whoIsTravelling.getOriginDestinationCity(), "Las Vegas to Los Angeles");
        Assert.assertEquals(whoIsTravelling.getNumberOfTickets(), 1);
        Assert.assertEquals(whoIsTravelling.getTripType(), "Roundtrip");
	
    }
	
	@Test(enabled=true)
	public void flightBookingWithHotelAndCarTest()
	{
		//Part 1
		TravelocityHome home = getStartingPage();
		FlightPlusHotelSearchForm searchForm = home.clickOnFlightPlusHotelTab();
		
		//Part 2
		LocalDate departDate = LocalDate.of(2017, 5, 3);
        LocalDate returnDate = departDate.plusDays(13);
        FlightPlusHotelResultsPage result = searchForm.doSearch("LAS", "LAX", departDate, returnDate, 1, 0, 1, null, null);
		
        //Part 3
        Assert.assertTrue(result.getTitle().contains("Search Results | Travelocity"));
        Assert.assertEquals(result.getOrigin(), "Las Vegas, NV, United States (LAS-All Airports)");
        Assert.assertEquals(result.getDestination(), "Los Angeles (and vicinity),Los Angeles (and vicinity)");
        Assert.assertEquals(result.getSectionHeader(), "Start by choosing your hotel");
        Assert.assertEquals(result.getActiveTabText(), "Hotel");
        
        //Part 4
        result.sortResults("Price");
        Assert.assertTrue(result.isSortedByPriceAsc());
        
        //Part 5
        RoomSelectPage roomSelect = result.selectFirstHotelWithStars(3);
        
        //Part 6
        Assert.assertEquals(roomSelect.getHotelName(), roomSelect.getSelectedHotelName());
        Assert.assertEquals(roomSelect.getStarRating(), roomSelect.getSelectedHotelStars());
        //Assert.assertEquals(roomSelect.getTelephone(), roomSelect.getSelectedHotelTelephone()); //Always shows the same phone number on the page
        Assert.assertEquals(roomSelect.getPrice(), roomSelect.getSelectedHotelPrice());
        
        //Part 7
        FlightResultsPage flightResults = roomSelect.selectRoom(1);
        
        //Parts 8 & 9
        TripDetails details = flightResults.selectFlights(1, 3);
        
        //Part 10
        details.selectCar(1);
        
        //Part 11
        Assert.assertEquals(details.getDepartureFromWithHotel(), "Las Vegas (LAS)");
        Assert.assertEquals(details.getDepartureToWithHotel(), "Los Angeles (LAX)");
        Assert.assertEquals(details.getNumberOfTickets(), 1);
        Assert.assertEquals(details.getVehicles(), 1);
        
        WhoIsTravellingPage whoIsTravelling = details.continueBooking();
        
        //Part 13
        Assert.assertEquals(whoIsTravelling.getTitle(), "Travelocity: Payment");
        Assert.assertEquals(whoIsTravelling.getModuleTitle(), "Who's traveling?");
        Assert.assertTrue(whoIsTravelling.getOriginDestinationCity().contains("Las Vegas to Los Angeles"));
        Assert.assertEquals(whoIsTravelling.getNumberOfTickets(), 1);
        Assert.assertEquals(whoIsTravelling.getTripType(), "Roundtrip");
	}
	
	@Test(enabled=true)
	public void hotelSearchTest()
	{
		//Part 1
		TravelocityHome home = getStartingPage();
		
		HotelSearchForm searchForm = home.clickOnHotelsMenu();
		searchForm.clickOnHotelOnlyTab();
		
		LocalDate checkinDate = LocalDate.of(2017, 6, 14);
        LocalDate checkoutDate = checkinDate.plusDays(3);
        HotelResultsPage result = searchForm.doSearch("Dunes Manor Hotel", checkinDate, checkoutDate, 1, 1, 0);
		
        Assert.assertTrue(result.getSelectHotelName().contains("Dunes Manor Hotel"));
	}
	
	@Test(enabled=true)
	public void hotelSearchWithIncorrectDatesFailsTest() {
		
		//Part 1
		TravelocityHome home = getStartingPage();
		FlightPlusHotelSearchForm searchForm = home.clickOnFlightPlusHotelTab();
		
		//Part 2, 3 & 4
		LocalDate departDate = LocalDate.of(2017, 5, 3);
        LocalDate returnDate = departDate.plusDays(6);
        LocalDate hotelCheckinDate = departDate.plusDays(10);
        LocalDate hotelCheckoutDate = departDate.plusDays(14);
        searchForm.doSearch("LAS", "LAX", departDate, returnDate, 1, 0, 1, hotelCheckinDate, hotelCheckoutDate);
        
        //Part 5
        Assert.assertEquals(searchForm.getErrorMessage(), "Your partial check-in and check-out dates must fall within your arrival and departure dates. Please review your dates.");
	}

	@Test(enabled=true)
	public void cruiseInformationTest() {
		
		//Part 1
		TravelocityHome home = getStartingPage();
		CruiseSearchForm searchForm = home.clickOnCruisesTab();
		
		//Parts 2 & 3
		String destination = "Europe";
		String departureMonth = "May 2017";
		CruiseResultsPage result = searchForm.doSearch(destination, departureMonth);
		
		//Part 4
		Assert.assertEquals(result.getDestination(), destination);
		Assert.assertEquals(result.getDepartureMonth(), departureMonth);
		
		//Part 5
		result.selectCruiseLength("10-14");
		
		//Part 6
		int itineraryElements = result.showDates(1);
		
		Assert.assertTrue(itineraryElements >= 10);
		Assert.assertTrue(itineraryElements <= 15);
	}
}