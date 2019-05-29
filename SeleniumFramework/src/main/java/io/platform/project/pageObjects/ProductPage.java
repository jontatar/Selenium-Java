package io.platform.project.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.platform.project.commons.Common;

public class ProductPage {
	public WebDriver driver;
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//*[@id=\"bodyContent\"]/h1")
	WebElement productPageHeader;
	
	@FindBy (css = "button[type='submit']")
	WebElement addToCartButton;
	
	// #################################################################
	
	public String getPageHeaderText() {
		return Common.getElementText(driver, productPageHeader);
	}
	
	public void clickAddToCartButton() {
		Common.clickElement(addToCartButton);
	}

}
