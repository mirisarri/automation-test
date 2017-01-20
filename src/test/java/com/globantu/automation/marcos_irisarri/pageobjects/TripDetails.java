package com.globantu.automation.marcos_irisarri.pageobjects;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.globantu.automation.marcos_irisarri.framework.web.PageObjectBase;

public class TripDetails extends PageObjectBase {

	private static final String PAGE_TITLE = "Trip Detail | Travelocity";
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d, yyyy");
	
	@FindBy(id="flightModule-0")
	private WebElement departureFlight;
	
	@FindBy(id="flightModule-1")
	private WebElement returnFlight;
	
	@FindBy(id="trip-flight-to")
	private WebElement departureFrom;
	
	@FindBy(id="trip-flight-from")
	private WebElement departureTo;
	
	@FindAll({@FindBy(id="departure-date-0"), @FindBy(id="trip-flight-start")})
	private List<WebElement> lblDepartureDate;
	
	@FindAll({@FindBy(id="departure-date-1"), @FindBy(id="trip-flight-end")})
	private List<WebElement> lblReturnDate;
	
	@FindBy(css=".btn-secondary.btn-sub-action.gt-add-btn")
	List<WebElement> addCarButtons;
	
	@FindBy(css=".btn-primary.btn-action")
	private WebElement btnContinueBooking;
	
	@FindBy(id="numberOfTickets")
	private WebElement lblNumberOfTickets;
	
	@FindBy(css=".activities .ticket-traveler-info")
	private WebElement lblVehicles;
	
	public TripDetails(WebDriver driver){
		super(driver);
		/*
         * Trip Details page opens up a new window.
         * We need to switch WebDriver's focus to the NEW window, which is the other one that is not the current one.
         */
        getWait().until(numberOfWindowsToBe(2));
        final String currentHandler = driver.getWindowHandle();
        driver.getWindowHandles().forEach(h -> {
            if (!h.equals(currentHandler) && !PAGE_TITLE.equals(getDriver().getTitle())) {
                driver.switchTo().window(h);
            }
        });
	}
	
	public String getDepartureFrom() {
		return departureFlight.findElements(By.cssSelector(".fdp-location")).get(0).getText();
	}
	
	public String getDepartureTo() {
		return departureFlight.findElements(By.cssSelector(".fdp-location")).get(1).getText();
	}
	
	public String getReturnFrom() {
		return returnFlight.findElements(By.className("fdp-location")).get(0).getText();
	}
	
	public String getReturnTo() {
		return returnFlight.findElements(By.className("fdp-location")).get(1).getText();
	}
	
	public LocalDate getDepartureDate() {
		return LocalDate.parse(lblDepartureDate.get(0).getText().toLowerCase(), DATE_FORMAT);
	}
	
	public LocalDate getReturnDate() {
		return LocalDate.parse(lblReturnDate.get(0).getText().toLowerCase(), DATE_FORMAT);
	}
	
	public String getDepartureFromWithHotel() {
		return departureFrom.getText();
	}
	
	public String getDepartureToWithHotel() {
		return departureTo.getText();
	}
	
	public int getNumberOfTickets() {
		return Integer.valueOf(lblNumberOfTickets.getText().split(" ")[0]);
	}
	
	public int getVehicles() {
		return Integer.valueOf(lblVehicles.getText().split(" ")[0]);
	}
	
	public void selectCar(int numberInList) {
		WebElement btnAddCar = addCarButtons.get(numberInList-1);
		click(btnAddCar);
		getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.className("added-to-trip"))));
	}
	
	public WhoIsTravellingPage continueBooking() {
		click(btnContinueBooking);
		return new WhoIsTravellingPage(getDriver());
	}
}
