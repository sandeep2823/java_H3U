package com.h3u.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(name = "loginid")
	WebElement username;

	@FindBy(name = "userpassword")
	WebElement password;

	@FindBy(xpath = "//*[@id=\"loginrow\"]//form[1]/div[3]/button")
	WebElement loginButton;

	public void loginToApp(String uname, String pwd) {
		username.sendKeys(uname);
		password.sendKeys(pwd);
		loginButton.click();
	}

}
