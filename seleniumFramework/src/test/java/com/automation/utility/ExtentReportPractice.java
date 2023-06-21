package com.automation.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportPractice {
	
	public static void main(String[] args) {
		
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter report = new ExtentSparkReporter("MySpark.html");
		extent.attachReporter(report);
		
		extent.setSystemInfo("Host Name", "Fire Base");
		extent.setSystemInfo("Env", "QA");
		
		report.config().setDocumentTitle("Test Execution Report");
		report.config().setReportName("Fire Base Tests");
		report.config().setTheme(Theme.DARK);
		
		ExtentTest reportLogger = extent.createTest("testscript1");
		reportLogger.log(Status.INFO, "test started");
		reportLogger.log(Status.INFO, "browser launched");
		reportLogger.log(Status.INFO, "url entered");
		reportLogger.log(Status.INFO, "username entered");
		reportLogger.log(Status.INFO, "password entered");
		reportLogger.log(Status.INFO, "login button clicked");
		reportLogger.log(Status.PASS, "test completed with pass");
		
		extent.flush();
	}

}
