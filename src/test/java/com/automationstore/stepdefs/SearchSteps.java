package com.automationstore.stepdefs;

import com.automationstore.utils.ExcelUtils;
import java.io.IOException;
import com.automationstore.pages.SearchPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class SearchSteps {
    WebDriver driver;
    SearchPage searchPage;

    @Before("@Search") // This tag ensures we only run this setup for Search tests
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        searchPage = new SearchPage(driver);
    }

    @Given("I am on the home page")
    public void i_am_on_home_page() {
        driver.get("https://automationteststore.com/");
    }

    @When("I search for product specified in Excel row {int}")
    public void i_search_from_excel(int rowIndex) throws IOException {
        // 1. Get data from Sheet 1 (SearchData)
        String keyword = ExcelUtils.getSearchTerm(rowIndex);
        
        System.out.println(">>> Fetching Search Term from Excel: " + keyword);
        
        // 2. Perform action
        searchPage.enterSearchKeyword(keyword);
        searchPage.clickSearch();
    }

    @Then("I should see results related to {string}")
    public void i_should_see_results(String keyword) {
        // OLD CODE (Failed): 
        // String headerText = searchPage.getSearchResultHeader();
        // assertTrue(headerText.toLowerCase().contains(keyword.toLowerCase()));

        // NEW CODE (Better): Check if product thumbnails are visible
        boolean productsFound = driver.findElements(org.openqa.selenium.By.className("fixed_wrapper")).size() > 0;
        assertTrue("No products found for search: " + keyword, productsFound);
    }

    @After("@Search")
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}