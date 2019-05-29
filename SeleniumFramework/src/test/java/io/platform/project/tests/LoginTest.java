package io.platform.project.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.platform.project.commons.Common;
import io.platform.project.commons.WebDriverManager;
import io.platform.project.data.LoginData;
import io.platform.project.pageObjects.CatalogPage;
import io.platform.project.pageObjects.LoginPage;

public class LoginTest {
	WebDriver driver;
	WebDriverManager webDrivermanager;
	CatalogPage catalogPage;
	LoginPage loginPage;
	Common common;

	@BeforeMethod
	public void setUp() {
		// Open Chrome
		webDrivermanager = new WebDriverManager();
		driver = webDrivermanager.getDriver();
		// Open URL
		catalogPage = new CatalogPage(driver);
		catalogPage.openHomePageURL(driver);
		loginPage = new LoginPage(driver);
		common = new Common();
	}

	@Test(dataProvider = "credentials", dataProviderClass = LoginData.class)
	public void testLogin(String email, String pwd) {
		catalogPage.clickLogYourselfInLink();
		loginPage.fillInEmailAddressTextBox(email);
		loginPage.fillInPasswordTextBox(pwd);
		loginPage.clickOnSignInButton();
		Assert.assertEquals(catalogPage.getPageHeaderText(), "Welcome to iBusiness");
		System.out.println(catalogPage.getUserGreetingText());
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		common.logResults(driver, result.getStatus(), result.getMethod().getMethodName());
		driver.close();
	}

}
