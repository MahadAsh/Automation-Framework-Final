@Login
Feature: Login Functionality
...
Feature: Login Functionality

  # This meets the "Scenario Outline" requirement (Source: 31)
  Scenario Outline: specific users cannot login with invalid credentials
    Given I am on the login page
    When I enter username "<username>" and password "<password>"
    And I click the login button
    Then I should see the error message "<error>"

    Examples:
      | username       | password    | error                                     |
      | wronguser      | wrongpass   | Error: Incorrect login or password provided. |
      |                | 123456      | Error: Incorrect login or password provided. |
      
 Scenario: Login with credentials fetched from Excel
    Given I am on the login page
    When I enter credentials from Excel row 1
    And I click the login button
    Then I should see the error message "Error: Incorrect login or password provided."