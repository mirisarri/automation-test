package com.globantu.automation.marcos_irisarri.pageobjects;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.globantu.automation.marcos_irisarri.framework.web.PageObjectBase;

public class HotelSearchForm extends PageObjectBase {
	
	@FindBy(id="tab-hotel-tab")
	private WebElement hotelOnlyTab;
	
	@FindBy(id = "hotel-destination")
    private WebElement txtDestination;

    @FindBy(id = "hotel-checkin")
    private WebElement txtCheckin;

    @FindBy(id = "hotel-checkout")
    private WebElement txtCheckout;

    @FindBy(xpath="(//button[@type='button'])[6]")
	private WebElement btnDatePickerNextMonth;
	
    @FindBy(id="hotel-rooms")
	private WebElement cmbRooms;
    
	@FindBy(id="hotel-1-adults")
	private WebElement cmbAdults;
	
	@FindBy(id="hotel-1-children")
	private WebElement cmbChildren;
	
	@FindBy(id = "search-button")
    private WebElement btnSearch;
	
	public HotelSearchForm(WebDriver driver) {
        super(driver);
    }
	
	public void clickOnHotelOnlyTab() {
		click(hotelOnlyTab);
	}
	
	public HotelResultsPage doSearch(String destination, LocalDate checkinDate, LocalDate checkOutDate, int rooms, int adults, int children) {

        type(txtDestination, destination);
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("main-suggestion")));
        txtDestination.sendKeys(Keys.ENTER);
        
        //Validate check in date is before check out date
        Assert.assertTrue(checkinDate.isBefore(checkOutDate));
        
        selectDateInDatePicker(txtCheckin, checkinDate, LocalDate.now());
        selectDateInDatePicker(txtCheckout, checkOutDate, checkinDate);

        selectByIndex(cmbRooms, rooms-1);
        selectByIndex(cmbAdults, adults-1);
        selectByIndex(cmbChildren, children);
        
        click(btnSearch);

        return new HotelResultsPage(getDriver());
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
