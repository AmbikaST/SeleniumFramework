package com.automation.tests;

import static org.testng.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.utility.Constants;
import com.automation.utility.PropertiesUtility;



public class SfdcAuromationScripts extends BaseTest{
	
	@Test
	//Test ID TC01
	public static void sfdcEmptyPwd() {
		PropertiesUtility propUtility = new PropertiesUtility();
		Properties application = propUtility.loadFile("applicationDataProperties");
		String userId = application.getProperty("login.valid.username");
		String password = application.getProperty("login.invalid.pwd.empty");
		String expectedMsg = propUtility.getPropertyValue("login.empty.password.msg");
		WebElement userNameElement = driver.findElement(By.id("username"));
		waitUntilElementIsVisible(userNameElement, "username field");
		enterText(userNameElement,userId,"username");
		
		WebElement passwordElement = driver.findElement(By.id("password"));
		enterText(passwordElement,password,"Password");
		WebElement buttonElement = driver.findElement(By.id("Login"));
		clickElement(buttonElement,"login Button");
		String actualMsg = errorMsg("error");
		assertEquals(expectedMsg, actualMsg);
		log.info("testscript TC01 execution completed");
		}
	
	@Test
	//Test ID	Login To SalesForce -2
	public static void sfdcLoginScript() {
		log.info("inside the SFDC Test Script");
		
		PropertiesUtility propUtility = new PropertiesUtility();
		Properties application = propUtility.loadFile("applicationDataProperties");
		String userId = propUtility.getPropertyValue("login.valid.username");
		String password = application.getProperty("login.valid.password");
		
		String expectedTitle = propUtility.getPropertyValue("login.title");
		
		String actualTitle = getPageTitle();
		assertEquals(expectedTitle, actualTitle);
		WebElement userNameElement = driver.findElement(By.id("username"));
		waitUntilElementIsVisible(userNameElement, "username field");
		enterText(userNameElement,userId,"username");
		
		WebElement passwordElement = driver.findElement(By.id("password"));
		enterText(passwordElement,password,"Password");
		
		WebElement buttonElement = driver.findElement(By.id("Login"));
		clickElement(buttonElement,"login Button");
		log.info("testscript execution completed");
	}
	
