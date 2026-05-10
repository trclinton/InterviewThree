package tests;

import base.Base;
import org.demo.listeners.TestListener;
import org.demo.manager.DriverManager;
import org.demo.manager.ExtentTestManager;
import org.demo.pages.FlightPage;
import org.demo.pages.HomePage;
import org.demo.pages.ReservationPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class ReservationTest extends Base {

    @Test
    public void reserveTest(){
        ExtentTestManager.getTest().info("Starting Reservation Page Test");
        HomePage homePage = new HomePage(DriverManager.getDriver());
        FlightPage flightPage = new FlightPage(DriverManager.getDriver());
        ReservationPage reservationPage = new ReservationPage(DriverManager.getDriver());
        homePage.selectFromPort("Boston");
        homePage.selectToPort("London");
        homePage.clickSubmit();
        flightPage.selectFlight("United Airlines");
        softAssert.assertEquals(reservationPage.getReservationPageTitle(), "Your flight from TLV to SFO has been reserved.", "Invalid Reservation Page Header");
        ExtentTestManager.getTest().pass("Reservation Page Header validated successfully");
    }
}