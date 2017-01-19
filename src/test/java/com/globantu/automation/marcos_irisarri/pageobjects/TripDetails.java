package com.globantu.automation.marcos_irisarri.pageobjects;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.globantu.automation.marcos_irisarri.framework.web.PageObjectBase;

public class TripDetails extends PageObjectBase {

	private static final String PAGE_TITLE = "Trip Detail | Travelocity";
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d, yyyy");
	
	@FindBy(id="flightModule-0")
	private WebElement departureFlight;
	
	@FindBy(id="flightModule-1")
	private WebElement returnFlight;
	
	@FindBy(id="departure-date-0")
	private WebElement lblDepartureDate;
	
	@FindBy(id="departure-date-1")
	private WebElement lblReturnDate;
	
	@FindBy(css=".btn-primary.btn-action")
	private WebElement btnContinueBooking;
	
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
		return LocalDate.parse(lblDepartureDate.getText().toLowerCase(), DATE_FORMAT);
	}
	
	public LocalDate getReturnDate() {
		return LocalDate.parse(lblReturnDate.getText().toLowerCase(), DATE_FORMAT);
	}
	
	public WhoIsTravellingPage continueBooking() {
		click(btnContinueBooking);
		return new WhoIsTravellingPage(getDriver());
	}
}
