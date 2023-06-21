package com.automation.utility;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automation.base.BaseTest;

public class TestEventListernersUtility implements ITestListener{
	private static ExtentReportsUtility extentReport;
	@Override
	public void onTestStart(ITestResult result) {
		extentReport.startSingleTestReport(result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentReport.logTestpassed(result.getMethod().getMethodName()+"is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentReport.logTestFailed(result.getMethod().getMethodName()+"is failed");
		extentReport.logTestFailedWithException(result.getThrowable());
		BaseTest ob=new BaseTest();
		WebDriver driver= BaseTest.driver;
		System.out.println("driver got it in listerners utility="+driver);
		File imageFile=ob.getScreenshotOfThePage(driver);
		byte[] fileContent=null;
		try {
			fileContent = FileUtils.readFileToByteArray(new File(imageFile.getAbsolutePath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String imageEncodedString = Base64.getEncoder().encodeToString(fileContent);
		
		extentReport.logTestWithscreenshot(imageEncodedString);
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		extentReport=ExtentReportsUtility.getInstance();
		extentReport.startExtentReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.endReport();
	}
	

}