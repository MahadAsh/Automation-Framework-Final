package com.automationstore.stepdefs;

import org.openqa.selenium.chrome.ChromeOptions;
import com.automationstore.utils.ExcelUtils;
import java.io.IOException;
import com.automationstore.pages.ContactUsPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class ContactUsSteps {
    WebDriver driver;
    ContactUsPage contactPage;

 // Only run this setup for Scenarios tagged with @Contact
    @Before("@Contact")
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Runs without a GUI (Crucial for GitHub)
        options.addArguments("--window-size=1920,1080"); // Ensures elements are visible
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        driver = new ChromeDriver(options); // Pass the options here
        
        // driver.manage().window().maximize(); // Not needed in headless mode
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        contactPage = new ContactUsPage(driver);
    }
    @Given("I am on the contact us page")
    public void i_am_on_contact_page() {
        driver.get("https://automationteststore.com/index.php?rt=content/contact");
    }

    @When("I submit the contact form using data from Excel row {int}")
    public void i_submit_form_excel(int rowIndex) throws IOException {
        // 1. Get data from Sheet 2 (ContactData)
        String[] data = ExcelUtils.getContactData(rowIndex);
        String name = data[0];
        String email = data[1];
        String enquiry = data[2];

        System.out.println(">>> Fetching Contact Data: " + name + " / " + email);

        // 2. Perform action
        contactPage.enterName(name);
        contactPage.enterEmail(email);
        contactPage.enterInquiry(enquiry);
        contactPage.clickSubmit();
    }

    @Then("I should see the success message {string}")
    public void i_should_see_success(String expectedMsg) {
        String actualMsg = contactPage.getSuccessText();

        // DEBUG: Prints the exact text found on the website to the Console
        System.out.println(">>> DEBUG ACTUAL TEXT FOUND: " + actualMsg);
        System.out.println(">>> DEBUG EXPECTED TEXT:     " + expectedMsg);

        // Assertion: Checks if the big text block contains our specific sentence
        assertTrue("Error! Expected message '" + expectedMsg + "' was not found inside: " + actualMsg,
                actualMsg.contains(expectedMsg));
    }

    @After("@Contact")
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}