package com.globantu.automation.marcos_irisarri.pageobjects;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.globantu.automation.marcos_irisarri.framework.web.PageObjectBase;

public class FlightSearchForm extends PageObjectBase {
	
	@FindBy(id = "flight-origin")
    private WebElement txtOrigin;

    @FindBy(id = "flight-destination")
    private WebElement txtDestination;

    @FindBy(id = "flight-departing")
    private WebElement txtDeparture;

    @FindBy(id = "flight-returning")
    private WebElement txtReturn;

    @FindBy(xpath="(//button[@type='button'])[6]")
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
	
	public FlightResultsPage doSearch(String origin, String destination, LocalDate departDate, LocalDate returnDate, int adults, int children) {

        type(txtOrigin, origin);
        type(txtDestination, destination);
        
        //Validate departure date is before return date
        Assert.assertTrue(departDate.isBefore(returnDate));
        
        //Validate departure date is at least 2 months in the future
        LocalDate today = LocalDate.now();
        Assert.assertTrue(departDate.isAfter(today.plusMonths(2)));
        
        selectDateInDatePicker(txtDeparture, departDate, today);
        selectDateInDatePicker(txtReturn, returnDate, departDate);

        selectByIndex(cmbAdults, adults-1);
        selectByIndex(cmbChildren, children);
        
        click(btnSearch);

        return new FlightResultsPage(getDriver());
    }
	
	private void selectDateInDatePicker(WebElement txtDate, LocalDate selectDate, LocalDate currentDateSelected){
		
		//Display date picker
		click(txtDate);
        
		//Move forward in months
		int monthsDiff = selectDate.getMonthValue() - currentDateSelected.getMonthValue();
        while(monthsDiff > 0) {
        	click(btnDatePickerNextMonth);
        	monthsDiff--;
        }
        
        //Select the day
        List<WebElement> days = getDriver().findElements(By.className("datepicker-cal-date"));
        for(WebElement day : days){
        	if(new Integer(day.getAttribute("data-year")) == selectDate.getYear() && 
        			new Integer(day.getAttribute("data-month")) == selectDate.getMonthValue() - 1 && 
        			new Integer(day.getAttribute("data-day")) == selectDate.getDayOfMonth()){
        		
        		day.click();
        		break;
        	}
        }
	}
}
