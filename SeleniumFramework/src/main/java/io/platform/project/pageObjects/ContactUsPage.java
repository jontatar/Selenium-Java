package io.platform.project.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.platform.project.commons.Common;
import io.platform.project.commons.ReadPropertyFile;
import io.platform.project.commons.WebDriverManager;

public class ContactUsPage {

	public WebDriver driver;
	private static Logger logger = Logger.getLogger(ContactUsPage.class);
	
	public ContactUsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//*[@id=\"bodyContent\"]/h1")
	WebElement successPageHeader;

	@FindBy (css = "input[name='name']")
	WebElement nameTextBox;
	
	@FindBy (css = "input[name='email']")
	WebElement emailTextBox;
	
	@FindBy (css = "textarea[name='enquiry']")
	WebElement enquiryTextBox;
	
	@FindBy (id="tdb4")
	WebElement continueButton;
	
	@FindBy (className = "contentText")
	WebElement successString;

	
	// ###############################################################3
	
	public String getPageHeaderText() {
		return Common.getElementText(driver, successPageHeader);
	}
	
	public void fillInNameTextBox(String fullName) {
		Common.fillInElementTextBox(nameTextBox, fullName);
	}
	
	
	public void fillInEmailTextBox(String emailAddress) {
		if (Common.isValidEmail(emailAddress)) {
			Common.fillInElementTextBox(emailTextBox, emailAddress);
		} else {
			logger.info("Malformed email address" );
			throw new RuntimeException("Malformed email address");
		}
	}
	
	public void fillInEnquiryTextBox(String comments) {
		Common.fillInElementTextBox(enquiryTextBox, comments);
	}
	
	public void clickContinueButton() {
		Common.clickElement(continueButton);
	}
	
	public String getSuccessStringText() {
		return Common.getElementText(driver, successString);
	}

	public void openContactUsPageURL(WebDriver driver){
		WebDriverManager.openURL(driver, ReadPropertyFile.getConfigPropertyVal("contactUsPageURL"));
	}

}
