package com.globantu.automation.marcos_irisarri.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.globantu.automation.marcos_irisarri.framework.web.PageObjectBase;

public class FlightPlusHotelResultsPage extends PageObjectBase {

	@FindBy(css=".origin.fakeLink")
	private WebElement btnOrigin;
	
	@FindBy(css=".destination.fakeLink")
	private WebElement btnDestination;
	
	public FlightPlusHotelResultsPage(WebDriver driver) {
        super(driver);
    }
	
	public String getTitle() {
		return getDriver().getTitle();
	}
	
	public String getOrigin() {
		return btnOrigin.getText();
	}
	
	public String getDestination() {
		return btnDestination.getText();
	}
	/*
	public void sortResults(String criteria) {
		selectByVisibleText(cmbSort, criteria);
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Sorting by')]")));
	}
	
	public boolean isSortedByDurationAsc() {
		
		List<WebElement> times = lstFlights.findElements(By.cssSelector(".primary.duration-emphasis"));
		if(!times.isEmpty()){
			
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
		}
		return true;
	}
	
	public void selectFlight(int numberInList) {
		WebElement btnSelect = lstFlights.findElements(By.cssSelector(".btn-secondary.btn-action.t-select-btn")).get(numberInList-1);
		click(btnSelect);
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Finding return flights')]")));
	}
	
	public TripDetails selectFlights(int departurePositionInList, int returnPositionInList)
	{
		selectFlight(departurePositionInList);
		selectFlight(returnPositionInList);
		return new TripDetails(getDriver());
	}*/
}
