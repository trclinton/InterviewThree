package org.demo.utils;

import org.demo.manager.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.*;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtil {

    private ScreenshotUtil() {}

    public static String captureScreenshot(String testName) {

        File src = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String destination = System.getProperty("user.dir")
                        + "/screenshots/"
                        + testName
                        + "_"
                        + timestamp
                        + ".png";

        try {
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/screenshots"));
            Files.copy(src.toPath(), Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to capture screenshot", e);
        }

        return destination;
    }
}
