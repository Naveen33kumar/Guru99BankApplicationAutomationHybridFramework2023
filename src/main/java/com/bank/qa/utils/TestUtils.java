package com.bank.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.IllegalFormatException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;

import com.bank.qa.base.TestBase;

public class TestUtils extends TestBase{
	
		public static long PAGE_LOAD_TIMEOUT = 20;
		public static long IMPLICITWAIT_TIMEOUT = 20;
		
		public static String TESTDATA_SHEET_PATH = "C:\\Users\\navee\\eclipse-workspace\\BankApplicationTest\\"
				+ "src\\main\\java\\com\\bank\\qa\\testdata\\TestDatas.xlsx";
		
		static Workbook book;
		static Sheet sheet;
		
		
		public void acceptAlert()
		{
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}
		
		public String getAlertText()
		{
			Alert alert = driver.switchTo().alert();
			return alert.getText();
		}
		
		public void dismissAlert()
		{
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		}
		
		public static Object[][] getTestData(String sheetName) {
			FileInputStream file = null;
			try {
				file = new FileInputStream(TESTDATA_SHEET_PATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				book = WorkbookFactory.create(file);
			} catch (IllegalFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			sheet = book.getSheet(sheetName);
			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			// System.out.println(sheet.getLastRowNum() + "--------" +
			// sheet.getRow(0).getLastCellNum());
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
					// System.out.println(data[i][k]);
				}
			}
			return data;
		}
		
		//public static void takeScreenshotAtEndOfTest(String testMethodName) throws IOException {
			//File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			//String currentDir = System.getProperty("user.dir");
			//FileUtils.copyFile(scrFile, new File(currentDir + "/testFailureScreenshots/"+ testMethodName + ".png"));
		//}
		
		public static String takeScreenShot(String testName)
		{
			File scrScreenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String fileDestination = System.getProperty("user.dir")+"\\testFailureScreenshots\\"+testName+".png";
			try {
				FileHandler.copy(scrScreenShot, new File(fileDestination));
			} catch (IOException e) {
				 //TODO Auto-generated catch block
				e.printStackTrace();
			}
			return fileDestination;
		}
}