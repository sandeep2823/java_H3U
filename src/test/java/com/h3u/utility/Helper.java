package com.h3u.utility;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Helper {

	public static String captureScreenshot(WebDriver driver) {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir")+"/Screenshots/H3U/H3U_"+getCurrentDateTime()+".png";
		try {
			FileHandler.copy(src, new File(screenshotPath));
		} catch (Exception e) {
			System.out.println("Unable to take screenshot >> " + e.getMessage());
		}
		
		return screenshotPath;
	}

	public static String getCurrentDateTime() {
		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currantDate = new Date();
		return customFormat.format(currantDate);
	}
}
