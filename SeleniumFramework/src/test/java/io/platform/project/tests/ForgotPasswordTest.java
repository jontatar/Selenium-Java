package io.platform.project.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.platform.project.commons.Common;
import io.platform.project.commons.ReadPropertyFile;
import io.platform.project.commons.WebDriverManager;
import io.platform.project.pageObjects.CatalogPage;
import io.platform.project.pageObjects.LoginPage;
import io.platform.project.pageObjects.PasswordForgottenPage;

public class ForgotPasswordTest {
	WebDriver driver;
	WebDriverManager webDrivermanager;
	CatalogPage catalogPage;
	LoginPage loginPage;
	PasswordForgottenPage passwordForgottenPage;
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
		passwordForgottenPage = new PasswordForgottenPage(driver);
		common = new Common();

	}

	@Test
	public void testForgotPasswordBackButton() {
		Assert.assertEquals(catalogPage.getPageHeaderText(), "Welcome to iBusiness");
		catalogPage.clickLogYourselfInLink();
		Assert.assertEquals(loginPage.getPageHeaderText(), "Welcome, Please Sign In");
		loginPage.clickForgotPasswordLink();
		Assert.assertEquals(passwordForgottenPage.getPageHeaderText(), "I've Forgotten My Password!");
		passwordForgottenPage.clickBackButton();
		Assert.assertEquals(loginPage.getPageHeaderText(), "Welcome, Please Sign In");
	}

	@Test
	public void testForgotPasswordNoEmailAddress() {
		catalogPage.clickLogYourselfInLink();
		loginPage.clickForgotPasswordLink();
		Assert.assertEquals(passwordForgottenPage.getPageHeaderText(), "I've Forgotten My Password!");
		passwordForgottenPage.clickContinueButton();
		Assert.assertEquals(passwordForgottenPage.getErrorMessageText(), " Error: The E-Mail Address was not found in our records, please try again.");
	}

	@Test
	public void testForgotPasswordBadEmailAddress() {
		catalogPage.clickLogYourselfInLink();
		loginPage.clickForgotPasswordLink();
		Assert.assertEquals(passwordForgottenPage.getPageHeaderText(), "I've Forgotten My Password!");
		passwordForgottenPage.fillInEmailAddressTextBox("BAD EMAIL");
		passwordForgottenPage.clickContinueButton();
		Assert.assertEquals(passwordForgottenPage.getErrorMessageText(), " Error: The E-Mail Address was not found in our records, please try again.");
	}

	@Test
	public void testForgotPasswordGoodEmailAddress() {
		catalogPage.clickLogYourselfInLink();
		loginPage.clickForgotPasswordLink();
		AssertJUnit.assertEquals(passwordForgottenPage.getPageHeaderText(), "I've Forgotten My Password!");
		passwordForgottenPage.fillInEmailAddressTextBox(ReadPropertyFile.getConfigPropertyVal("user1"));
		passwordForgottenPage.clickContinueButton();
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("contentText")));
		String content = passwordForgottenPage.getSuccessContentText();
		boolean containsText = content.contains("Please check your e-mail for instructions");
		Assert.assertTrue(containsText);
	}
	
	@Test
	public void testForgotPasswordGoodEmailAddressSecondRequest() {
		catalogPage.clickLogYourselfInLink();
		loginPage.clickForgotPasswordLink();
		Assert.assertEquals(passwordForgottenPage.getPageHeaderText(), "I've Forgotten My Password!");
		passwordForgottenPage.fillInEmailAddressTextBox(ReadPropertyFile.getConfigPropertyVal("user1"));
		passwordForgottenPage.clickContinueButton();
		Assert.assertEquals(passwordForgottenPage.getErrorMessageText(), " Error: A password reset link has already been sent. Please try again in 5 minutes.");
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		common.logResults(driver, result.getStatus(), result.getMethod().getMethodName());
		driver.close();
	}

}
