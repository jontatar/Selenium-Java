package io.platform.project.tests;

import org.testng.annotations.Test;

import io.platform.project.commons.Common;
import io.platform.project.commons.WebDriverManager;
import io.platform.project.pageObjects.CatalogPage;
import io.platform.project.pageObjects.ResultsPage;

import org.testng.annotations.BeforeMethod;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class PrintManufacturersDDValues {
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
	public void printManufacturersDDValues() {

		List<String> aList = catalogPage.manufacturersDropdownTextValues();
		for (int i = 0; i < aList.size(); i++) {
			System.out.println(aList.get(i));
		}
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		common.logResults(driver, result.getStatus(), result.getMethod().getMethodName());
		driver.close();
	}

}
