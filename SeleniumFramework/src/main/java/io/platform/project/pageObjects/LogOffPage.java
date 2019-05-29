package io.platform.project.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.platform.project.commons.Common;

public class LogOffPage {
	public WebDriver driver;
	
	public LogOffPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//*[@id=\"bodyContent\"]/h1")
	WebElement logOffPageHeader;
	
	@FindBy (id="tdb4")
	WebElement continueButton;

	
	// ###############################################################3
	
	public String getPageHeaderText() {
		return Common.getElementText(driver, logOffPageHeader);
	}
	
	public void clickContinueButton() {
		Common.clickElement(continueButton);
	}

}
