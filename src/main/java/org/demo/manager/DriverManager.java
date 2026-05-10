package org.demo.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverManager {

    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    private WebDriverManager(){}

    public static void initLocalDriver(String browser){
        if(threadLocalDriver.get() == null){
            WebDriver driver = switch(browser.toLowerCase()){
                case "chrome" -> {
                    ChromeOptions options = new ChromeOptions();
                    yield new ChromeDriver(options);
                }
                case "firefox" -> {
                    FirefoxOptions options = new FirefoxOptions();
                    yield new FirefoxDriver(options);
                }
                default -> throw new IllegalStateException("Unexpected value: " + browser.toLowerCase());
            };

            driver.manage().window().maximize();

            threadLocalDriver.set(driver);
        }
    }

    public static WebDriver getDriver(){

        WebDriver driver = threadLocalDriver.get();
        if (driver == null) {
            throw new IllegalStateException(
                    "Driver is not initialized. Call initLocalDriver() first.");
        }
        return driver;
    }

    public static void quitDriver(){
        WebDriver driver = threadLocalDriver.get();
        if (driver != null) {
            driver.quit();
            threadLocalDriver.remove();
        }
    }
}