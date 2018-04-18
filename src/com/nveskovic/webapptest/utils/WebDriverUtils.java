package com.nveskovic.webapptest.utils;

import org.openqa.selenium.WebElement;

public class WebDriverUtils {

	public static void setText(WebElement e, String text) {
		e.clear();
		e.sendKeys(text);
	}
}
