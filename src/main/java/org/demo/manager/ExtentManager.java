package org.demo.manager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentManager {

    private static ExtentReports extentReports;

    private ExtentManager() {}

    public static ExtentReports getInstance() {

        if (extentReports == null) {

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(
                            System.getProperty("user.dir")
                                    + "/reports/ExtentReport.html"
                    );

            sparkReporter.config().setReportName("Automation Test Report");
            sparkReporter.config().setDocumentTitle("Execution Results");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Tester", "Reynold");
            extentReports.setSystemInfo("Environment", "QA");
        }

        return extentReports;
    }
}
