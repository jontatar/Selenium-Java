package io.platform.project.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.platform.project.commons.Common;

public class CartContentsPage {
	public WebDriver driver;
	
	public CartContentsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//*[@id=\"bodyContent\"]/h1")
	WebElement catalogPageHeader;
	
	@FindBy (css = "input[name='cart_quantity[]'")
	WebElement productQuantityTextBox;

	/*
	 * @FindBy (id="tdb1") WebElement cartContentsButton;
	 * 
	 * @FindBy (id="tdb2") WebElement checkoutButton;
	 * 
	 * @FindBy (id="tdb3") WebElement myAccountButton;
	 * 
	 * @FindBy (id="tdb4") WebElement logOffButton;
	 */

	@FindBy (id="tdb5")
	WebElement updateButton;

	@FindBy (id="tdb6")
	WebElement checkoutButton;
	
	@FindBy (linkText = "remove")
	WebElement removeProductLink;
	
	// #################################################################
	
	public String getPageHeaderText() {
		return Common.getElementText(driver, catalogPageHeader);
	}
	
	public void clickUpdateButton() {
		Common.clickElement(updateButton);
	}
	
	public void clickCheckoutButton() {
		Common.clickElement(checkoutButton);
	}

}
