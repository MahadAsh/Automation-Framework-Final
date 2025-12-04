package com.automationstore.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    // 1. Where are the Feature files?
    features = "src/test/resources/features",
    
    // 2. Where is the Java glue code?
    glue = "com.automationstore.stepdefs",
    
    // 3. Plugins: 'pretty' for console output, 'Allure' for the HTML report
    plugin = {
        "pretty",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    },
    
    // 4. Make console output readable
    monochrome = true
)
public class TestRunner {
    // This class remains empty. 
    // It is just a holder for the annotations above.
}