package org.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReservationPage extends BasePage {

    private final By reservationPageTitle = By.xpath("//h2");

    public ReservationPage(WebDriver driver) {
        super(driver);
    }

    public String getReservationPageTitle() {
        waitForVisibility(reservationPageTitle);
        return driver.findElement(reservationPageTitle).getText();
    }
}
