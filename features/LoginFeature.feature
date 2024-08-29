Feature: Login Functionality

  Scenario: User logs in with valid username and password
    Given the user is on the login page
    When the user enters a valid username and password
    And the user clicks on the login button
    Then the user should be navigated to the home page

  Scenario: User logs in with invalid username and password
    Given the user is on the login page
    When the user enters an invalid username and password
    And the user clicks on the login button
    Then an error message should be displayed stating "Invalid username or password"

  Scenario: User attempts to login with empty username and password
    Given the user is on the login page
    When the user enters an empty username and an empty password
    And the user clicks on the login button
    Then an error message should be displayed stating "Username and password cannot be empty"

  Scenario: Submit button is disabled when credentials are empty
    Given the user is on the login page
    When the user has not entered username or password
    Then the submit button should be disabled

  Scenario: Validate alphanumeric values in input text boxes
    Given the user is on the input page with text boxes
    When the user inputs alphanumeric characters into the text boxes
    Then the input should be accepted without errors

  Scenario: Input text boxes should show error on empty space entry
    Given the user is on the input page with text boxes
    When the user enters only spaces in the text boxes
    Then an error message should be displayed stating "Input cannot be only spaces"