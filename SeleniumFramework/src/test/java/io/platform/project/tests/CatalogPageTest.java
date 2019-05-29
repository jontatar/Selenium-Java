package io.platform.project.tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.platform.project.commons.WebDriverManager;
import io.platform.project.pageObjects.CatalogPage;

public class CatalogPageTest {
	WebDriver driver;
	CatalogPage catalogPage;
	WebDriverManager webDrivermanager;
	
	@BeforeTest
	public void setup() {
		//Open Chrome
		webDrivermanager = new WebDriverManager();
		driver = webDrivermanager.getDriver();
		//Open URL
		catalogPage = new CatalogPage(driver);
		catalogPage.openHomePageURL(driver);
	}
	
	@Test
	public void testPageHeader() {
		String actualText = catalogPage.getPageHeaderText();
		assertEquals(actualText, "Welcome to iBusiness");
	}
	
	@Test
	public void testLoggedIn() {
		catalogPage.clickLogOffButton();
	}
	
	@Test
	public void testGoToLoginPage() {
		catalogPage.clickLogYourselfInLink();
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(3000);
		driver.close();
	}

}
