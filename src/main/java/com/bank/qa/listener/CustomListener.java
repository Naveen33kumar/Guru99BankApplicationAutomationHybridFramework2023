package com.bank.qa.listener;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.bank.qa.utils.ExtentReporterNG;
import com.bank.qa.utils.TestUtils;

public class CustomListener extends TestUtils implements ITestListener{
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;
	
	public void onTestStart(ITestResult result) {
	    testName = result.getName();
	    extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName+ "Started Executing");
		
	}

	public void onTestSuccess(ITestResult result) {
		testName = result.getName();
		extentTest.log(Status.PASS,testName+ " Got Successfully Executed And It Was Success");
	}

	public void onTestSkipped(ITestResult result) {
		testName = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP,testName+" Got Skipped ");
	}

	public void onStart(ITestContext context) {
		try {
			  extentReport  = ExtentReporterNG.generateExtentReport();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void onFinish(ITestContext context) {
		extentReport.flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestFailure(ITestResult result)
	{
		testName = result.getName();
		
		String fileDestination = TestUtils.takeScreenShot(testName);
		
		extentTest.addScreenCaptureFromPath(fileDestination);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,result.getThrowable());
		extentTest.log(Status.INFO,testName+" Got Failed ");	
	}
}