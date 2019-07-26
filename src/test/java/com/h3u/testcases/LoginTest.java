package com.h3u.testcases;

import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;
import com.h3u.pages.BaseClass;
import com.h3u.pages.LoginPage;

public class LoginTest extends BaseClass {

	@Test
	public void loginApp() {
		
		logger = report.createTest("Login to H3U");

		LoginPage loginpage = PageFactory.initElements(driver, LoginPage.class);
		logger.info("Starting the appliction");
		
		loginpage.loginToApp(edp.getStringData("Login", 0, 0), edp.getStringData("Login", 0, 1));
		logger.pass("Login Successfully");
	}
}
