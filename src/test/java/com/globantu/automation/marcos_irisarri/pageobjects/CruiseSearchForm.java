package com.globantu.automation.marcos_irisarri.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.globantu.automation.marcos_irisarri.framework.web.PageObjectBase;

public class CruiseSearchForm extends PageObjectBase {
	
	@FindBy(id="cruise-destination")
	private WebElement cmbDestination;
	
	@FindBy(id="cruise-departure-month")
	private WebElement cmbDepartureMonth;
	
	@FindBy(id = "search-button")
    private WebElement btnSearch;
	
	public CruiseSearchForm(WebDriver driver) {
        super(driver);
    }
	
	public CruiseResultsPage doSearch(String destination, String departureMonth) {

		selectByVisibleText(cmbDestination, destination);
		selectByVisibleText(cmbDepartureMonth, departureMonth);
        
        click(btnSearch);

        return new CruiseResultsPage(getDriver());
    }
}
