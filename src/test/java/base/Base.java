package base;

import org.demo.manager.DriverManager;
import org.demo.manager.ExtentTestManager;
import org.demo.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class Base {

    protected SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        String browser = System.getProperty("browser", System.getenv().getOrDefault("browser", "chrome"));
        boolean remote = Boolean.parseBoolean(System.getProperty("isRemote", System.getenv().getOrDefault("isRemote", "false")));
        DriverManager.initDriver(browser, remote);
        DriverManager.getDriver().get("https://blazedemo.com/");
        softAssert = new SoftAssert();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            softAssert.assertAll();
        } finally {
            DriverManager.quitDriver();
        }
    }
}