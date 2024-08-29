Feature: User login validation

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
  When the user enters an empty username and password
  And the user clicks on the submit button
  Then an error message should be displayed