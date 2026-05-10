package org.demo.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public final class DriverManager {

    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    private DriverManager() {}

    public static void initDriver(String browser, boolean remote) {
        if (threadLocalDriver.get() == null) {
            WebDriver driver;
            try {
                if (remote) {
                    URL gridUrl = new URL("http://localhost:4444");
                    driver = switch (browser.toLowerCase()) {
                        case "chrome" -> {
                            ChromeOptions options = new ChromeOptions();
                            options.addArguments("--headless=new");
                            options.addArguments("--no-sandbox");
                            options.addArguments("--disable-dev-shm-usage");
                            yield new RemoteWebDriver(gridUrl, options);
                        }
                        case "firefox" -> {
                            FirefoxOptions options = new FirefoxOptions();
                            options.addArguments("-headless");
                            yield new RemoteWebDriver(gridUrl, options);
                        }
                        default -> throw new IllegalArgumentException(
                                "Unsupported browser: " + browser);
                    };

                } else {
                    driver = switch (browser.toLowerCase()) {
                        case "chrome" -> {
                            ChromeOptions options = new ChromeOptions();
                            yield new ChromeDriver(options);
                        }
                        case "firefox" -> {
                            FirefoxOptions options = new FirefoxOptions();
                            yield new FirefoxDriver(options);
                        }
                        default -> throw new IllegalArgumentException(
                                "Unsupported browser: " + browser);
                    };
                }
                driver.manage().window().maximize();
                threadLocalDriver.set(driver);

            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid Grid URL", e);
            }
        }
    }

    public static WebDriver getDriver() {
        WebDriver driver = threadLocalDriver.get();
        if (driver == null) {
            throw new IllegalStateException(
                    "Driver is not initialized. Call initDriver() first.");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = threadLocalDriver.get();
        if (driver != null) {
            driver.quit();
            threadLocalDriver.remove();
        }
    }
}