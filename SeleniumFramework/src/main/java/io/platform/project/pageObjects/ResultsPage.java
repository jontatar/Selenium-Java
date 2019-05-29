package io.platform.project.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.platform.project.commons.Common;

public class ResultsPage {
	public WebDriver driver;
	public WebElement elem;
	
	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//*[@id=\"bodyContent\"]/h1")
	WebElement resultsPageHeader;
	
	@FindBy (xpath = "//div[@class='ui-widget-content ui-corner-bottom productListTable']//tr")
	List<WebElement> tableRows;

	@FindBy (xpath = "//*[@id=\\\'bodyContent\\\']/div/div[1]/div[1]/div[2]/table/tbody/tr[1]/td")
	List<WebElement> tableCols;
	
	@FindBy (xpath = "//u[contains(text(),'[Next >>]')]")
	WebElement nextPageLink;

	
	// ##################################################################
	
	// Returns the header of the page
	public String getPageHeaderText() {
		return Common.getElementText(driver,resultsPageHeader);
	}

	public List<String> getFirstPageResultsTitles() {
		List<String> aList = new ArrayList<String>();

		for (int i = 0; i < tableRows.size(); i++) {
			WebElement row = tableRows.get(i);
			WebElement cellData = row.findElement(By.xpath("td[1]/a/img"));
			aList.add(cellData.getAttribute("title"));
		}
		return aList;
	}

	public List<String> getAllResultsTitles() {
		List<String> aList = new ArrayList<String>();
		Boolean moreResults = true;

		while (moreResults) {
			for (int i = 0; i < tableRows.size(); i++) {
				WebElement row = tableRows.get(i);
				WebElement cellData = row.findElement(By.xpath("td[1]/a/img"));
				aList.add(cellData.getAttribute("title"));
			}
			try {
				Common.clickElement(nextPageLink);
			} catch (Exception e) {
				moreResults = false;
			}
		}
		return aList;
	}

	public List<String> getFirstPageResultsLinks() {
		List<String> aList = new ArrayList<String>();

		for (int i = 0; i < tableRows.size(); i++) {
			WebElement row = tableRows.get(i);
			WebElement cellData = row.findElement(By.xpath("td[1]/a/img"));
			aList.add(cellData.getAttribute("src"));
		}

		return aList;
	}
	
	public List<String> getAllResultsLinks() {
		List<String> aList = new ArrayList<String>();
		Boolean moreResults = true;

		while (moreResults) {
			for (int i = 0; i < tableRows.size(); i++) {
				WebElement row = tableRows.get(i);
				WebElement cellData = row.findElement(By.xpath("td[1]/a/img"));
				aList.add(cellData.getAttribute("src"));
			}
			try {
				Common.clickElement(nextPageLink);
			} catch (Exception e) {
				moreResults = false;
			}
		}
		return aList;
	}

}
