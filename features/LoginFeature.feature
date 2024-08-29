Feature: User Login Feature

  Scenario: User login with valid credentials
    Given the user is on the login page
    When the user enters the username "validUser" and password "validPass"
    And the user clicks on the submit button
    Then the user should be navigated to the home page

  Scenario: User login with invalid credentials
    Given the user is on the login page
    When the user enters the username "invalidUser" and password "invalidPass"
    And the user clicks on the submit button
    Then an error message should be displayed

  Scenario: User login with empty credentials
    Given the user is on the login page
    When the user leaves both username and password fields empty
    And the user clicks on the submit button
    Then an error message should be displayed

  Scenario: Verify submit button state with empty credentials
    Given the user is on the login page
    When the user leaves both username and password fields empty
    Then the submit button should be in a disabled state

  Scenario: Validate alphanumeric input in text boxes
    Given the user is on the login page
    When the user enters the username "user123" and password "pass123"
    And the user clicks on the submit button
    Then the data should be accepted and submitted

  Scenario: Validate error on empty space in text boxes
    Given the user is on the login page
    When the user enters only spaces in both username and password fields
    And the user clicks on the submit button
    Then an error message should be displayed

  Scenario: Validate special characters input rejection in text boxes
    Given the user is on the login page
    When the user enters special characters "user@#%" and "pass@#%" in the username and password fields respectively
    And the user clicks on the submit button
    Then an error message about invalid characters should be displayed

  Scenario: Validate max length of username and password fields
    Given the user is on the login page
    When the user enters 51 characters in both username and password fields
    And the user clicks on the submit button
    Then an error message about max character limit should be displayed

  Scenario: Validate correct error message for empty credentials
    Given the user is on the login page
    When the user leaves both username and password fields empty
    And the user clicks on the submit button
    Then a specific error message for empty credentials should be displayed

  Scenario: Validate that input is case-sensitive
    Given the user is on the login page
    When the user enters username "USER" and password "PASS" in uppercase
    And the user clicks on the submit button
    Then an error message or successful login if applicable should be displayed