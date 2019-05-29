package io.platform.project.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.platform.project.commons.Common;

public class ConfirmationPage {
	public WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//*[@id=\"bodyContent\"]/h1")
	WebElement checkoutPageHeader;
	
	@FindBy (id="tdb5")
	WebElement confirmOrderButton;
	
	// ###############################################################3
	
	public String getPageHeaderText() {
		return Common.getElementText(driver, checkoutPageHeader);
	}
	
	public void clickConfirmOrderButton() {
		Common.clickElement(confirmOrderButton);
	}

}
