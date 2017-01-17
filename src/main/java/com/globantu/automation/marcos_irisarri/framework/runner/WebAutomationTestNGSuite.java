package com.globantu.automation.marcos_irisarri.framework.runner;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.globantu.automation.marcos_irisarri.framework.web.Browser;
import com.globantu.automation.marcos_irisarri.framework.web.PageObjectBase;

/**
 * Base test class for all TestNG based test suites.
 *
 * @author Juan Krzemien
 */
public class WebAutomationTestNGSuite<T extends PageObjectBase> extends GenericWebAutomationTest<T> {

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(Browser browser) {
        super.setUp(browser);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        super.tearDown();
    }

}
