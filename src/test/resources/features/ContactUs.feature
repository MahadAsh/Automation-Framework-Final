@Contact
Feature: Contact Us Form Submission

Scenario: User sends inquiry using Excel data
    Given I am on the contact us page
    When I submit the contact form using data from Excel row 0
    Then I should see the success message "Your enquiry has been successfully sent to the store owner!"