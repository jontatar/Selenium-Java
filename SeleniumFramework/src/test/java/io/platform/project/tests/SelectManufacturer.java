package io.platform.project.tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.platform.project.commons.Common;
import io.platform.project.commons.WebDriverManager;
import io.platform.project.pageObjects.CatalogPage;
import io.platform.project.pageObjects.ResultsPage;

public class SelectManufacturer {
	WebDriver driver;
	WebDriverManager webDrivermanager;
	CatalogPage catalogPage;
	ResultsPage resultsPage;
	Common common;

	@BeforeMethod
	public void setUp() {
		// Open Chrome
		webDrivermanager = new WebDriverManager();
		driver = webDrivermanager.getDriver();
		// Open URL
		catalogPage = new CatalogPage(driver);
		catalogPage.openHomePageURL(driver);
		common = new Common();
	}

	@Test
	public void printManufacturersDDValues() throws Exception {

		catalogPage.selectManufacturer("Microsoft");
		Thread.sleep(10000);
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		common.logResults(driver, result.getStatus(), result.getMethod().getMethodName());
		driver.close();
	}
}
