package com.globantu.automation.marcos_irisarri.pageobjects;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.globantu.automation.marcos_irisarri.framework.web.PageObjectBase;

public class RoomSelectPage extends PageObjectBase {

	@FindBy(css=".segment.no-target.room.cf.room-above-fold")
	private List<WebElement> rooms;
	
	@FindBy(id="hotel-name")
	private WebElement lblHotelName;
	
	@FindBy(css=".star-rating.rating-secondary.star.rating .visuallyhidden")
	private WebElement lblStarRating;
	
	@FindBy(css=".phone-number span")
	private WebElement lblTelephone;
	
	@FindBy(css=".price.link-to-rooms")
	private WebElement lblPrice;
	
	private String selectedHotelName;
	private String selectedHotelStars;
	private String selectedHotelTelephone;
	private String selectedHotelPrice;
	
	public RoomSelectPage(WebDriver driver, String hotelName, String stars, String telephone, String price) {
		super(driver);
		/*
         * Room select page opens up a new window.
         * We need to switch WebDriver's focus to the NEW window, which is the other one that is not the current one.
         */
        getWait().until(numberOfWindowsToBe(2));
        final String currentHandler = driver.getWindowHandle();
        driver.getWindowHandles().forEach(h -> {
            if (!h.equals(currentHandler) && !getDriver().getTitle().startsWith("Book ")) {
                driver.switchTo().window(h);
            }
        });
        
        selectedHotelName = hotelName;
        selectedHotelStars = stars;
        selectedHotelTelephone = telephone;
        selectedHotelPrice = price;
	}
	
	public String getSelectedHotelName() {
		return selectedHotelName;
	}
	
	public String getSelectedHotelStars() {
		return selectedHotelStars;
	}
	
	public String getSelectedHotelTelephone() {
		return selectedHotelTelephone;
	}
	
	public String getSelectedHotelPrice() {
		return selectedHotelPrice;
	}
	
	public String getHotelName() {
		return lblHotelName.getText();
	}
	
	public String getStarRating() {
		return lblStarRating.getText();
	}
	
	public String getTelephone() {
		return lblTelephone.getText();
	}
	
	public String getPrice() {
		return lblPrice.getText();
	}
	
	public FlightResultsPage selectRoom(int numberInList) {
		WebElement btnSelect = rooms.get(numberInList-1).findElement(By.cssSelector(".book-button-wrapper a"));
		click(btnSelect);
		return new FlightResultsPage(getDriver());
	}
}
