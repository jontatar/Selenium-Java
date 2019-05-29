package io.platform.project.tests;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.platform.project.commons.Common;
import io.platform.project.commons.WebDriverManager;
import io.platform.project.pageObjects.CatalogPage;
import io.platform.project.pageObjects.ResultsPage;

public class PrintSearchResultsTest {
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
		resultsPage = new ResultsPage(driver);
		common = new Common();
	}

	@Test
	public void printAllResultTitles() {
		catalogPage.fillQuickFind("A");
		catalogPage.clickQuickFindSearchButton();
		List<String> aList = resultsPage.getAllResultsTitles();
		for (String title : aList) {
			System.out.println(title);
		}
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		common.logResults(driver, result.getStatus(), result.getMethod().getMethodName());
		driver.close();
	}

}
