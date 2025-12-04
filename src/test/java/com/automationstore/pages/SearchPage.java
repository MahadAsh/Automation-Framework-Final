package com.automationstore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {
    private WebDriver driver;

    // Locators for Search
    private By searchBox = By.id("filter_keyword");
    private By searchButton = By.className("button-in-search");
    private By searchResultHeader = By.className("maintext"); 

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearchKeyword(String keyword) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(keyword);
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }

    public String getSearchResultHeader() {
        // This returns the text like "Search: 'makeup'"
        return driver.findElement(searchResultHeader).getText();
    }
}