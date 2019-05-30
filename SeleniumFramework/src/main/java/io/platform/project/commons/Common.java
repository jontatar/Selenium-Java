package io.platform.project.commons;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Common {
	WebDriver driver;
	XSSFWorkbook WBookRead;

	private static Logger logger = Logger.getLogger(Common.class);

	public static boolean checkForElement(WebDriver driver, WebElement elem, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		try {
			wait.until(ExpectedConditions.visibilityOf(elem));
			logger.info(elem + " found");
			return true;
		} catch (TimeoutException ex) {
			ex.printStackTrace();
			logger.info(elem + " not found");
			return false;
		}
	}
	
	public static String getElementText(WebDriver driver, WebElement elem) {
		if (checkForElement(driver, elem, 2)) {
			String str = elem.getText();
			logger.info("Text value returned: " + str);
			return str;
		} else {
			return null;
		}

	}

	public static void clickElement(WebElement elem) {
		try {
			elem.click();
			logger.info("Clicked: " + elem);
		} catch (NoSuchElementException ex) {
			logger.info("Element cannot be found - " + elem);
			throw new RuntimeException("Element cannot be found");
		}
	}

	public static void fillInElementTextBox(WebElement elem, String str) {
		try {
			elem.clear();
			elem.sendKeys(str);
			logger.info("Value entered into " + elem + ": " + str);
		} catch (NoSuchElementException ex) {
			logger.info("Element cannot be found - " + elem);
			throw new RuntimeException("Element cannot be found");
		}
	}
	
	public static void check( WebDriver driver, boolean condition, String failMessage){
		if (condition) {
			logger.info("Check Condition True");
			Assert.assertTrue(true);
		} else {
//			screenShot(driver,currentThread().getStackTrace()[2].getClassName(),
//					currentThread().getStackTrace()[2].getMethodName());
			logger.info(failMessage);
			Assert.fail();
		}
	}

	
	// Add java classs name
	// Defined as private because it's only called from Common
	private void takeScreenshot(WebDriver driver, String fileName) throws Exception {
		String directory = ReadPropertyFile.getConfigPropertyVal("captureLoc") + "\\";
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile, new File(directory, fileName));
	}

	// change from getStatus() to isSuccess()
	public void logResults(WebDriver driver, int condition, String methodName) throws Exception {
		if (condition == 2) {
			String fileName = methodName + "_" + this.timestamp() + ".png";
			this.takeScreenshot(driver, fileName);
			logger.info("\nAssertion Failed. Screenshot taken.\n");
		} else {
			logger.info("\nAssertion Passed\n");
		}
	}

	
	// Correct the filename.  TS first, add in java.class
	public String timestamp() {
		String aDate = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss").format(new Date());
		return aDate;
	}
	
	public static boolean isValidEmail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	public String linkStatus(URL url) {
		try {
			HttpURLConnection hConn = (HttpURLConnection) url.openConnection();
			hConn.connect();
			String responseMessage = hConn.getResponseMessage();
			hConn.disconnect();
			return responseMessage;
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
