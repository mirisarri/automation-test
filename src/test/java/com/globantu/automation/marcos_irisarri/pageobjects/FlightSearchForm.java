package com.globantu.automation.marcos_irisarri.pageobjects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.globantu.automation.marcos_irisarri.framework.web.PageObjectBase;

public class FlightSearchForm extends PageObjectBase {

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	
	@FindBy(id = "flight-origin")
    private WebElement txtOrigin;

    @FindBy(id = "flight-destination")
    private WebElement txtDestination;

    @FindBy(id = "flight-departing")
    private WebElement txtDeparture;

    @FindBy(id = "flight-returning")
    private WebElement txtReturn;

	@FindBy(className="datepicker-paging datepicker-next btn-paging btn-secondary next")
	private WebElement btnDatePickerNextMonth;
	
	@FindBy(id="flight-adults")
	private WebElement cmbAdults;
	
	@FindBy(id="flight-children")
	private WebElement cmbChildren;
	
	@FindBy(id = "search-button")
    private WebElement btnSearch;
	
	public FlightSearchForm(WebDriver driver) {
        super(driver);
    }
	
	public FlightResultsPage doSearch(String origin, String destination, int daysTilTrip, int daysToStayOnDestination, int adults, int children) {

        type(txtOrigin, origin);
        type(txtDestination, destination);

        LocalDateTime departDate = dateFromToday(daysTilTrip);
        LocalDateTime returnDate = dateFromToday(daysTilTrip + daysToStayOnDestination);

        type(txtDeparture, departDate.format(DATE_FORMAT));
        type(txtReturn, returnDate.format(DATE_FORMAT));

        selectByVisibleText(cmbAdults, adults + "");
        selectByVisibleText(cmbChildren, children + "");
        
        click(btnSearch);

        return new FlightResultsPage(getDriver());
    }
	
	private LocalDateTime dateFromToday(int plusDays) {
        // today + <plusDays> days
        return LocalDateTime.now().plusDays(plusDays);
    }
	
	
	/*
	public void setDepartureDate(Calendar selectedDate){
		Assert.assertTrue(selectedDate.after(Calendar.getInstance()));
		departureDate.click();
		int selectedMonth = selectedDate.get(Calendar.MONTH);
		int monthDiff = selectedMonth - Calendar.getInstance().get(Calendar.MONTH);
		while(monthDiff > 0){
			datePickerNextButton.click();
			monthDiff--;
		}
	}*/
}
