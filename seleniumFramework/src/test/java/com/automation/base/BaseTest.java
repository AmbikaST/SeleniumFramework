package com.automation.base;

import java.io.File;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.utility.PropertiesUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver = null;
	protected static WebDriverWait wait = null;
	protected static Logger log;
	
	@BeforeTest
	public void setUpForBeforeTest() {
		log = LogManager.getLogger(BaseTest.class.getName());
	}
	
	@BeforeMethod
	@Parameters("browserName")
	public void setUpBeforeTestMethod(@Optional("Chrome") String browserName) {
		PropertiesUtility propUtility = new PropertiesUtility();
		Properties application = propUtility.loadFile("applicationDataProperties");
		String url = application.getProperty("url");
		launchBrowser(browserName);//"Chrome"
		goToURL(url);
	}
	
	@AfterMethod
	public void tearDownAfterTestMethod() {
		closeBrowser();
	}
	
	public static void launchBrowser(String browserName) {
		switch(browserName) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		}
		log.info(browserName+" browser is opened");
		
	}
	
	public static void goToURL(String url) {
		driver.get(url);
		log.info(url+" is entered");
		
	}
	
	public static String getPageTitle() {
		return driver.getTitle();
	}
	
	public static void enterText(WebElement element,String data,String objectName) {
		if(element.isDisplayed()) {
			clearElement(element,objectName);
			element.sendKeys(data);
			log.info("pass:"+objectName+"is entered to the "+objectName+" field");
		}else {
			log.error("Fail:"+objectName+"element is not displayed");
		}
	}
	
	public static void clearElement(WebElement element, String objectName) {
		if(element.isDisplayed()) {
			element.clear();
			log.info("pass:"+objectName+"element is cleared");
		}else {
			log.error("Fail:"+objectName+"element is not cleared");
		}
	}
	
	public static void clickElement(WebElement element, String objectName) {
		if(element.isDisplayed()) {
			element.click();
			System.out.println("pass:"+objectName+"element is clicked");
		}else {
			System.out.println("Fail:"+objectName+"element is not clicked");
		}
	}
	
	public static void closeBrowser() {
		driver.close();
		System.out.println("current browser is closed");
	}
	
	public static void refreshPage() {
		driver.navigate().refresh();
	}
	
	public static String getTextFromWebElement(WebElement element,String name) {
		if(element.isDisplayed()) {
			return element.getText();
		} else {
			System.out.println(name+"webelement is not displayed");
			return null;
		}
	}
	
	public static void checkBox(String check) {
		driver.findElement(By.id(check)).click();
	}
	
	public static Alert switchToAlert() {
		Alert alert = driver.switchTo().alert();
		System.out.println("Switched to Alert");
		return alert;
	}
	
	public static void acceptAlert(Alert alert) {
		System.out.println("Alert accepted");
		alert.accept();
	}
	
	public static String getAlertText(Alert alert) {
		System.out.println("Extratcing text from the Alert");
		return alert.getText();
	}
	
	public static void dismisAlert() {
		Alert alert = switchToAlert();
		alert.dismiss();
		System.out.println("Alert is closed");
	}
	
	public void moveToElementAction(WebElement element,String objName) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		System.out.println("Cursor moved to the element "+objName);
	}
	
	public void contextClickAction(WebElement element,String objName) {
		Actions action = new Actions(driver);
		action.contextClick(element).build().perform();
		System.out.println("right click performed on web Element:"+objName);
	}
	
	public static void waitUntilElementIsVisible(WebElement ele,String objName) {
		System.out.println("waiting for an web element"+objName+"for its visibility");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public File getScreenshotOfThePage(WebDriver driver2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static String errorMsg(String errorId) {
		WebElement errorMessage  = driver.findElement(By.id(errorId));
		return errorMessage.getText();
	}
	public static void explicitWait(int seconds,String id) {
		wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(id)));
	}
	
	public static void selectFromDropDown(String id,String value) {
		WebElement dropdown = driver.findElement(By.id(id));
		Select select = new Select(dropdown);
		 select.selectByVisibleText(value);		
	}
	
	public static void advertisement(String dialogBox) {
		driver.getWindowHandle();
		WebElement adpopup = driver.findElement(By.id("tryLexDialogX"));
		if(adpopup.isDisplayed()) {
		clickElement(adpopup, "Advertisement pop up");
		}
	}
	
	public static String selectedelement(WebElement dropDown) {
		Select select = new Select(dropDown);
		return select.getFirstSelectedOption().getText();	
	}
	
	public static void logout(String id, String path){
		WebElement userNavDropDown = driver.findElement(By.id(id));
		clickElement(userNavDropDown, "userNav Drop down");
		WebElement logoutelem = driver.findElement(By.xpath(path));
		if(logoutelem.isDisplayed()) {
		clickElement(logoutelem, "Log out ");
		}
	}
	
}
