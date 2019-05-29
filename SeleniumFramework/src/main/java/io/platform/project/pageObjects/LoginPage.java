package io.platform.project.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.platform.project.commons.Common;

public class LoginPage {
	public WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//*[@id=\"bodyContent\"]/h1")
	WebElement loginPageHeader;
	
	@FindBy (className = "messageStackError")
	WebElement signInError;
	
//	@FindBy (id="tdb1")
//	WebElement cartContentsButton;
//
//	@FindBy (id="tdb2")
//	WebElement checkoutButton;
//
//	@FindBy (id="tdb3")
//	WebElement myAccountButton;
	
	@FindBy (id="tdb4")
	WebElement continueButton;
	
	@FindBy (id="tdb5")
	WebElement signInButton;
	
	@FindBy (name="email_address")
	WebElement emailAddressTextBox;
	
	@FindBy (name="password")
	WebElement passwordTextBox;
	
	@FindBy (linkText = "Password forgotten? Click here.")
	WebElement forgotPasswordLink;
	
	// ####################################################################################
	
	public String getPageHeaderText() {
		return Common.getElementText(driver, loginPageHeader);
	}
	
	public void fillInEmailAddressTextBox(String emailAddress) {
		Common.fillInElementTextBox(emailAddressTextBox, emailAddress);
	}
	
	
	public void fillInPasswordTextBox(String pwd) {
		Common.fillInElementTextBox(passwordTextBox, pwd);
	}
	
	public void clickOnSignInButton() {
		Common.clickElement(signInButton);
	}
	
	public String getSignInErrorMessage() {
		return Common.getElementText(driver, signInError);
	}
	
	public void clickForgotPasswordLink() {
		Common.clickElement(forgotPasswordLink);
	}

}
