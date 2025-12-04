package com.automationstore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage {
    private WebDriver driver;

    // Locators
    private By firstNameField = By.id("ContactUsFrm_first_name");
    private By emailField = By.id("ContactUsFrm_email");
    private By inquiryField = By.id("ContactUsFrm_enquiry");
    private By submitButton = By.xpath("//button[@title='Submit']");
    
    // UPDATED LOCATOR: Grabs the entire container (.mb40)
    // This captures ALL text in the result area, preventing "Element Not Found" errors.
    private By successMessage = By.cssSelector(".mb40");

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterName(String name) {
        driver.findElement(firstNameField).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterInquiry(String text) {
        driver.findElement(inquiryField).sendKeys(text);
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    public String getSuccessText() {
        // This will now return all text in the success box, 
        // e.g. "Contact Us \n Your inquiry has been successfully sent..."
        return driver.findElement(successMessage).getText();
    }
}