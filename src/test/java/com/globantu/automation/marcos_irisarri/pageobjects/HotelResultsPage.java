package com.globantu.automation.marcos_irisarri.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.globantu.automation.marcos_irisarri.framework.web.PageObjectBase;

public class HotelResultsPage extends PageObjectBase {
	
	@FindBy(css="#resultsContainer article")
	private List<WebElement> hotels;
	
	public HotelResultsPage(WebDriver driver) {
        super(driver);
    }
	
	public String getSelectHotelName() {
		
		for(WebElement hotel : hotels) {
			
			try {
				WebElement heading = hotel.findElement(By.cssSelector(".flex-flag.listing-headline .pinnedName"));
				if(heading.getText().equalsIgnoreCase("You picked this hotel")) {
					return hotel.findElement(By.cssSelector(".hotelName.fakeLink")).getText();
				}
			}
			catch(NoSuchElementException e) {
				
			}
		}
		
		return "";
	}
}
