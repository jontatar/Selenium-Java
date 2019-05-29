package io.platform.project.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.platform.project.commons.Common;

public class PaymentMethodPage {
	public WebDriver driver;
	
	public PaymentMethodPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//*[@id=\"bodyContent\"]/h1")
	WebElement paymentMethodPageHeader;
	
	@FindBy (id="tdb5")
	WebElement changeAddressButton;

	@FindBy (id="tdb6")
	WebElement continueButton;
	
	@FindBy (css = "input[value='cod']")
	WebElement codRadioButton;
	
	@FindBy (css = "input[value='paypal-express']")
	WebElement payPalRadioButton;

	@FindBy (css = "textarea[name='comments']")
	WebElement commentsTextBox;
	
	// ###############################################################3
	
	public String getPageHeaderText() {
		return Common.getElementText(driver, paymentMethodPageHeader);
	}
	
	public void clickChangeAddressButton() {
		Common.clickElement(changeAddressButton);
	}
	
	public void clickContinueButton() {
		Common.clickElement(continueButton);
	}
	
	public void clickCODRadioButton() {
		Common.clickElement(codRadioButton);
	}
	
	public void clickPayPalRadioButton() {
		Common.clickElement(payPalRadioButton);
	}
	
	public  void fillInCommentsTextBox(String comments) {
		Common.fillInElementTextBox(commentsTextBox, comments);
	}
	
}
