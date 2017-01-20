package com.globantu.automation.marcos_irisarri.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.globantu.automation.marcos_irisarri.framework.web.PageObjectBase;

public class CruiseResultsPage extends PageObjectBase {

	@FindBy(id="destination-select")
	private WebElement cmbDestination;
	
	@FindBy(id="departureMonth-select")
	private WebElement cmbDepartureMonth;
	
	@FindBy(css="#main-results .flex-card")
	private List<WebElement> cruises;
	
	public CruiseResultsPage(WebDriver driver) {
        super(driver);
    }
	
	public String getDestination() {
		return new Select(cmbDestination).getFirstSelectedOption().getText();
	}
	
	public String getDepartureMonth() {
		return new Select(cmbDepartureMonth).getFirstSelectedOption().getText();
	}
	
	public void selectCruiseLength(String length) {
		click(getDriver().findElement(By.id("length-"+length)));
		//getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.id("main-results"))));
		//getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".btn-secondary.btn-sub-action.show-dates-button")));
		//getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Updating...')]")));
		//getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".btn-secondary.btn-sub-action.show-dates-button")));
		getWait().until(ExpectedConditions.attributeToBe(By.id("main-results"), "class", "site-content-wrap "));
	}
	
	public int showDates(int cruiseNumberInList) {
		WebElement btnShowDate = cruises.get(cruiseNumberInList-1).findElement(By.cssSelector(".btn-secondary.btn-sub-action.show-dates-button")); 
		click(btnShowDate);
		getWait().until(ExpectedConditions.presenceOfElementLocated(By.className("itinerary-details")));
		
		WebElement btnShowItinerary = getDriver().findElements(By.className("itinerary-details")).get(cruiseNumberInList-1).findElement(By.className("ports-toggle")); 
		click(btnShowItinerary);
		
		return getDriver().findElements(By.className("itinerary-details")).get(cruiseNumberInList-1).findElements(By.className("port-row")).size();		
	}
}
