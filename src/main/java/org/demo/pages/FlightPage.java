package org.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FlightPage extends BasePage {

    public FlightPage(WebDriver driver) {
        super(driver);
    }

    private By flightButton(String airlineName) {
        return By.xpath("//td[text()='" + airlineName + "']/ancestor::tr//td/input");
    }

    private final By flightPageTitle = By.xpath("//h3");

    public String getPageTitle() {
        waitForVisibility(flightPageTitle);
        return driver.findElement(flightPageTitle).getText();
    }

    public void selectFlight(String airlineName) {
        waitForVisibility(flightButton(airlineName));
        driver.findElement(flightButton(airlineName)).click();
    }
}
