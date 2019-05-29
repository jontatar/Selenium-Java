package io.platform.project.tests;

import java.net.URL;
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

public class VerifyAllResultLinks {
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
	public void verifyAllResultLinks() {
		catalogPage.fillQuickFind("A");
		catalogPage.clickQuickFindSearchButton();
		List<String> aList = resultsPage.getAllResultsLinks();
		for (int i = 0; i < aList.size(); i++) {
			String aLink = aList.get(i);
			try {
				System.out.println("Link: " + aLink + "  " + common.linkStatus(new URL(aLink)));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		common.logResults(driver, result.getStatus(), result.getMethod().getMethodName());
		driver.close();
	}

}
