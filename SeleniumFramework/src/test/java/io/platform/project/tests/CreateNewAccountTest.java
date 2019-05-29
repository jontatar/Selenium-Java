package io.platform.project.tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.platform.project.commons.WebDriverManager;
import io.platform.project.pageObjects.CatalogPage;
import io.platform.project.pageObjects.CreateAccountPage;
import io.platform.project.pageObjects.CreateAccountSuccessPage;

public class CreateNewAccountTest {
	
	WebDriver driver;
	WebDriverManager webDrivermanager;
	CatalogPage catalogPage;
	CreateAccountPage createAccountPage;
	CreateAccountSuccessPage createAccountSuccessPage;

	@BeforeMethod
	public void setUp() {
		//Open Chrome
		webDrivermanager = new WebDriverManager();
		driver = webDrivermanager.getDriver();
		//Open URL
		catalogPage = new CatalogPage(driver);
		catalogPage.openHomePageURL(driver);
	}
	
	@Test
	public void test() throws Exception {
	
		catalogPage.clickCreateAnAccountLink();
		createAccountPage = new CreateAccountPage(driver);
		createAccountPage.clickMaleRadioButton();
		createAccountPage.fillInFirstName("Jorge");
		createAccountPage.fillInLastName("Jones");
		createAccountPage.fillInDoB("12/21/1943");
		createAccountPage.fillInEmailAddress("jjones@test.com");
		createAccountPage.fillInCompanyName("N/A");
		createAccountPage.fillInStreetAddressTextBox("100 Main Street");
		createAccountPage.fillInSuburbTextBox("The Burbs");
		createAccountPage.fillInPostCodeTextBox("95070");
		createAccountPage.fillInCityTextBox("Saratoga");
		createAccountPage.fillInStateTextBox("CA");
		createAccountPage.fillInCountryDropdown("United States");
		createAccountPage.fillInTelephoneNumberTextBox("408-555-1212");
		createAccountPage.fillInFaxNumberTextBox("408-555-1213");
		createAccountPage.clickNewletterCheckbox(true);
		createAccountPage.fillInPasswordTextBox("password");
		createAccountPage.fillInPasswordConfirmationTextBox("password");
		createAccountPage.clickContinueButton();
		createAccountSuccessPage = new CreateAccountSuccessPage(driver);
		assertEquals(createAccountSuccessPage.getPageHeaderText(), "Your Account Has Been Created!");
		createAccountPage.clickContinueButton();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
