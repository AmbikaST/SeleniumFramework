package com.automation.tests;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.utility.PropertiesUtility;

public class AutomationScripts extends BaseTest{
	@Test
	public static void loginToFireBaseTestScript() {
		log.info("inside the logintoFireBase  Test Script");
		
		PropertiesUtility propUtility = new PropertiesUtility();
		Properties application = propUtility.loadFile("applicationDataProperties");
		String userId = application.getProperty("login.valid.username");
		String password = application.getProperty("login.valid.password");
		
		String expectedTitle ="Selenium";
		
		String actualTitle = getPageTitle();
		assertEquals(expectedTitle, actualTitle);
		//Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2)).ignoring(ElementNotInteractableException.class);
		WebElement userNameElement = driver.findElement(By.id("email_field"));
		//wait.until(ExpectedConditions.visibilityOf(userNameElement));
		waitUntilElementIsVisible(userNameElement, "username field");
		enterText(userNameElement,userId,"username");
		
		WebElement passwordElement = driver.findElement(By.id("password_field"));
		enterText(passwordElement,password,"Password");
		
		WebElement buttonElement = driver.findElement(By.tagName("button"));
		clickElement(buttonElement,"login Button");
		log.info("testscript execution completed");
	}
	
	@Test(enabled = false)
	public static void errorLoginToFireBaseTestScript() {
		String expectedError = "Error: The Password is invalid or the user doesnot have a password";
		PropertiesUtility propUtility = new PropertiesUtility();
		Properties application = propUtility.loadFile("applicationDataProperties");
		String userId = application.getProperty("login.invalid.username");
		String password = application.getProperty("login.invalid.password");
		
		String expectedTitle ="Selenium";

		String actualTitle = getPageTitle();
		assertEquals(expectedTitle, actualTitle);	
		WebElement userNameElement = driver.findElement(By.id("email_field"));
		waitUntilElementIsVisible(userNameElement, "username field");
		enterText(userNameElement,userId,"username");
		
		WebElement passwordElement = driver.findElement(By.id("password_field"));
		enterText(passwordElement,password,"Password");
		
		WebElement buttonElement = driver.findElement(By.tagName("button"));
		clickElement(buttonElement,"login Button");
		Alert alert = switchToAlert();
		String errorMsg = getAlertText(alert);
		acceptAlert(alert);
		assertEquals(expectedError, errorMsg);
		
	}

}
