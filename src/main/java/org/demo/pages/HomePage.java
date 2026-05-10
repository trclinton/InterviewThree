package org.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends BasePage{

    private final By homePageTitle = By.xpath("//h1");
    private final By homePageTitle2 = By.xpath("//div/h2");
    private final By homePageTitle3 = By.xpath("//form/h2");
    private final By fromPort = By.xpath("//select[@name='fromPort']");
    private final By toPort = By.xpath("//select[@name='toPort']");
    private final By submit = By.xpath("//input[@type='submit']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getTitleText() {
        waitForVisibility(homePageTitle);
        return driver.findElement(homePageTitle).getText();
    }

    public void selectFromPort(String value) {
        waitForVisibility(fromPort);
        new Select(driver.findElement(fromPort)).selectByVisibleText(value);
    }

    public void selectToPort(String value) {
        waitForVisibility(toPort);
        new Select(driver.findElement(toPort)).selectByVisibleText(value);
    }

    public void clickSubmit() {
        waitForClickable(submit);
        driver.findElement(submit).click();
    }
}