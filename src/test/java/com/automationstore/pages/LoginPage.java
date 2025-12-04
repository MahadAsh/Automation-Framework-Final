package com.automationstore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    // 1. Locators (These match the HTML IDs on the website)
    private By loginNameField = By.id("loginFrm_loginname");
    private By passwordField = By.id("loginFrm_password");
    private By loginButton = By.xpath("//button[@title='Login']");
    private By errorMessage = By.cssSelector(".alert-danger");

    // 2. Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // 3. Actions (Methods to interact with the page)
    public void enterUsername(String username) {
        driver.findElement(loginNameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
    
    public String getPageTitle() {
        return driver.getTitle();
    }
}