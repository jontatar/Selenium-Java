package io.platform.project.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.platform.project.commons.Common;

public class SuccessPage {
	public WebDriver driver;
	
	public SuccessPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//*[@id=\"bodyContent\"]/h1")
	WebElement successPageHeader;

	@FindBy (id="tdb5")
	WebElement continueButton;
	
	@FindBy (css = "input[name='notify[]']")
	WebElement notifyCheckbox;
	
	// ###############################################################3
	
	public String getPageHeaderText() {
		return Common.getElementText(driver,successPageHeader);
	}
	
	public void clickContinueButton() {
		Common.clickElement(continueButton);
	}
	
	public void clickNotifyCheckbox(Boolean check) {
		if (check && ! notifyCheckbox.isSelected()) {
			Common.clickElement(notifyCheckbox);
		}
	}

}
