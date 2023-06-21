package com.automation.tests;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScreenshotInSeleniumTest {
	public static void takescreenshot(WebDriver driver,String filepath) throws IOException {
		TakesScreenshot screenCapture= (TakesScreenshot)driver;
		File srcFile= screenCapture.getScreenshotAs(OutputType.FILE);
		File destFile=new File(filepath);
		FileUtils.copyFile(srcFile, destFile);
		
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		String expectedTitle="Selenium";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		System.out.println("chrome browser opened");
		driver.get("https://qa-tekarch.firebaseapp.com/");
		System.out.println("url entered");
		String actualTitle=driver.getTitle();
		if(expectedTitle.equals(actualTitle)) {
			System.out.println("title matched");
		}
		else {
			System.out.println("title is not matched");
		}
		Thread.sleep(4000);
		
		WebElement label=driver.findElement(By.tagName("h3"));
		System.out.println("text of the login label=="+label.getText());
		
		By userName=By.id("email_field");
		WebElement userNameElement= driver.findElement(userName);
		
		if(userNameElement.isDisplayed()) {
		userNameElement.clear();
		userNameElement.sendKeys("admin123@gmail.com");
		System.out.println("username is entered to the username filed");
		}
		else {
			System.out.println("username element is not displayed");
		}
		//password_field
		By password=By.id("password_field");
		WebElement passwordElement= driver.findElement(password);
		String value=passwordElement.getAttribute("type");
		System.out.println("type value =="+value);
		if(passwordElement.isDisplayed()) {
		passwordElement.clear();
		passwordElement.sendKeys("admin123");
		System.out.println("pawword is entered to the password filed");
		}
		else {
			System.out.println("password element is not displayed");
		}
		By button= By.tagName("button");
		WebElement buttonElement= driver.findElement(button);
		if(buttonElement.isDisplayed()) {
			buttonElement.click();
			System.out.println("login button is clicked");
			}
			else {
				System.out.println("button element is not displayed");
			}
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		if(driver.getTitle().equals("tekarch")) {
			System.out.println("test script passed");
		}
		else{
			System.out.println("testscript failed");
			// testcase name+ date and time
			String path=System.getProperty("user.dir")+"/screenshots/image2.png";
			takescreenshot(driver, path);
		}
		driver.close();
		System.out.println("testscript exxecution completed");
		
		
	}

}