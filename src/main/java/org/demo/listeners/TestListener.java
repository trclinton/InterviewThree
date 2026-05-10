package org.demo.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.demo.manager.ExtentManager;
import org.demo.manager.ExtentTestManager;
import org.demo.utils.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private final ExtentReports extentReports = ExtentManager.getInstance();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReports.createTest(result.getMethod().getMethodName());
        ExtentTestManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String screenshotPath = ScreenshotUtil.captureScreenshot(result.getMethod().getMethodName());
        ExtentTestManager.getTest().fail(result.getThrowable()).addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
        ExtentTestManager.unload();
    }
}
