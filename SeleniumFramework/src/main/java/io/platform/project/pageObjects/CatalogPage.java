package io.platform.project.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import io.platform.project.commons.Common;
import io.platform.project.commons.ReadPropertyFile;
import io.platform.project.commons.WebDriverManager;

public class CatalogPage {
	public WebDriver driver;
	
	public CatalogPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//*[@id=\"bodyContent\"]/h1")
	WebElement catalogPageHeader;
	
	@FindBy (xpath = "//span[@class='greetUser']")
	WebElement userGreeting;
	
	@FindBy (linkText = "log yourself in")
	WebElement logYourselfInLink;

	@FindBy (id="tdb1")
	WebElement cartContentsButton;

	@FindBy (id="tdb2")
	WebElement checkoutButton;

	@FindBy (id="tdb3")
	WebElement myAccountButton;

	@FindBy (id="tdb4")
	WebElement logOffButton;

	@FindBy (linkText = "create an account")
	WebElement createAnAccountLink;

	@FindBy (css = "input[name='keywords']")
	WebElement quickFindTextBox;

	@FindBy (css = "input[title=' Quick Find ']")
	WebElement quickFindSearchButton;

	@FindBy (name = "manufacturers_id")
	WebElement manufacturersDropdown;

	// @FindBy (xpath = "//*[@id=\\'bodyContent\\']/div/div[2]/table")
	@FindBy (xpath = "//div[@class='contentText']//table")
	WebElement newProductsTable;

	@FindBy (xpath = "//*[@id=\\\"bodyContent\\\"]/div/div[2]/table/tbody/tr/td[1]")
	List<WebElement> newProductsTableRows;

	@FindBy (xpath = "//*[@id=\\\"bodyContent\\\"]/div/div[2]/table/tbody/tr[1]/td")
	List<WebElement> newProductsTableCols;

	// ##########################################################################

	public String getPageHeaderText() {
		return Common.getElementText(driver, catalogPageHeader);
	}
	
	public String getUserGreetingText() {
		return Common.getElementText(driver, userGreeting);
	}

	public void clickLogYourselfInLink() {
		Common.clickElement(logYourselfInLink);
	}

	public void clickCreateAnAccountLink() {
		Common.clickElement(createAnAccountLink);
	}

	public void fillQuickFind(String str) {
		Common.fillInElementTextBox(quickFindTextBox, str);
	}

	public void clickQuickFindSearchButton() {
		Common.clickElement(quickFindSearchButton);
	}

	public List<String> manufacturersDropdownTextValues() {
		List<String> aList = new ArrayList<String>();
		Select sel = new Select(manufacturersDropdown);
		List<WebElement> options = sel.getOptions();
		for (int i = 0; i < options.size(); i++) {
			aList.add(options.get(i).getText());
		}
		return aList;
	}
	
	public void selectManufacturer(String manufacturer) {
		List<String> aList = new ArrayList<String>();
		Select sel = new Select(manufacturersDropdown);
		  List<WebElement> options = sel.getOptions(); 
		  for (int i=0; i< options.size(); i++) { 
			  aList.add(options.get(i).getText());
			  }
		  if (aList.contains(manufacturer)) {
			  sel.selectByVisibleText(manufacturer);
		  } else {
			  System.out.println(manufacturer + " is not in the list");
			  throw new RuntimeException("Selection not in list of manufacturers");
		  }
	}

	public void clickCartContentsButton() {
		Common.clickElement(cartContentsButton);
	}

	public void clickCheckoutButton() {
		Common.clickElement(checkoutButton);
	}

	public void clickMyAccountButton() {
		Common.clickElement(myAccountButton);
	}
	
	public void clickLogOffButton() {
		Common.clickElement(logOffButton);
	}
	
	public void selectProduct(String productTitle) {
		WebElement anchor = newProductsTable.findElement(By.cssSelector("img[title='" + productTitle + "']"));
		anchor.click();
	}

	public void openHomePageURL(WebDriver driver){
		WebDriverManager.openURL(driver, ReadPropertyFile.getConfigPropertyVal("catalogPageURL"));
	}

}
