package io.platform.project.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.platform.project.commons.Common;
import io.platform.project.commons.WebDriverManager;
import io.platform.project.data.ContactUsData;
import io.platform.project.pageObjects.ContactUsPage;

public class ContactUsTest {
	WebDriver driver;
	WebDriverManager webDrivermanager;
	ContactUsPage contactUsPage;
	Common common;

	@BeforeMethod
	public void setUp() {
		// Open Chrome
		webDrivermanager = new WebDriverManager();
		driver = webDrivermanager.getDriver();
		// Open URL
		contactUsPage = new ContactUsPage(driver);
		contactUsPage.openContactUsPageURL(driver);
		common = new Common();
	}

	@Test(dataProvider = "credentials", dataProviderClass = ContactUsData.class)
	public void contactUsTest(String fullName, String email, String enquiry) throws Exception {

		contactUsPage.fillInNameTextBox(fullName);
		contactUsPage.fillInEmailTextBox(email);
		contactUsPage.fillInEnquiryTextBox(enquiry);
		contactUsPage.clickContinueButton();
		Assert.assertEquals(contactUsPage.getSuccessStringText(), "Your enquiry has been successfully sent to the Store Owner.");
		Thread.sleep(5000);
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		common.logResults(driver, result.getStatus(), result.getMethod().getMethodName());
		driver.close();
	}
}
