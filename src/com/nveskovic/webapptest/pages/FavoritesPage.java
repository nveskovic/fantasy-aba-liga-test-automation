package com.nveskovic.webapptest.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FavoritesPage {

	private WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(FavoritesPage.class);
	
	public FavoritesPage(WebDriver driver) {
		this.driver = driver;
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
			String href = e.findElement(By.xpath("td/a[1]")).getAttribute("href");
			//String title = e.findElement(By.xpath("td/div")).getText();
			String price = e.findElement(By.xpath("td[6]/a")).getText().trim();
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
	
	
	private List<WebElement> getRowsFromResultsTable() {
		return driver.findElements(By.xpath("//tr[starts-with(@id, 'tr_')]"));
	}
}
