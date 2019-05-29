package io.platform.project.pageObjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import io.platform.project.commons.Common;

public class CreateAccountPage {
	public WebDriver driver;
	private String pwd;
	private String confPwd;
	
	private static Logger logger = Logger.getLogger(CreateAccountPage.class);
	
	public CreateAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//*[@id=\"bodyContent\"]/h1")
	WebElement createAccountPageHeader;
	
	@FindBy (css = "input[value='m']")
	WebElement maleRadioButton;
	
	@FindBy (css = "input[value='f']")
	WebElement femaleRadioButton;
	
	@FindBy (name = "firstname")
	WebElement firstNameTextBox;
	
	@FindBy (name = "lastname")
	WebElement lastNameTextBox;
	
	@FindBy (id="dob")
	WebElement dobTextBox;
	
	@FindBy (className = "ui-datepicker-month")
	WebElement dobMonthPicker;
	
	@FindBy (className = "ui-datepicker-year")
	WebElement dobYearPicker;
	
	@FindBy (className = "ui-datepicker-calendar")
	WebElement calendarTable;
	
	@FindBy (name = "email_address")
	WebElement emailAddressTextBox;
	
	@FindBy (name = "company")
	WebElement companyNameTextBox;
	
	@FindBy (name = "street_address")
	WebElement streetAddressTextBox;
	
	@FindBy (name = "suburb")
	WebElement suburbTextBox;
	
	@FindBy (name = "postcode")
	WebElement postCodeTextBox;
	
	@FindBy (name = "city")
	WebElement cityTextBox;
	
	@FindBy (name = "state")
	WebElement stateTextBox;
	
	@FindBy (name = "country")
	WebElement countryDropdown;
	
	@FindBy (name = "telephone")
	WebElement telephoneTextBox;
	
	@FindBy (name = "fax")
	WebElement faxNumberTextBox;
	
	@FindBy (name = "newsletter")
	WebElement newsletterBox;
	
	@FindBy (name = "password")
	WebElement passwordTextBox;
	
	@FindBy (name = "confirmation")
	WebElement passwordConfirmationTextBox;
	
	@FindBy (id="tdb4")
	WebElement continueButton;

	@FindBy (className = "messageStackError")
	WebElement existingAccountError;
	
	// ##############################################################################################
	
	public String getPageHeaderText() {
		return Common.getElementText(driver, createAccountPageHeader);
	}
	
	public void clickMaleRadioButton() {
		Common.clickElement(maleRadioButton);
	}
	
	public void clickFemaleRadioButton() {
		Common.clickElement(femaleRadioButton);
	}
	public void fillInFirstName(String firstname) {
		Common.fillInElementTextBox(firstNameTextBox, firstname);
	}

	public void fillInLastName(String lastname) {
		Common.fillInElementTextBox(lastNameTextBox, lastname);
	}

	public void fillInDoB(String dob) {
		Common.fillInElementTextBox(dobTextBox, dob);
	}

	public void fillInEmailAddress(String emailAddress) {
		Common.fillInElementTextBox(emailAddressTextBox, emailAddress);
	}

	public void fillInCompanyName(String companyName) {
		Common.fillInElementTextBox(companyNameTextBox, companyName);
	}

	public void fillInStreetAddressTextBox(String streetAddress) {
		Common.fillInElementTextBox(streetAddressTextBox, streetAddress);
	}

	public void fillInSuburbTextBox(String suburb) {
		Common.fillInElementTextBox(suburbTextBox, suburb);
	}

	public void fillInPostCodeTextBox(String postCode) {
		Common.fillInElementTextBox(postCodeTextBox, postCode);
	}

	public void fillInCityTextBox(String city) {
		Common.fillInElementTextBox(cityTextBox, city);
	}

	public void fillInStateTextBox(String state) {
		Common.fillInElementTextBox(stateTextBox, state);
	}

	public void fillInCountryDropdown(String country) {
		List<String> aList = new ArrayList<String>();
		Select sel = new Select(countryDropdown);
		  List<WebElement> options = sel.getOptions(); 
		  for (int i=0; i< options.size(); i++) { 
			  aList.add(options.get(i).getText());
			  }
		  if (aList.contains(country)) {
			  sel.selectByVisibleText(country);
		  } else {
			  System.out.println(country + " is not in the list of countries");
			  throw new RuntimeException("Selection not in list of countries");
		  }
	}

	public void fillInTelephoneNumberTextBox(String phoneNumber) {
		Common.fillInElementTextBox(telephoneTextBox, phoneNumber);
	}

	public void fillInFaxNumberTextBox(String faxNumber) {
		Common.fillInElementTextBox(faxNumberTextBox, faxNumber);
	}

	public void clickNewletterCheckbox(Boolean check) {
		if (check) {
			if (! newsletterBox.isSelected()) {
				Common.clickElement(newsletterBox);
			}
		}
	}

	public void fillInPasswordTextBox(String password) {
		this.pwd = password;
		Common.fillInElementTextBox(passwordTextBox, password);
	}

	public void fillInPasswordConfirmationTextBox(String passwordConfirm) {
		this.confPwd = passwordConfirm;
		Common.fillInElementTextBox(passwordConfirmationTextBox, passwordConfirm);
	}

	public void clickContinueButton() {
		// Verify password and passwordConfirmation are the same
		if (pwd.equals(confPwd)) {
			Common.clickElement(continueButton);
		} else {
			logger.info("Password and Password Confirmation are not the same.");
		}
	}
	
	public void clickDoBMonthPicker(String month) {
		Common.clickElement(dobTextBox);
		Select sel = new Select(dobMonthPicker);
		sel.selectByValue(month);
	}
	
	public void clickDoBYearPicker(String year) {
		Common.clickElement(dobTextBox);
		Select sel = new Select(dobYearPicker);
		sel.selectByValue(year);
	}
	
	public void clickDoBDayPicker(String day) {
		List<WebElement> aList = calendarTable.findElements(By.tagName("td"));
		for (WebElement cell : aList) {
			if (cell.getText().equals(day)) {
				cell.click();
				break;
			}
		}
	}
	
	public Boolean isValidDate(String strDate) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(strDate);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public void setDoB(String dateString) {
		if (isValidDate(dateString)) {
			String[] dateArray = dateString.split("/");
			int aMonth = Integer.valueOf(dateArray[0]) -1;
			int aDay = Integer.valueOf(dateArray[1]);
			int aYear = Integer.valueOf(dateArray[2]);
			if (aYear >= 1919 && aYear <= 2019) {		
				clickDoBMonthPicker(String.valueOf(aMonth));
				clickDoBYearPicker(String.valueOf(aYear));
				clickDoBDayPicker(String.valueOf(aDay));
			} else {
				logger.info("Year out of range");
			}
		} else {
			logger.info("Invalid Date of Birth");
		}
		
	}
	
	public String getSignInErrorMessage() {
		return Common.getElementText(driver, existingAccountError);
	}

}
