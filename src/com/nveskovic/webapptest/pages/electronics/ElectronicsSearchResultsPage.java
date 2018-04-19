package com.nveskovic.webapptest.pages.electronics;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nveskovic.webapptest.pages.FavoritesPage;

public class ElectronicsSearchResultsPage {
	private WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(ElectronicsSearchResultsPage.class);
	
	public ElectronicsSearchResultsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy (className = "msg_column_td")
	WebElement priceTableHeader;
	
	@FindBy (className = "filter_second_line_dv")
	WebElement filtersHeader;

	@FindBy (id = "page_main")
	WebElement searchResultsTable;
	
	@FindBy (id = "a_fav_sel")
	WebElement addToMemoLink;
	
	@FindBy (id = "alert_ok")
	WebElement alertOkButton;
	
	@FindBy (xpath = "//a[@class = 'a_menu'][contains(@href, 'favorites')]")
	WebElement favoritesLink;

	public ElectronicsSearchResultsPage clickOnPriceLinkInTableHeader() {
		this.priceTableHeader.findElement(By.tagName("a")).click();
		return PageFactory.initElements(driver, ElectronicsSearchResultsPage.class);
	}

	public ElectronicsSearchResultsPage selectTypeOfItemsByName(String name) {
		WebElement elem = this.filtersHeader.findElements(By.tagName("select")) 	// locates all filter dropdowns
								.get(2);											// locates Type of items dropdown
		
		Select select = new Select(elem);
		select.selectByVisibleText(name);
		
		return PageFactory.initElements(driver, ElectronicsSearchResultsPage.class);
	}

    public void clickOnLinkByLinkTest(String text) {
		driver.findElement(By.linkText(text)).click();
    }

    /**
     * Collects the data of each item, and pack it into a JSON string
     * @return
     */
	public ArrayList<String> getAllResultsFromPage() {
		ArrayList<String> toReturn = new ArrayList<String>();
		List<WebElement> elements = getRowsFromResultsTable();
		for (WebElement e : elements) {
			String tr_id = e.getAttribute("id");
			//System.out.println("Iterration " + tr_id);
			String href = e.findElement(By.xpath("td/a[1]")).getAttribute("href");
			//String title = e.findElement(By.xpath("td/div[@class='d1']")).getText();
			String price = e.findElement(By.xpath("td[@class='msga2-o pp6']")).getText().trim();
			String json = "{"
					+ "id: \"" + tr_id + "\", "
					+ "href: \"" + href + "\", "
					//+ "title: \"" + title + "\", "
					+ "price: \"" + price + "\""
					+ "}";
			toReturn.add(json);
		}
		return toReturn;
	}

	public void clickOnItemCheckboxByIndex(int index) {
		getRowsFromResultsTable().get(index).findElement(By.tagName("input")).click();
	}
	
	public boolean getStateOfItemCheckboxByIndex(int index) {
		return getRowsFromResultsTable().get(index).findElement(By.tagName("input")).isSelected();
	}

	public void clickOnAddToMemoLink() {
		this.addToMemoLink.click();
	}
	
	public void clickOnAlertOKButton() {
		this.alertOkButton.click();
	}
	
	public FavoritesPage clickOnFavoritesLink() {
		this.favoritesLink.click();
		return PageFactory.initElements(driver, FavoritesPage.class);
	}
	
	
	private List<WebElement> getRowsFromResultsTable() {
		return searchResultsTable.findElement(By.tagName("table"))
				.findElements(By.xpath("//tr[starts-with(@id, 'tr_')][contains(@style,'cursor')]"));
	}
}
