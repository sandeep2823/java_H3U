package com.h3u.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.h3u.utility.BrowserFactory;
import com.h3u.utility.ConfigDataProvider;
import com.h3u.utility.ExcelDataProvider;
import com.h3u.utility.Helper;

public class BaseClass {
	public WebDriver driver;
	public ConfigDataProvider config;
	public ExcelDataProvider edp;
	public ExtentReports report;
	public ExtentTest logger;

	@BeforeSuite
	public void setupSuite() {

		Reporter.log("Setting up reports and test is getting started", true);
		edp = new ExcelDataProvider();
		config = new ConfigDataProvider();
		ExtentHtmlReporter extent = new ExtentHtmlReporter(
				new File(System.getProperty("user.dir") + "/Reports/H3U/H3U_" + Helper.getCurrentDateTime() + ".html"));
		report = new ExtentReports();
		report.attachReporter(extent);

		Reporter.log("Setting done - Test can be started", true);
	}

	@BeforeClass
	public void setup() {
		Reporter.log("Browser is starting and getting application ready", true);
		driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getUrl());
		Reporter.log("Browser and Application is up and running", true);
	}

	@AfterClass
	public void tearDown() {
		BrowserFactory.quitBrowser(driver);
	}

	@AfterMethod
	public void tearDownScreenshot(ITestResult result) throws IOException {
		Reporter.log("test is about to complete", true);
		if (result.getStatus() == ITestResult.FAILURE) {
			Helper.captureScreenshot(driver);
			logger.fail("Test Failed",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.pass("Test Passed",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		report.flush();
		Reporter.log("Test Completed >>> Reports Generated", true);
	}
}
