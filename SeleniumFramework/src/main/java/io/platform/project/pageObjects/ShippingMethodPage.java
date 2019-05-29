package io.platform.project.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.platform.project.commons.Common;

public class ShippingMethodPage {
	public WebDriver driver;
	
	public ShippingMethodPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//*[@id=\"bodyContent\"]/h1")
	WebElement shippingMethodPageHeader;

	@FindBy (id="tdb5")
	WebElement changeAddressButton;

	@FindBy (id="tdb6")
	WebElement continueButton;
	
	@FindBy (css = "textarea[name='comments']")
	WebElement commentsTextBox;
	
	// #################################################################
	
	public String getPageHeaderText() {
		return Common.getElementText(driver,shippingMethodPageHeader);
	}
	
	public void clickChangeAddressButton() {
		Common.clickElement(changeAddressButton);
	}
	
	public void clickContineButton() {
		Common.clickElement(continueButton);
	}
	
	public void fillInCommentsTextBox(String comments) {
		Common.fillInElementTextBox(commentsTextBox, comments);
	}

}
