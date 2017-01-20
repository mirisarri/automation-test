package com.globantu.automation.marcos_irisarri.pageobjects;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

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
	
	@FindBy(className="section-header-main")
	private WebElement sectionHeader;
	
	@FindBy(className="msi-active-state")
	private WebElement activeTab;
	
	@FindBy(css=".btn-sort.tab")
	private List<WebElement> sortButtons;
	
	@FindBy(css="#resultsContainer article")
	private List<WebElement> hotels;
	
	public FlightPlusHotelResultsPage(WebDriver driver) {
        super(driver);
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.className("imgLoading")));
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
	
	public String getSectionHeader() {
		return sectionHeader.getText();
	}
	
	public String getActiveTabText() {
		return activeTab.findElement(By.className("msi-label")).getText();
	}
	
	public void sortResults(String criteria) {
		
		for (WebElement btnSort : sortButtons) {
			if(btnSort.findElement(By.className("btn-label")).getText().equalsIgnoreCase(criteria)) {
				click(btnSort);
				break;
			}
		}
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Updating your results')]")));
	}
	
	public boolean isSortedByPriceAsc() {
		
		try {
			Thread.sleep(3000); //wait on sortResults not working
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		hotels = getDriver().findElements(By.cssSelector("#resultsContainer article"));
		int prevPrice = 0;
		for (WebElement hotelResult : hotels) {
			if(hotelResult.getAttribute("data-automation").equals("organic")) {
				String priceStr = hotelResult.findElement(By.cssSelector(".actualPrice.price.fakeLink")).getText();
				int newPrice;
				try {
					newPrice = NumberFormat.getCurrencyInstance(Locale.US).parse(priceStr).intValue();
				} catch (ParseException e) {
					e.printStackTrace();
					return false;
				}
				if(newPrice < prevPrice) {
					return false;
				}
				prevPrice = newPrice;
			}
		}
		return true;
	}
	
	public RoomSelectPage selectFirstHotelWithStars(int numberOfStars) {
		
		boolean found = false;
		String hotelName = "";
		String stars = "";
		String telephone = "";
		String price = "";
		
		while(!found) {
			
			hotels = getDriver().findElements(By.cssSelector("#resultsContainer article"));
			for (WebElement hotelResult : hotels) {
				if(hotelResult.getAttribute("data-automation").equals("organic")) {
					stars = hotelResult.findElement(By.className("starRating")).findElement(By.className("visuallyhidden")).getText();
					if(Integer.valueOf(stars.substring(0, 1)) == numberOfStars) {
						
						hotelName = hotelResult.findElement(By.cssSelector(".hotelName.fakeLink")).getText();
						telephone = hotelResult.findElement(By.cssSelector(".phone.secondary.gt-mobile")).findElement(By.className("no-wrap")).getText();
						price = hotelResult.findElement(By.cssSelector(".actualPrice.price.fakeLink")).getText();
						
						click(hotelResult.findElement(By.className("flex-link")));
						found = true;
						break;
					}
				}	
			}
			
			if(!found) {
				//If notifications divs are shown wait for them to close
				getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.className("notification")));
				
				//Check in next page
				click(getDriver().findElement(By.className("pagination-next")));
				getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Updating your results')]")));
				try {
					Thread.sleep(3000); //wait on sortResults not working
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		return new RoomSelectPage(getDriver(), hotelName, stars, telephone, price);
	}
}
