package com.globantu.automation.marcos_irisarri.pageobjects;

import org.openqa.selenium.WebDriver;

import com.globantu.automation.marcos_irisarri.framework.web.PageObjectBase;

/**
 * @author Juan Krzemien
 */
public class FlightResultsPage extends PageObjectBase {

	public FlightResultsPage(WebDriver driver) {
        super(driver);
    }
	/*
    private final By notifications = By.cssSelector("div.notification");

    @FindBy(css = "button[data-test-id='select-button']")
    private List<WebElement> selectButtons;

    private PopUp flightPlusHotelPopUp;

    public FlightResultsPage(WebDriver driver) {
        super(driver);
        this.flightPlusHotelPopUp = new PopUp(getDriver());
    }

    public FlightResultsPage selectDepartureFlight(int index) {
        selectFlight(index);
        return new FlightResultsPage(getDriver());
    }

    public ReviewTripPage selectReturnFlight(int index) {
        selectFlight(index);
        return new ReviewTripPage(getDriver());
    }

    private void selectFlight(int index) {
        getWait().until(visibilityOfAllElements(selectButtons));
        click(selectButtons.get(index - 1));
    }
*/
    /**
     * When clicking on a Select button in Results page there may be the following issues that need to be addressed:
     * <p>
     * 1 - A huge modal pop up MAY appear *before* click on it
     * 2 - Depending on the screen resolution, a toast/small notification may appear on top of the Select buttons
     * 2a - This toast messages does not have a dismiss option for IE
     * 2b - This toast messages fade away after a couple of seconds
     * 3 - A huge modal pop up MAY appear *after* click on it
     *
     * @param element to click on
     */
  /*  @Override
    protected void click(WebElement element) {
        if (flightPlusHotelPopUp.isVisible()) {
            flightPlusHotelPopUp.dismiss();
        }

        getWait().until(invisibilityOfElementLocated(notifications));

        super.click(element);

        if (flightPlusHotelPopUp.isVisible()) {
            flightPlusHotelPopUp.dismiss();
        }
    }*/
}
