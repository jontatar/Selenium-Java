package io.platform.project.tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.platform.project.commons.Common;
import io.platform.project.commons.ReadPropertyFile;
import io.platform.project.commons.WebDriverManager;
import io.platform.project.pageObjects.CartContentsPage;
import io.platform.project.pageObjects.CatalogPage;
import io.platform.project.pageObjects.ConfirmationPage;
import io.platform.project.pageObjects.LogOffPage;
import io.platform.project.pageObjects.LoginPage;
import io.platform.project.pageObjects.PaymentMethodPage;
import io.platform.project.pageObjects.ProductPage;
import io.platform.project.pageObjects.ShippingMethodPage;
import io.platform.project.pageObjects.SuccessPage;

public class PlaceAndConfirmOrderTest {

	WebDriver driver;
	WebDriverManager webDrivermanager;
	Common common;
	CatalogPage catalogPage;
	LoginPage loginPage;
	ProductPage productPage;
	CartContentsPage cartContentsPage;
	ShippingMethodPage shippingMethodPage;
	PaymentMethodPage paymentMethodPage;
	ConfirmationPage confirmationPage;
	SuccessPage successPage;
	LogOffPage logOffPage;

	@BeforeMethod
	public void setUp() {
		//Open Chrome
		webDrivermanager = new WebDriverManager();
		driver = webDrivermanager.getDriver();
		//Open URL
		catalogPage = new CatalogPage(driver);
		catalogPage.openHomePageURL(driver);
		
		loginPage = new LoginPage(driver);
		productPage = new ProductPage(driver);
		cartContentsPage = new CartContentsPage(driver);
		shippingMethodPage = new ShippingMethodPage(driver);
		paymentMethodPage = new PaymentMethodPage(driver);
		confirmationPage = new ConfirmationPage(driver);
		successPage = new SuccessPage(driver);
		logOffPage = new LogOffPage(driver);
		common = new Common();
	}

	@Test
	public void test() throws Exception {

		catalogPage.clickLogYourselfInLink();
		loginPage.fillInEmailAddressTextBox(ReadPropertyFile.getConfigPropertyVal("user1"));
		loginPage.fillInPasswordTextBox(ReadPropertyFile.getConfigPropertyVal("pwd1"));
		loginPage.clickOnSignInButton();
		catalogPage.selectProduct("Samsung Galaxy Tab");
		productPage.clickAddToCartButton();
		cartContentsPage.clickCheckoutButton();
		shippingMethodPage.fillInCommentsTextBox("Please leave package on back porch");
		shippingMethodPage.clickContineButton();
		paymentMethodPage.clickCODRadioButton();
		paymentMethodPage.fillInCommentsTextBox("Do you take travelers checks?");
		paymentMethodPage.clickContinueButton();
		confirmationPage.clickConfirmOrderButton();
		successPage.clickNotifyCheckbox(true);
		successPage.clickContinueButton();
		catalogPage.clickLogOffButton();
		logOffPage.clickContinueButton();

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		common.logResults(driver, result.getStatus(), result.getMethod().getMethodName());
		driver.close();
	}

}
