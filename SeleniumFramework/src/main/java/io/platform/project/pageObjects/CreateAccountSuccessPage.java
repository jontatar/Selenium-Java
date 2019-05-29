package io.platform.project.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.platform.project.commons.Common;

public class CreateAccountSuccessPage {
	public WebDriver driver;
	
	public CreateAccountSuccessPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//*[@id=\"bodyContent\"]/h1")
	WebElement createAccountSuccessPageHeader;

	@FindBy (id="tdb5")
	WebElement continueButton;
	
	// ###################################################################
	
	public String getPageHeaderText() {
		return Common.getElementText(driver, createAccountSuccessPageHeader);
	}
	
	public void clickContinueButton() {
		Common.clickElement(continueButton);
	}
	
	
	

}
