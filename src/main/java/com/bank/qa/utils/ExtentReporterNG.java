package com.bank.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.ITestListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.bank.qa.base.TestBase;

public class ExtentReporterNG extends TestBase implements ITestListener{

	public static ExtentReports generateExtentReport() throws IOException
	{
	ExtentReports extentReport = new ExtentReports();
	File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
	ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
	
	sparkReporter.config().setTheme(Theme.STANDARD);
	sparkReporter.config().setReportName("Guru99 Bank Application Automation Test Report");
	sparkReporter.config().setDocumentTitle("Guru99 Bank Application");
	sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
	
	extentReport.attachReporter(sparkReporter);
	
	Properties prop = new Properties();
	File configPropFile = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\bank\\qa\\config\\config.properties");
	try {
	FileInputStream fis = new FileInputStream(configPropFile);
	prop.load(fis);
	}catch(Throwable e)
	{
		e.printStackTrace();
	}
	extentReport.setSystemInfo("Application URL", prop.getProperty("URL"));
	extentReport.setSystemInfo("BrowserName", prop.getProperty("browser"));
	extentReport.setSystemInfo("Application Username", prop.getProperty("username"));
	extentReport.setSystemInfo("Password", prop.getProperty("password"));
	extentReport.setSystemInfo("Operating System",System.getProperty("os.name"));
	extentReport.setSystemInfo("System Username",System.getProperty("user.name"));
	extentReport.setSystemInfo("Java Version",System.getProperty("java.version"));
	
	return extentReport;
}
}