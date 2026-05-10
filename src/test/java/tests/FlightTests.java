package tests;

import base.Base;
import org.demo.listeners.TestListener;
import org.demo.manager.DriverManager;
import org.demo.manager.ExtentTestManager;
import org.demo.pages.FlightPage;
import org.demo.pages.HomePage;
import org.demo.pages.ReservationPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners(TestListener.class)
public class FlightTests extends Base {

    @Test
    public void homePageTest() {

        ExtentTestManager.getTest().info("Starting Home Page Test");
        HomePage homePage = new HomePage(DriverManager.getDriver());
        String actual = homePage.getTitleText();
        softAssert.assertEquals(actual,
                "Welcome to the Simple Travel Agency!",
                "Incorrect Header found"
        );
        ExtentTestManager.getTest().pass("Home Page Header validated successfully");
    }

    @Test
    public void flightPageTest(){
        ExtentTestManager.getTest().info("Starting Flight Page Test");
        HomePage homePage = new HomePage(DriverManager.getDriver());
        FlightPage flightPage = new FlightPage(DriverManager.getDriver());
        homePage.selectFromPort("Boston");
        homePage.selectToPort("London");
        homePage.clickSubmit();
        softAssert.assertEquals(flightPage.getPageTitle(), "Flights from Boston to London:", "Invalid header found in Flight Page");
        ExtentTestManager.getTest().pass("Flight page Header validated successfully");
    }
}
