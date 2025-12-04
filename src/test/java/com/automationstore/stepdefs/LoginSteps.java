package com.automationstore.stepdefs;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.automationstore.utils.ExcelUtils;
import java.io.IOException;
import com.automationstore.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;

    // This runs BEFORE every scenario
    @Before("@Login")
    public void setup() {
        // Selenium 4.27 automatically handles the driver setup!
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        // Initialize the Page Object
        loginPage = new LoginPage(driver);
    }

    // This matches the "Given" line in the Feature file
    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver.get("https://automationteststore.com/index.php?rt=account/login");
    }

    // This matches the "When" line
    @When("I enter username {string} and password {string}")
    public void i_enter_credentials(String user, String pass) {
        // Handle empty strings for the 2nd example in feature file
        if(user == null) user = "";
        
        loginPage.enterUsername(user);
        loginPage.enterPassword(pass);
    }

    @And("I click the login button")
    public void i_click_login() {
        loginPage.clickLogin();
    }

    @Then("I should see the error message {string}")
    public void i_should_see_error(String expectedError) {
        String actualError = loginPage.getErrorMessage();
        // Assertion: Checks if the website actually showed the error
        assertTrue("Error message did not match!", actualError.contains(expectedError));
    }
    
    @When("I enter credentials from Excel row {int}")
    public void i_enter_credentials_from_excel(int rowIndex) throws IOException {
        // Use the NEW method: getLoginData
        String[] creds = com.automationstore.utils.ExcelUtils.getLoginData(rowIndex); 
        
        String username = creds[0];
        String password = creds[1];

        System.out.println("Fetching from Excel: " + username + " / " + password);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }
    // This runs AFTER every scenario
    @After("@Login")
    public void tearDown(Scenario scenario) {
        // 1. Check if the scenario failed
        if (scenario.isFailed()) {
            // 2. Take a screenshot
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            
            // 3. Attach it to the Allure Report
            scenario.attach(screenshot, "image/png", "Failed Scenario Screenshot");
        }
        
        // 4. Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}