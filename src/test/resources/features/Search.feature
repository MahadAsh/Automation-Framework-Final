@Search
Feature: Search Functionality

  Scenario Outline: Customer can search for products
    Given I am on the home page
    When I search for "<product>"
    Then I should see results related to "<product>"

    Examples:
      | product   |
      | makeup    |
      | fragrance |
      
Scenario: Search using data from Excel
    Given I am on the home page
    When I search for product specified in Excel row 0
    Then I should see results related to "makeup"