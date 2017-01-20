package com.globantu.automation.marcos_irisarri.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.globantu.automation.marcos_irisarri.framework.web.PageObjectBase;

public class FlightResultsPage extends PageObjectBase {

	@FindBy(id="departure-airport-1")
	private WebElement txtDeparture;
	
	@FindBy(id="arrival-airport-1")
	private WebElement txtArrival;
	
	@FindBy(id="departure-date-1")
	private WebElement txtDepartureDate;
	
	@FindBy(id="return-date-1")
	private WebElement txtReturnDate;
	
	@FindBy(name="route-type")
	private List<WebElement> routeTypes;
	
	@FindBy(name="sort")
	private WebElement cmbSort;
	
	@FindBy(id="flightModuleList")
	private WebElement lstFlights;
	
	public FlightResultsPage(WebDriver driver) {
        super(driver);
    }
	
	public String getDeparture() {
		return txtDeparture.getAttribute("value");
	}
	
	public String getArrival() {
		return txtArrival.getAttribute("value");
	}
	
	public String getDepartureDate() {
		return txtDepartureDate.getAttribute("value");
	}
	
	public String getReturnDate() {
		return txtReturnDate.getAttribute("value");
	}
	
	public String getRouteType() {
		String selectedRouteType = "";
		for (WebElement routeType : routeTypes) {
			if(routeType.isSelected()) {
				selectedRouteType = routeType.getAttribute("value");
			}
		}
		
		return selectedRouteType;
	}
	
	public void sortResults(String criteria) {
		selectByVisibleText(cmbSort, criteria);
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Sorting by')]")));
	}
	
	public boolean isSortedByDurationAsc() {
		
		List<WebElement> times = lstFlights.findElements(By.cssSelector(".primary.duration-emphasis"));
		
		int prevHours = 0;
		int prevMinutes = 0;
		for(WebElement time : times) {
			int newHours = new Integer(time.getText().split(" ")[0].split("h")[0]);
			int newMinutes = new Integer(time.getText().split(" ")[1].split("m")[0]);
			if(newHours < prevHours || (newHours == prevHours && newMinutes < prevMinutes)) {
				return false;
			}
			prevHours = newHours;
			prevMinutes = newMinutes;
		}
		return true;
	}
	
	public void selectFlight(int numberInList) {
		
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("acol-interstitial")));
		lstFlights = getDriver().findElement(By.id("flightModuleList"));
		WebElement btnSelect = lstFlights.findElements(By.cssSelector(".btn-secondary.btn-action.t-select-btn")).get(numberInList-1);
		//click(btnSelect);
		btnSelect.click();
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Finding return flights')]")));
	}
	
	public TripDetails selectFlights(int departurePositionInList, int returnPositionInList)
	{
		selectFlight(departurePositionInList);
		selectFlight(returnPositionInList);
		return new TripDetails(getDriver());
	}
}