	//Test ID	Check RemeberMe - 3
	@Test
	public static void sfdcRememberCheck() throws InterruptedException {
		log.info("inside the SFDC Test Script");
		
		PropertiesUtility propUtility = new PropertiesUtility();
		Properties application = propUtility.loadFile("applicationDataProperties");
		String userId = propUtility.getPropertyValue("login.valid.username");
		String password = application.getProperty("login.valid.password");
		
		WebElement userNameElement = driver.findElement(By.id("username"));
		waitUntilElementIsVisible(userNameElement, "username field");
		enterText(userNameElement,userId,"username");
		
		WebElement passwordElement = driver.findElement(By.id("password"));
		enterText(passwordElement,password,"Password");
		
		WebElement checkele = driver.findElement(By.id("rememberUn"));
		clickElement(checkele, "remember username");
		Thread.sleep(2000);
		WebElement buttonElement = driver.findElement(By.id("Login"));
		clickElement(buttonElement,"login Button");
		
		WebElement navButton = driver.findElement(By.id("userNavButton"));
		clickElement(navButton,"user navigation");
		WebElement usernavelement = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]"));
		clickElement(usernavelement,"user nav drop down");
		Thread.sleep(5000);
		WebElement usernameele = driver.findElement(By.id("idcard-identity"));
		String rememeberUsername = getTextFromWebElement(usernameele, "idcard-identity");
		Assert.assertEquals(userId, rememeberUsername);
		log.info("testscript TC=03 execution completed");		
	}
	
	//Test ID	Forgot Password- 4 A
	@Test
	public static void sfdcForgotPwd() {
		PropertiesUtility propUtility = new PropertiesUtility();
		Properties application = propUtility.loadFile("applicationDataProperties");
		String userId = application.getProperty("login.valid.username");
		
		WebElement forgotpwd = driver.findElement(By.id("forgot_password_link"));
		clickElement(forgotpwd,"forgot Password link");
		WebElement usernameele = driver.findElement(By.id("un"));
		enterText(usernameele, userId, "user name field");
		WebElement clickele = driver.findElement(By.id("continue"));
		clickElement(clickele, "continue button");
		log.info("testscript TC-04A execution completed");
		
	}
	
	// Test ID	Forgot Password- 4 B
	@Test
	public static void sfdcInvalidLogin() {
		PropertiesUtility propUtility = new PropertiesUtility();
		Properties application = propUtility.loadFile("applicationDataProperties");
		String userId = propUtility.getPropertyValue("login.invalid.username");
		String password = application.getProperty("login.invalid.password");
		
		String expectedMsg = propUtility.getPropertyValue("login.invalid.password.msg");
		
		WebElement userNameElement = driver.findElement(By.id("username"));
		waitUntilElementIsVisible(userNameElement, "username field");
		enterText(userNameElement,userId,"username");
		
		WebElement passwordElement = driver.findElement(By.id("password"));
		enterText(passwordElement,password,"Password");
		
		WebElement buttonElement = driver.findElement(By.id("Login"));
		clickElement(buttonElement,"login Button");
		
		String actualMsg = errorMsg("error");
		assertEquals(expectedMsg, actualMsg);
		log.info("testscript TC04B execution completed");
	}
	
	//Test ID	TC05
	@Test
	public static void sfdcUserMenuDropDown() {
		PropertiesUtility propUtility = new PropertiesUtility();
		Properties application = propUtility.loadFile("applicationDataProperties");
		String userId = propUtility.getPropertyValue("login.valid.username");
		String password = application.getProperty("login.valid.password");
		
		WebElement userNameElement = driver.findElement(By.id("username"));
		waitUntilElementIsVisible(userNameElement, "username field");
		enterText(userNameElement,userId,"username");
		
		WebElement passwordElement = driver.findElement(By.id("password"));
		enterText(passwordElement,password,"Password");
		
		WebElement buttonElement = driver.findElement(By.id("Login"));
		clickElement(buttonElement,"login Button");
		
		WebElement userNavDropDown = driver.findElement(By.id("userNavButton"));
		clickElement(userNavDropDown, "userNav Drop down");
		
		List<WebElement> list = driver.findElements(By.xpath("//div[@id='userNav-menuItems']//a"));
		Iterator<WebElement> str = list.iterator();
		String expValues[] = {"My Profile", "My Settings", "Developer Console", "Switch to Lightning Experience",
	    "Logout" };
		int i =0;
		while(str.hasNext()) {
			 String actualValues = str.next().getText();
			 log.info("ExpectedValues-->"+expValues[i]+" ActualValues-->"+actualValues);
			 assertEquals(expValues[i], actualValues);
			 i++;
		}
	}
	
	//Test Id TC06
	@Test
	public static void sfdcMyProfile() throws InterruptedException{

		PropertiesUtility propUtility = new PropertiesUtility();
		Properties application = propUtility.loadFile("applicationDataProperties");
		String userId = propUtility.getPropertyValue("login.valid.username");
		String password = application.getProperty("login.valid.password");
		
		WebElement userNameElement = driver.findElement(By.id("username"));
		waitUntilElementIsVisible(userNameElement, "username field");
		enterText(userNameElement,userId,"username");
		
		WebElement passwordElement = driver.findElement(By.id("password"));
		enterText(passwordElement,password,"Password");
		
		WebElement buttonElement = driver.findElement(By.id("Login"));
		clickElement(buttonElement,"login Button");
		
		WebElement userNavDropDown = driver.findElement(By.id("userNavButton"));
		String actname = userNavDropDown.getText();
		clickElement(userNavDropDown, "userNav Drop down");
		
		List<WebElement> list = driver.findElements(By.xpath("//div[@id='userNav-menuItems']//a"));
		Iterator<WebElement> str = list.iterator();
		String expValues[] = {"My Profile", "My Settings", "Developer Console", "Switch to Lightning Experience",
	    "Logout" };
		int i =0;
		while(str.hasNext()) {
			//refreshPage();
			 String actualValues = str.next().getText();
			 assertEquals(expValues[i], actualValues);
			 
			 if(actualValues.equalsIgnoreCase("My Profile")) {
				 WebElement myprofileEle = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[1]"));
				 clickElement(myprofileEle, "MyProfile Info");
				 log.info("coming inside myprofile");
				 Thread.sleep(5000);
				 String editprofileTitle = "User: "+actname+" ~ Salesforce - Developer Edition";
				 WebElement edit = driver.findElement(By.xpath("//*[@id=\"chatterTab\"]/div[2]/div[2]/div[1]/h3/div/div/a/img"));
				 clickElement(edit, "Edit profile ");
					
				 Assert.assertEquals(editprofileTitle, getPageTitle());
				 Thread.sleep(3000);
					
				 WebElement aboutFrame = driver.findElement(By.id("contactInfoContentId"));
				 driver.switchTo().frame(aboutFrame);
				 WebElement about = driver.findElement(By.xpath("//*[@id=\"aboutTab\"]/a"));
				 clickElement(about, "About Tab ");
				
				 WebElement lastName = driver.findElement(By.id("lastName"));
				 enterText(lastName, "AB", "Last Name ");
					
				 WebElement save = driver.findElement(By.xpath("//*[@id=\"TabPanel\"]/div/div[2]/form/div/input[1]"));
				 clickElement(save, "Save All Button ");
				 
				 Thread.sleep(3000);
				 //WebElement mode = driver.findElement(By.xpath("//*[@id=\"photoSection\"]/span[2]/img[1]"));
				 
				 WebElement post = driver.findElement(By.xpath("//*[@id=\"publisherAttachTextPost\"]"));
				 clickElement(post, "Post link");
				 
				 driver.switchTo().frame(0);
				 WebElement postText = driver.findElement(By.xpath("/html/body"));
				 enterText(postText, "Hi", "Text");
				 
				 driver.switchTo().defaultContent();
				 WebElement shareEleme = driver.findElement(By.id("publishersharebutton"));
				 clickElement(shareEleme, "Share Button");
				 
				 WebElement FileEleme = driver.findElement(By.xpath("//*[@id=\"publisherAttachContentPost\"]"));
				 clickElement(FileEleme, "File link ");
				 
				 WebElement uploadFile = driver.findElement(By.xpath("//*[@id=\"chatterUploadFileAction\"]"));
				 clickElement(uploadFile, "Upload file from computer ");
				 
				 
				 driver.findElement(By.id("chatterFile")).sendKeys("C:\\Users\\t430\\Pictures\\Saved Pictures\\cat.jpg");
				 
				 Thread.sleep(3000);
				 WebElement uploadShareBtn = driver.findElement(By.id("publishersharebutton"));
				 clickElement(uploadShareBtn, "Share Button");
				 addPhoto();
				 
				 break;
			 }
			 i++;
		}
	}
	
	public static void addPhoto() throws InterruptedException {
		 WebElement addPhotoele = driver.findElement(By.xpath("//*[@id=\"listItem-addPhoto\"]/a"));
		 clickElement(addPhotoele, "Add your Photo");
		 Thread.sleep(5000);
		 driver.switchTo().frame("uploadPhotoContentId");
		 log.info("went inside the frame");
		 driver.findElement(By.xpath("//*[@id=\"j_id0:uploadFileForm:uploadInputFile\"]")).sendKeys("C:\\Users\\t430\\Pictures\\Saved Pictures\\Natural_img.png");
		 log.info("Image is choosed");
		 Thread.sleep(3000);
		// driver.switchTo().defaultContent();
		 WebElement savebtn = driver.findElement(By.xpath("//*[@id=\"j_id0:uploadFileForm:uploadBtn\"]"));
		 clickElement(savebtn, "save button");
		 Thread.sleep(4000);
		 WebElement saveele = driver.findElement(By.xpath("//*[@id=\"j_id0:j_id7:save\"]"));
		 clickElement(saveele, "Save ");
		 Thread.sleep(3000);
	}
	
	//Test Id TC07
	@Test
	public static void sfdcMySettings() throws InterruptedException{
	log.info("inside the SFDC Test Script");
		
		PropertiesUtility propUtility = new PropertiesUtility();
		Properties application = propUtility.loadFile("applicationDataProperties");
		String userId = propUtility.getPropertyValue("login.valid.username");
		String password = application.getProperty("login.valid.password");
		
		String expectedTitle = propUtility.getPropertyValue("login.title");
		
		String actualTitle = getPageTitle();
		assertEquals(expectedTitle, actualTitle);
		WebElement userNameElement = driver.findElement(By.id("username"));
		waitUntilElementIsVisible(userNameElement, "username field");
		enterText(userNameElement,userId,"username");
		
		WebElement passwordElement = driver.findElement(By.id("password"));
		enterText(passwordElement,password,"Password");
		
		WebElement buttonElement = driver.findElement(By.id("Login"));
		clickElement(buttonElement,"login Button");
		
		WebElement userNavDropDown = driver.findElement(By.id("userNavButton"));
		clickElement(userNavDropDown, "userNav Drop down");
		
		List<WebElement> list = driver.findElements(By.xpath("//div[@id='userNav-menuItems']//a"));
		Iterator<WebElement> str = list.iterator();
		String expValues[] = {"My Profile", "My Settings", "Developer Console", "Switch to Lightning Experience",
	    "Logout" };
		int i =0;
		while(str.hasNext()) {
			 String actualValues = str.next().getText();
			 log.info("ExpectedValues-->"+expValues[i]+" ActualValues-->"+actualValues);
			 assertEquals(expValues[i], actualValues);
			 i++;
		}
		WebElement mysettingele = driver.findElement(By.xpath("//div[@id='userNav-menuItems']//a[2]"));
		clickElement(mysettingele, "My Settings");
		Thread.sleep(2000);
		mySettings();//mysettings
		refreshPage();
		displayAndLayout();//DisplayAndLayout
		refreshPage();
		emailSettings();//Email
		
		log.info("TestScrip TC-07 execution is completed");
	}
	
	private static void mySettings() throws InterruptedException{
		WebElement PersonalInfoele = driver.findElement(By.xpath("//*[@id=\"PersonalInfo\"]/a"));
		clickElement(PersonalInfoele, "PersonalInfo");
		WebElement loginHisele = driver.findElement(By.id("LoginHistory_font"));
		clickElement(loginHisele, "Login History font");
		WebElement downloadhistory = driver.findElement(By.xpath("//*[@id=\"RelatedUserLoginHistoryList_body\"]/div/a"));
		clickElement(downloadhistory, "Download login history");
	}
	
	private static void displayAndLayout() {
		WebElement displayLayout = driver.findElement(By.id("DisplayAndLayout"));
		clickElement(displayLayout, "Display Layout");
		WebElement customizeele = driver.findElement(By.id("CustomizeTabs_font"));
		clickElement(customizeele, "customize Tab font");
		selectFromDropDown("p4", "Salesforce Chatter");
		explicitWait(30, "p4");
		selectFromDropDown("duel_select_0", "Reports");
		WebElement csselement = driver.findElement(By.cssSelector("#duel_select_0_right"));
		clickElement(csselement, "Arrow button");
		WebElement saveelem = driver.findElement(By.name("save"));
		clickElement(saveelem, "Save");
	}
	
	private static void emailSettings() {
		WebElement emailelem = driver.findElement(By.id("EmailSetup"));
		clickElement(emailelem, "Email");
		WebElement emailset = driver.findElement(By.id("EmailSettings_font"));
		clickElement(emailset, "Email settings");
		WebElement sendername = driver.findElement(By.id("sender_name"));
		String senderKey = Constants.USERNAME;
		log.info("senderKey -->"+senderKey);
		enterText(sendername,senderKey , "sender name");
		WebElement senderEmail = driver.findElement(By.id("sender_email"));
		String emailKey = Constants.EMAIL;
		enterText(senderEmail,emailKey , "sender Email");
		WebElement radioelement = driver.findElement(By.id("auto_bcc1"));
		clickElement(radioelement, "Yes Radio button");
		WebElement saveBtn = driver.findElement(By.name("save"));
		clickElement(saveBtn, "Save");
		
	}
	
	private static void loginPage() {
		PropertiesUtility propUtility = new PropertiesUtility();
		Properties application = propUtility.loadFile("applicationDataProperties");
		String userId = propUtility.getPropertyValue("login.valid.username");
		String password = application.getProperty("login.valid.password");
		
		String expectedTitle = propUtility.getPropertyValue("login.title");
		
		String actualTitle = getPageTitle();
		//assertEquals(expectedTitle, actualTitle);
		WebElement userNameElement = driver.findElement(By.id("username"));
		waitUntilElementIsVisible(userNameElement, "username field");
		enterText(userNameElement,userId,"username");
		
		WebElement passwordElement = driver.findElement(By.id("password"));
		enterText(passwordElement,password,"Password");
		
		WebElement buttonElement = driver.findElement(By.id("Login"));
		clickElement(buttonElement,"login Button");
	}
	//Test ID	TC08
	@Test
	public static void developerConsole() throws InterruptedException{
		loginPage();
		
		WebElement userNavDropDown = driver.findElement(By.id("userNavButton"));
		clickElement(userNavDropDown, "userNav Drop down");
		List<WebElement> list = driver.findElements(By.xpath("//div[@id='userNav-menuItems']//a"));
		Iterator<WebElement> str = list.iterator();
		String expValues[] = {"My Profile", "My Settings", "Developer Console", "Switch to Lightning Experience",
	    "Logout" };
		int i =0;
		while(str.hasNext()) {
			 String actualValues = str.next().getText();
			 log.info("ExpectedValues-->"+expValues[i]+" ActualValues-->"+actualValues);
			 assertEquals(expValues[i], actualValues);
			 i++;
		}
		String parentwindow = driver.getWindowHandle();
		WebElement developelem = driver.findElement(By.xpath("//div[@id='userNav-menuItems']//a[3]"));
		clickElement(developelem, "Developer console");
		Thread.sleep(4000);
		Set<String> allwindowHandle = driver.getWindowHandles();
		for(String handle: allwindowHandle) {
			if(!parentwindow.equals(handle)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}
		
	}
	
	//Test ID TC09
	@Test
	public static void sfdcLogout() {
		loginPage();
		String expectedUrl = Constants.LOGOUTURL;
		WebElement userNavDropDown = driver.findElement(By.id("userNavButton"));
		clickElement(userNavDropDown, "userNav Drop down");
		List<WebElement> list = driver.findElements(By.xpath("//div[@id='userNav-menuItems']//a"));
		Iterator<WebElement> str = list.iterator();
		String expValues[] = {"My Profile", "My Settings", "Developer Console", "Switch to Lightning Experience",
	    "Logout" };
		int i =0;
		while(str.hasNext()) {
			 String actualValues = str.next().getText();
			 assertEquals(expValues[i], actualValues);
			 i++;
		}
		WebElement logoutelem = driver.findElement(By.xpath("//div[@id='userNav-menuItems']//a[5]"));
		clickElement(logoutelem, "Log out ");
		String actualurl = driver.getCurrentUrl();
		Assert.assertEquals(expectedUrl, actualurl);
		
		log.info("Testscripts TC09 execution completed");
	}
	
	//Test ID TC10
	// need to check 
	@Test
	public static void sfdcAccount() throws InterruptedException{
		loginPage();
		PropertiesUtility propUtility = new PropertiesUtility();
		Properties application = propUtility.loadFile("applicationDataProperties");
		String acountName = application.getProperty("account.name");
		WebElement accountelem = driver.findElement(By.xpath("//*[@id=\"Account_Tab\"]"));
		clickElement(accountelem, "Account Tab ");
		Thread.sleep(2000);
		driver.getWindowHandle();
		WebElement adpopup = driver.findElement(By.id("tryLexDialogX"));
		clickElement(adpopup, "Advertisement pop up");
		Thread.sleep(3000);
		String expectedName = driver.findElement(By.id("userNavLabel")).getText();
		String actualName = driver.findElement(By.xpath("//*[@id=\"mru005Hs00000C7pYS\"]/a/span")).getText();
		Assert.assertEquals(expectedName, actualName);
		WebElement newelem = driver.findElement(By.name("new"));
		clickElement(newelem, "New Button ");
		WebElement accName = driver.findElement(By.id("acc2"));
		enterText(accName,acountName, "Account Name ");
		selectFromDropDown("acc6", "Technology Partner");
		selectFromDropDown("00NHs00000Dlofp", "High");
		WebElement saveele = driver.findElement(By.name("save"));
		clickElement(saveele, "Save Button ");
		log.info("TestScript TC-10 execution is completed");
	}
	
	//Test ID TC-11
	@Test
	public static void sfdcAccountView() throws InterruptedException {
		log.info("TestScript TC-11 execution started");
		loginPage();
		WebElement accountelem = driver.findElement(By.xpath("//*[@id=\"Account_Tab\"]"));
		clickElement(accountelem, "Account Tab ");
		Thread.sleep(4000);
		WebElement adpopup = driver.findElement(By.id("tryLexDialogX"));
		clickElement(adpopup, "Advertisement pop up");
		Thread.sleep(3000);
		String expectedName = driver.findElement(By.id("userNavLabel")).getText();
		String actualName = driver.findElement(By.xpath("//*[@id=\"mru005Hs00000C7pYS\"]/a/span")).getText();
		Assert.assertEquals(expectedName, actualName);
		WebElement createelem = driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]"));
		clickElement(createelem, "Create new view link");
		WebElement viewelem = driver.findElement(By.id("fname"));
		String viewName = Constants.VIEWNAME;
		enterText(viewelem, viewName, "View Name");
		WebElement viewUnique = driver.findElement(By.id("devname"));
		String uniqueName = Constants.VIEWUNIQUE;
		enterText(viewUnique, uniqueName, "View unique Name");
		WebElement save = driver.findElement(By.name("save"));
		clickElement(save, "Save Button ");
		log.info("TestScript TC-11 execution completed");
	}
	
	//Test ID TC-12
	@Test
	public static void sfdcEditView() throws InterruptedException{
		loginPage();
		WebElement accountelem = driver.findElement(By.xpath("//*[@id=\"Account_Tab\"]"));
		clickElement(accountelem, "Account Tab ");
		Thread.sleep(4000);
		WebElement adpopup = driver.findElement(By.id("tryLexDialogX"));
		clickElement(adpopup, "Advertisement pop up");
		Thread.sleep(3000);
		String expectedName = driver.findElement(By.id("userNavLabel")).getText();
		String actualName = driver.findElement(By.xpath("//*[@id=\"mru005Hs00000C7pYS\"]/a/span")).getText();
		Assert.assertEquals(expectedName, actualName);
		Thread.sleep(3000);
		String viewName = Constants.VIEWNAME;
		WebElement viewelem = driver.findElement(By.id("fcf"));
		Select select = new Select(viewelem);
		WebElement element = select.getFirstSelectedOption();
		String selectedOpt = element.getText();
		Assert.assertEquals(viewName, selectedOpt);
		WebElement editelem = driver.findElement(By.linkText("Edit"));
		clickElement(editelem, "Edit link ");
		WebElement viewelement = driver.findElement(By.id("fname"));
		String newviewName = Constants.NEWVIEWNAME;
		enterText(viewelement, newviewName, "New View Name ");
		selectFromDropDown("fcol1", "Account Name");
		selectFromDropDown("fop1", "contains");
		WebElement valueelem = driver.findElement(By.id("fval1"));
		enterText(valueelem, "a", "Value ");
		WebElement save = driver.findElement(By.name("save"));
		clickElement(save, "Save Button ");
		Thread.sleep(3000);
		WebElement viewele = driver.findElement(By.id("00BHs00000Cife9_listSelect"));
		Select selectele = new Select(viewele);
		WebElement webelement = selectele.getFirstSelectedOption();
		String selectOpt = webelement.getText();
		Assert.assertEquals(newviewName, selectOpt);
		log.info("TestScript TC-12 execution completed");
	}
	
	//Test ID TC-13
	@Test
	public static void sfdcMergeAccounts() throws InterruptedException {
		loginPage();
		WebElement accountelem = driver.findElement(By.xpath("//*[@id=\"Account_Tab\"]"));
		clickElement(accountelem, "Account Tab ");
		Thread.sleep(4000);
		WebElement adpopup = driver.findElement(By.id("tryLexDialogX"));
		if(adpopup.isDisplayed()) {
		clickElement(adpopup, "Advertisement pop up");
		}
		Thread.sleep(3000);
		String expectedName = driver.findElement(By.id("userNavLabel")).getText();
		String actualName = driver.findElement(By.xpath("//*[@id=\"mru005Hs00000C7pYS\"]/a/span")).getText();
		Assert.assertEquals(expectedName, actualName);
		Thread.sleep(3000);
		WebElement mergeelement = driver.findElement(By.linkText("Merge Accounts"));
		clickElement(mergeelement, "Merge Accounts link ");
		
		WebElement srchele = driver.findElement(By.id("srch"));
		enterText(srchele, "Tekarch", "Search box ");
		
		WebElement fndBtn = driver.findElement(By.name("srchbutton"));
		clickElement(fndBtn, "Find Button ");
		
		WebElement checkbox = driver.findElement(By.id("cid0"));
		if(!checkbox.isSelected()) {
		clickElement(checkbox, "First Check box ");
		}
		
		WebElement checkbox2 = driver.findElement(By.id("cid1"));
		if(!checkbox2.isSelected()) {
		clickElement(checkbox2, "second check box ");
		}
		
		WebElement nextelem = driver.findElement(By.name("goNext"));
		clickElement(nextelem, "Next button ");
		WebElement mergebtn = driver.findElement(By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[5]/div/input[2]"));
		clickElement(mergebtn, "Merge Account button ");
		
		String expectedAlertmsg = "These records will be merged into one record using the selected values. Merging can't be undone. Proceed with the record merge?";
		Alert alert = switchToAlert();
		String Actualalertmsg = getAlertText(alert);
		Assert.assertEquals(expectedAlertmsg, Actualalertmsg);
		acceptAlert(alert);
		Thread.sleep(5000);
		
		log.info("TestSctipt TC-13 is executed successfully");
	}
	
	//Test ID TC-14
	@Test
	public static void sfdcCreateAccount() throws InterruptedException{
		loginPage();
		WebElement accountelem = driver.findElement(By.xpath("//*[@id=\"Account_Tab\"]"));
		clickElement(accountelem, "Account Tab ");
		Thread.sleep(4000);
		WebElement adpopup = driver.findElement(By.id("tryLexDialogX"));
		clickElement(adpopup, "Advertisement pop up");
		Thread.sleep(3000);
		String expectedName = driver.findElement(By.id("userNavLabel")).getText();
		String actualName = driver.findElement(By.xpath("//*[@id=\"mru005Hs00000C7pYS\"]/a/span")).getText();
		Assert.assertEquals(expectedName, actualName);
		Thread.sleep(3000);
		
		WebElement acctdaysele = driver.findElement(By.linkText("Accounts with last activity > 30 days"));
		clickElement(acctdaysele, "Accounts with last 30 days ");
		
		log.info("title -->"+getPageTitle());
		Thread.sleep(3000);
		DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime ld = LocalDateTime.now();

		WebElement todayelem = driver.findElement(By.xpath("//*[@id=\"ext-comp-1042\"]"));
		enterText(todayelem, df.format(ld), "Today's date");
		
		WebElement toDate = driver.findElement(By.xpath("//*[@id=\"ext-comp-1045\"]"));
		enterText(toDate, df.format(ld), "Today's date");
		
		WebElement savebtn = driver.findElement(By.xpath("//*[@id=\"saveReportBtn\"]/tbody/tr[2]/td[2]"));
		clickElement(savebtn, "Save button");
		
		WebElement reportele = driver.findElement(By.xpath("//*[@id=\"saveReportDlg_reportNameField\"]"));
		enterText(reportele, "FirstReport", "Report Name");
		
		WebElement reportunique = driver.findElement(By.xpath("//*[@id=\"saveReportDlg_DeveloperName\"]"));
		enterText(reportunique, "New", "Report unique Name");
		
		WebElement rptsavbtn = driver.findElement(By.id("dlgSaveReport"));
		clickElement(rptsavbtn, "Report Save button");
	}
	
	//Test ID TC-15
	@Test
	public static void sfdcOpp() throws InterruptedException {
		log.info("TestScript TC-15 is executed Started");
		loginPage();
		WebElement oppelem = driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));
		clickElement(oppelem, "opportunities");
		Assert.assertEquals(Constants.OPPORTUNITY, getPageTitle());
		Thread.sleep(3000);
		String[] expectedValues = {"All Opportunities","Closing Next Month","Closing This Month","My Opportunities","New Last Week","New This Week","Opportunity Pipeline","Private","Recently Viewed Opportunities","Won"};
		Select selectelem = new Select(driver.findElement(By.id("fcf")));
		List<WebElement> select = selectelem.getOptions();
		int i=0;
		for(WebElement web: select) {
			Assert.assertEquals(expectedValues[i], web.getText());
			i++;
		}
		log.info("TestScript TC-15 is executed successfully");
		
	}
	
	//Test ID TC-16
	//need to check primary source
	@Test
	public static void sfdcCreateOpty() throws InterruptedException {
		log.info("TestScript TC-16 is executed successfully");
		loginPage();
		WebElement oppelem = driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));
		clickElement(oppelem, "opportunities");
		Assert.assertEquals(Constants.OPPORTUNITY, getPageTitle());
		Thread.sleep(3000);
		
		advertisement("tryLexDialogX");
		Thread.sleep(3000);
		
		WebElement newOpty = driver.findElement(By.xpath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input"));
		clickElement(newOpty, "Create New Opty ");
		
		Assert.assertEquals(Constants.OPTYEDIT, getPageTitle());
				
		WebElement optyname = driver.findElement(By.id("opp3"));
		enterText(optyname, "NewOpty", "Opty Name");
		
		WebElement actName = driver.findElement(By.id("opp4"));
		enterText(actName, "Tekarch1", "Account Name");
		
		WebElement closeDate = driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[3]/table/tbody/tr[2]/td[4]/div/span/span/a"));
		clickElement(closeDate, "Date ");
		
		selectFromDropDown("opp11", "Prospecting");
		
		WebElement prop = driver.findElement(By.id("opp12"));
		enterText(prop, "100", "Propability");
		
		selectFromDropDown("opp6", "Web");
		
		//WebElement primary = driver.findElement(By.id("opp17"));
		//enterText(primary, "opty", "Primary source ");
		
		WebElement save = driver.findElement(By.name("save"));
		clickElement(save,"Save Button ");
		
		Assert.assertEquals(Constants.OPTYSAVE, getPageTitle());
		
	}
	
	//Test ID TC-17
	@Test
	public static void sfdcOptyPipeLine() throws InterruptedException{
		loginPage();
		WebElement oppelem = driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));
		clickElement(oppelem, "opportunities");
		Assert.assertEquals(Constants.OPPORTUNITY, getPageTitle());
		Thread.sleep(3000);
		
		advertisement("tryLexDialogX");
		Thread.sleep(3000);
		
		WebElement pipeline = driver.findElement(By.linkText("Opportunity Pipeline"));
		clickElement(pipeline, "Opty Pipeline");
		
		Assert.assertEquals(Constants.OPTYPIPELINE, getPageTitle());
	}
	
	//Test ID TC-18
	@Test
	public static void sfdcOptyStuck() throws InterruptedException{
		loginPage();
		WebElement oppelem = driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));
		clickElement(oppelem, "opportunities");
		Assert.assertEquals(Constants.OPPORTUNITY, getPageTitle());
		Thread.sleep(3000);
		
		advertisement("tryLexDialogX");
		Thread.sleep(3000);
		
		WebElement stuck = driver.findElement(By.linkText("Stuck Opportunities"));
		clickElement(stuck, "Opty stuck");
		
		Assert.assertEquals(Constants.OPTYSTUCK, getPageTitle());
	}
	
	//Test ID TC-19
		@Test
		public static void sfdcOptySummary() throws InterruptedException{
			log.info("Test Script TC-19 is started successfully");
			loginPage();
			WebElement oppelem = driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));
			clickElement(oppelem, "opportunities");
			Assert.assertEquals(Constants.OPPORTUNITY, getPageTitle());
			Thread.sleep(3000);
			
			advertisement("tryLexDialogX");
			Thread.sleep(3000);
			String[] quarter_q = {"Current FQ", "Next FQ"}; 
			String[] open = {"All Opportunities","Open Opportunities","Closed Opportunities"};
			for(int i=0;i<quarter_q.length;i++) {
				for (int j=0;j<open.length;j++) {
					selectFromDropDown("quarter_q",quarter_q[i]);
					selectFromDropDown("open", open[j]);
					WebElement summary = driver.findElement(By.xpath("//*[@id=\"lead_summary\"]/table/tbody/tr[3]/td/input"));
					clickElement(summary, "Opty Quaterly summary");
					
					WebElement selectele = driver.findElement(By.id("quarter_q"));
					Assert.assertEquals(quarter_q[i], selectedelement(selectele));
					
					WebElement openele = driver.findElement(By.id("open"));
					if(open[j].equalsIgnoreCase("All Opportunities")) {
						Assert.assertEquals("Any", selectedelement(openele));
					}else if(open[j].equalsIgnoreCase("Open Opportunities")) {
					Assert.assertEquals("Open", selectedelement(openele));
					}else if(open[j].equalsIgnoreCase("Closed Opportunities")) {
					Assert.assertEquals("Closed", selectedelement(openele));
					}
					Thread.sleep(3000);
					WebElement oppty = driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));
					clickElement(oppty, "opportunities");
				}
			}
		log.info("Test Script TC-19 is executed successfully");
		}
		
		//Test ID TC-20
		@Test
		public static void sfdcLead() throws InterruptedException{
			log.info("Test Script TC-20 is started successfully");
			loginPage();
			sfdcLeadTab();
			logout("userNavButton","\"//div[@id='userNav-menuItems']//a[5]\"");
			log.info("Test Script TC-20 is executed successfully");
		}
		
		//Test ID TC-21
		@Test
		public static void sfdcLeadHome() throws InterruptedException{
			log.info("Test Script TC-21 is started successfully");
			loginPage();
			sfdcLeadTab();			
			WebElement leadHome = driver.findElement(By.xpath("//*[@id=\"fcf\"]"));
			Select select = new Select(leadHome);
			List<WebElement> web = select.getOptions();
			String[] leadDropdown = {"All Open Leads","My Unread Leads","Recently Viewed Leads","Today's Leads",""};
			
			int i =0;
			for(WebElement dropDown: web) {
				if(i<leadDropdown.length-1) {
					Assert.assertEquals(dropDown.getText(),leadDropdown[i]);
					i++;
				}
			}
			
			logout("userNavButton","\"//div[@id='userNav-menuItems']//a[5]\"");
			log.info("Test Script TC-21 is executed successfully");
		}
		
		//Test ID TC-22
		@Test
		public static void sfdcLeadGo() throws InterruptedException{
			log.info("Test Script TC-22 is started successfully");
			loginPage();
			sfdcLeadTab();	
			advertisement("tryLexDialogX");
			selectFromDropDown("fcf","Today's Leads" );
			
			logout("userNavButton","//div[@id='userNav-menuItems']//a[5]");
			Thread.sleep(3000);
			loginPage();
			sfdcLeadTab();
			
			WebElement goelem = driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[1]/input"));
			clickElement(goelem, "Go button ");
			Assert.assertEquals(Constants.LEADGO,getPageTitle());
			
			log.info("Test Script TC-22 is executed successfully");
		}
		
		public static void sfdcLeadTab() throws InterruptedException{
			WebElement leadelem = driver.findElement(By.xpath("//*[@id=\"Lead_Tab\"]"));
			clickElement(leadelem, "Lead tab ");
			Assert.assertEquals(Constants.LEADHOME, getPageTitle());
			Thread.sleep(3000);
		}
		//Test ID TC-23
		@Test
		public static void sfdcTodaysLead() throws InterruptedException{
			log.info("Test Script TC-23 is started successfully");
			loginPage();
			sfdcLeadTab();
			advertisement("tryLexDialogX");
			selectFromDropDown("fcf","Today's Leads" );
			WebElement goelem = driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[1]/input"));
			clickElement(goelem, "Go button ");
			Assert.assertEquals(Constants.LEADGO,getPageTitle());
			logout("userNavButton","//div[@id='userNav-menuItems']//a[5]");
												
			log.info("Test Script TC-23 is executed successfully");
		}
	//Test ID Tc-24
	@Test
	public static void sfdcLeadNew() throws InterruptedException{
		log.info("Test Script TC-24 is started successfully");
		loginPage();
		sfdcLeadTab();
		advertisement("tryLexDialogX");
		
		WebElement newelem = driver.findElement(By.xpath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input"));
		clickElement(newelem, "New button ");
		Assert.assertEquals(Constants.LEADEDIT, getPageTitle());
		
		WebElement lastname = driver.findElement(By.id("name_lastlea2"));
		enterText(lastname, "ABCD", "Last Name ");
		
		WebElement companyname = driver.findElement(By.id("lea3"));
		enterText(companyname, "ABCD", "Company Name ");
		
		WebElement savebtn = driver.findElement(By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]"));
		clickElement(savebtn, "Save button");
		Assert.assertEquals(Constants.LEADNAME,getPageTitle());
		logout("userNavButton","//div[@id='userNav-menuItems']//a[5]");
		log.info("Test Script TC-24 is executed successfully");
	}
	
	//Test ID TC-25
	@Test
	public static void sfdcContact() throws InterruptedException{
		log.info("Test Script TC-25 is started successfully");
		loginPage();
		sfdcContactTab();
		Thread.sleep(3000);
		advertisement("tryLexDialogX");
		WebElement newelem = driver.findElement(By.xpath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input"));
		clickElement(newelem, "New button ");
		Assert.assertEquals(Constants.CONTACTEDIT, getPageTitle());
		
		WebElement lastname = driver.findElement(By.id("name_lastcon2"));
		enterText(lastname, "Newcnt", "Last Name ");
		
		WebElement accountname = driver.findElement(By.id("con4"));
		enterText(accountname, "ABCD", "Last Name ");
		
		WebElement savebtn = driver.findElement(By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]"));
		clickElement(savebtn, "Save button");
		
		Assert.assertEquals(Constants.NEWCONTACT, getPageTitle());
		log.info("Test Script TC-25 is executed successfully");
	}
	
	public static void sfdcContactTab() {
		WebElement contactelem = driver.findElement(By.xpath("//*[@id=\"Contact_Tab\"]/a"));
		clickElement(contactelem, "Contact Tab ");
		Assert.assertEquals(Constants.CONTACTTITLE, getPageTitle());
	}
	
	//Test ID TC-26
	@Test
	public static void sfdcContactView() throws InterruptedException{
		log.info("Test Script TC-26 is started successfully");
		loginPage();
		sfdcContactTab();
		Thread.sleep(3000);
		advertisement("tryLexDialogX");
		
		WebElement createNew = driver.findElement(By.linkText("Create New View"));
		clickElement(createNew, "Create New View ");
		
		WebElement viewele = driver.findElement(By.xpath("//*[@id=\"fname\"]"));
		enterText(viewele, "TekarchView", "Contact view ");
		
		WebElement viewUnique = driver.findElement(By.xpath("//*[@id=\"devname\"]"));
		enterText(viewUnique, "unique", "View unique field");
		
		WebElement save = driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]"));
		clickElement(save, "Save button ");
		
		Assert.assertEquals(Constants.CONTACTUNIQUE, getPageTitle());
		log.info("Test Script TC-26 is executed successfully");
	}
	
	//Test ID TC-27
	@Test
	public static void sfdcCntRecentlyViewed() throws InterruptedException{
		log.info("Test Script TC-27 is started successfully");
		loginPage();
		sfdcContactTab();
		Thread.sleep(3000);
		advertisement("tryLexDialogX");
		
		selectFromDropDown("hotlist_mode", "Recently Viewed");
		Assert.assertEquals(Constants.CONTACTHOME,getPageTitle());
		
		log.info("Test Script TC-27 is executed successfully");
	}
	
	//Test ID TC-28
	@Test
	public static void sfdcMyContacts() throws InterruptedException{
		log.info("Test Script TC-28 is started successfully");
		loginPage();
		sfdcContactTab();
		Thread.sleep(3000);
		advertisement("tryLexDialogX");
		
		selectFromDropDown("fcf","My Contacts");
		Assert.assertEquals(Constants.CONTACTHOME,getPageTitle());
		
		log.info("Test Script TC-28 is executed successfully");
	}
	
	//Test ID TC-29
	@Test
	public static void sfdcContactName() throws InterruptedException{
		log.info("Test Script TC-29 is started successfully");
		loginPage();
		sfdcContactTab();
		Thread.sleep(3000);
		advertisement("tryLexDialogX");
		
		WebElement cntName = driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[3]/div[1]/div/div[2]/table/tbody/tr[2]/th/a"));
		clickElement(cntName, "Contact Name ");
		WebElement checkName = driver.findElement(By.xpath("//*[@id=\"contactHeaderRow\"]/div[2]/h2"));
		Assert.assertEquals(cntName.getText(),checkName.getText());
		
		log.info("Test Script TC-29 is executed successfully");
	}
	
	//Test ID TC-30
		@Test
		public static void sfdcCntNewView() throws InterruptedException{
			log.info("Test Script TC-30 is started successfully");
			loginPage();
			sfdcContactTab();
			Thread.sleep(3000);
			advertisement("tryLexDialogX");
			
			WebElement newView = driver.findElement(By.linkText("Create New View"));
			clickElement(newView, "Create New View ");
			System.out.println("Page-->"+getPageTitle());
			
			WebElement viewname = driver.findElement(By.id("devname"));
			enterText(viewname, "EFGH", "View Unique Name ");
			
			WebElement savebtn = driver.findElement(By.name("save"));
			clickElement(savebtn, "Save Button");
			
			WebElement errormsg = driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[2]/div[1]/div[2]/table/tbody/tr[1]/td[2]/div/div[2]"));
			
			Assert.assertEquals(Constants.CONTACTERRMSG, errormsg.getText());
			
			log.info("Test Script TC-30 is executed successfully");
		}
		
	//Test ID TC-31
		@Test
		public static void sfdcCntNew() throws InterruptedException{
			log.info("Test Script TC-31 is started successfully");
			loginPage();
			sfdcContactTab();
			Thread.sleep(3000);
			advertisement("tryLexDialogX");
			
			WebElement newView = driver.findElement(By.linkText("Create New View"));
			clickElement(newView, "Create New View ");
			Assert.assertEquals(Constants.CONTACTNEWVIEW, getPageTitle());
			
			WebElement viewname = driver.findElement(By.id("fname"));
			enterText(viewname, "ABCD", "View Name ");
			
			WebElement viewuniquename = driver.findElement(By.id("devname"));
			enterText(viewuniquename, "EFGH", "View Unique Name ");
			
			WebElement cancelbtn = driver.findElement(By.name("cancel"));
			clickElement(cancelbtn, "Cancel Button");
						
			Assert.assertEquals(Constants.CONTACTHOME, getPageTitle());
			
			log.info("Test Script TC-31 is executed successfully");
		}
	
	//Test ID TC-32
		@Test
		public static void sfdccntSaveNew() throws InterruptedException{
			log.info("Test Script TC-32 is started successfully");
			loginPage();
			sfdcContactTab();
			Thread.sleep(3000);
			advertisement("tryLexDialogX");
			
			WebElement newbtn = driver.findElement(By.xpath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input"));
			clickElement(newbtn, "New Button ");
			
			WebElement lastName = driver.findElement(By.id("name_lastcon2"));
			enterText(lastName, "Indian", "Last Name ");
			
			WebElement acctname = driver.findElement(By.id("con4"));
			enterText(acctname, "Global Media", "Account Name ");
			
			WebElement savenew = driver.findElement(By.xpath("//*[@id=\"bottomButtonRow\"]/input[2]"));
			clickElement(savenew, "Save & New button ");
			
			Assert.assertEquals(Constants.CONTACTEDIT, getPageTitle());
			
			log.info("Test Script TC-32 is executed successfully");
			
		}
		
	//Test ID TC-33
	@Test
	public static void sfdcHomeTab() throws InterruptedException{
		log.info("Test Script TC-33 is started successfully");
		loginPage();
		WebElement home = driver.findElement(By.linkText("Home"));
		clickElement(home, "Home Tab ");
		Thread.sleep(5000);
		advertisement("tryLexDialogX");
		
		WebElement actname = driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1/a"));
		String accountName = actname.getText();
		clickElement(actname, "First name Last Name ");
		System.out.println("pag->"+getPageTitle());
		Thread.sleep(3000);
		WebElement click = driver.findElement(By.id("userNavButton"));
		Assert.assertEquals(accountName, click.getText());
		clickElement(click, "UserNav ");
		 WebElement myprofileEle = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[1]"));
		 clickElement(myprofileEle, "MyProfile Info");
		 System.out.println("page-->"+getPageTitle());
		
		log.info("Test Script TC-33 is executed successfully");
	}
	
	//Test ID TC-34
	@Test
	public static void sfdcHomeAbout() throws InterruptedException{
		log.info("Test Script TC-34 is started successfully");
		loginPage();
		WebElement home = driver.findElement(By.linkText("Home"));
		clickElement(home, "Home Tab ");
		Thread.sleep(8000);
		advertisement("tryLexDialogX");
		
		WebElement actname = driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1/a"));
		String editprofileTitle = "User:"+actname.getText()+"~ Salesforce - Developer Edition";
		clickElement(actname, "First name Last Name ");
		WebElement edit = driver.findElement(By.xpath("//*[@id=\"chatterTab\"]/div[2]/div[2]/div[1]/h3/div/div/a/img"));
		clickElement(edit, "Edit profile ");
		
		Assert.assertEquals(editprofileTitle, getPageTitle());
		Thread.sleep(3000);
		
		WebElement aboutFrame = driver.findElement(By.id("contactInfoContentId"));
		driver.switchTo().frame(aboutFrame);
		WebElement about = driver.findElement(By.xpath("//*[@id=\"aboutTab\"]/a"));
		clickElement(about, "About Tab ");
		
		WebElement lastName = driver.findElement(By.id("lastName"));
		enterText(lastName, "AB", "Last Name ");
		
		WebElement save = driver.findElement(By.xpath("//*[@id=\"TabPanel\"]/div/div[2]/form/div/input[1]"));
		clickElement(save, "Save All Button ");
		
		WebElement actnewname = driver.findElement(By.id("tailBreadcrumbNode"));
		WebElement userNav = driver.findElement(By.id("userNavButton"));
		Assert.assertEquals(actnewname.getText().trim(), userNav.getText().trim());
		
		log.info("Test Script TC-34 is executed successfully");
	}
	
	//Test ID TC-35
	@Test
	public static void sfdcTabCustomize() throws InterruptedException{
		log.info("Test Script TC-35 is started successfully");
		boolean flag = false;
		String removetab = null;
		loginPage();
		WebElement allTab = driver.findElement(By.id("AllTab_Tab"));
		clickElement(allTab, "All Tab ");
		Thread.sleep(8000);
		WebElement customizeele = driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[3]/div[1]/table/tbody/tr/td[2]/input"));
		clickElement(customizeele, "Customize my Tabs ");
		
		WebElement selectele = driver.findElement(By.id("duel_select_1"));
		Select selectany = new Select(selectele);
		removetab = selectany.getOptions().get(1).getText();
		selectFromDropDown("duel_select_1", removetab);
		
		WebElement removeele = driver.findElement(By.id("duel_select_0_left"));
		clickElement(removeele, "Remove Tab ");
		Thread.sleep(3000);
		WebElement selectelem = driver.findElement(By.id("duel_select_1"));
		Select selectall = new Select(selectelem);
		List<WebElement> selectlist = selectall.getOptions();
		
		for(WebElement list: selectlist) {
			if(list.equals(removetab)) {
				flag = true;
			}
		}
		
		if(flag == true) {
			log.info(removetab+" is not removed from the All Tabs");
			}
				
		WebElement save = driver.findElement(By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]"));
		clickElement(save, "Save Button ");
		
		logout("userNavButton","//div[@id='userNav-menuItems']//a[5]");
		Thread.sleep(3000);
		loginPage();
		WebElement tabs = driver.findElement(By.id("tabBar"));
		System.out.println(tabs.getText());
		
		log.info("Test Script TC-35 is executed successfully");
	}
		
}
