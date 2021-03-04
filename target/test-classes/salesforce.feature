Feature: Testing some salesforce options Salesforce

  Background:
    Given user is on salesforce page
    When user enter user and password
    And user submits
    Then homepage is be display

  Scenario: Create an object
    Given homepage is be display
    And user click setup
    And click custom object
    When user fill the form data
    Then a custom object is created
