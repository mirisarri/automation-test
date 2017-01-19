package com.globantu.automation.marcos_irisarri.pageobjects;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import com.globantu.automation.marcos_irisarri.framework.web.PageObjectBase;

public abstract class SearchFormBase extends PageObjectBase {
	
	@FindAll({@FindBy(id="flight-origin"), @FindBy(id = "package-origin")})
    private List<WebElement> txtOrigin;
    
	@FindAll({@FindBy(id="flight-destination"), @FindBy(id = "package-destination")})
	private List<WebElement> txtDestination;
	
	@FindAll({@FindBy(id = "flight-departing"), @FindBy(id = "package-departing")})
    private List<WebElement> txtDeparture;
	
	@FindAll({@FindBy(id = "flight-returning"), @FindBy(id = "package-returning")})
    private List<WebElement> txtReturn;

    @FindBy(css=".datepicker-paging.datepicker-next.btn-paging.btn-secondary.next")
	private WebElement btnDatePickerNextMonth;
	
    @FindAll({@FindBy(id="package-rooms")})
	private List<WebElement> cmbRooms;
    
    @FindAll({@FindBy(id = "flight-adults"), @FindBy(id = "package-1-adults")})
	private List<WebElement> cmbAdults;
    
    @FindAll({@FindBy(id = "flight-children"), @FindBy(id = "package-1-children")})
	private List<WebElement> cmbChildren;
	
	@FindBy(id = "search-button")
    private WebElement btnSearch;
	
	public SearchFormBase(WebDriver driver) {
        super(driver);
    }
	
	public void search(String origin, String destination, LocalDate departDate, LocalDate returnDate, int adults, int children) {
        search(origin, destination, departDate, returnDate, adults, children, 0);
    }
	
	public void search(String origin, String destination, LocalDate departDate, LocalDate returnDate, int adults, int children, int rooms) {

        type(txtOrigin.get(0), origin);
        type(txtDestination.get(0), destination);
        
        //Validate departure date is before return date
        Assert.assertTrue(departDate.isBefore(returnDate));
        
        //Validate departure date is at least 2 months in the future
        LocalDate today = LocalDate.now();
        Assert.assertTrue(departDate.isAfter(today.plusMonths(2)));
        
        selectDateInDatePicker(txtDeparture.get(0), departDate, today);
        selectDateInDatePicker(txtReturn.get(0), returnDate, departDate);

        selectByIndex(cmbAdults.get(0), adults-1);
        selectByIndex(cmbChildren.get(0), children);
        
        if(rooms > 0) {
        	selectByIndex(cmbRooms.get(0), rooms-1);
        }
        
        click(btnSearch);
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
        	if(day.isEnabled()) {
	        	if(new Integer(day.getAttribute("data-year")) == selectDate.getYear() && 
	        			new Integer(day.getAttribute("data-month")) == selectDate.getMonthValue() - 1 && 
	        			new Integer(day.getAttribute("data-day")) == selectDate.getDayOfMonth()){
	        		
	        		day.click();
	        		break;
	        	}
        	}
        }
	}
}
