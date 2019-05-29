package io.platform.project.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.platform.project.commons.Common;

public class PasswordForgottenPage {
	public WebDriver driver;
	
	private static Logger logger = Logger.getLogger(PasswordForgottenPage.class);
	
	public PasswordForgottenPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//*[@id=\"bodyContent\"]/h1")
	WebElement passwordForgottenPageHeader;

	@FindBy (id="tdb4")
	WebElement continueButton;

	@FindBy (id="tdb5")
	WebElement backButton;
	
	@FindBy (css = "input[name='email_address']")
	WebElement emailAddressTextBox;
	
	@FindBy (className="messageStackError")
	WebElement errorMessageCell;
	
	@FindBy (className="contentText")
	WebElement successContent;
	
	// ###############################################################3

	public String getPageHeaderText() {
		return Common.getElementText(driver, passwordForgottenPageHeader);
	}
	
	public void fillInEmailAddressTextBox(String emailAddress) {
		if (Common.isValidEmail(emailAddress)) {
			Common.fillInElementTextBox(emailAddressTextBox, emailAddress);
		} else {
			logger.info("Invalid password: " + emailAddress);
		}
	}
	
	public void clickContinueButton() {
		Common.clickElement(continueButton);
	}
	
	public void clickBackButton() {
		Common.clickElement(backButton);
	}
	
	public String getErrorMessageText() {
		return Common.getElementText(driver, errorMessageCell);
	}
	
	public String getSuccessContentText() {
		return Common.getElementText(driver, successContent);
	}
	

}
