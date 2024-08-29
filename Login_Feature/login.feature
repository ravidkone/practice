Feature: Login functionality verification

@HighPriority
Scenario: Valid user login
  Given the user is on the login page
  When the user enters the valid username "user1" and password "pass1"
  And the user clicks on the submit button
  Then the user should be navigated to the home page

@HighPriority
Scenario: Invalid user login
  Given the user is on the login page
  When the user enters an invalid username "invalid" and password "1234"
  And the user clicks on the submit button
  Then an error message should be displayed

@HighPriority
Scenario: Login with empty credentials
  Given the user is on the login page
  When the user leaves the username and password fields empty
  And the user clicks on the submit button
  Then an error message should be displayed

@MediumPriority
Scenario: Submit button state with empty fields
  Given the user is on the login page
  When the user leaves both the username and password fields empty
  Then the submit button should be in a disabled state

@MediumPriority
Scenario: Alphanumeric check for username
  Given the user is on the login page
  When the user enters a non-alphanumeric username "user@123"
  Then an error message should be displayed regarding the username

@MediumPriority
Scenario: Alphanumeric check for password
  Given the user is on the login page
  When the user enters a non-alphanumeric password "pass@123"
  Then an error message should be displayed regarding the password

@MediumPriority
Scenario: Empty space in username
  Given the user is on the login page
  When the user enters a space in the username field
  Then an error message should be displayed regarding the username

@MediumPriority
Scenario: Empty space in password
  Given the user is on the login page
  When the user enters a space in the password field
  Then an error message should be displayed regarding the password

@LowPriority
Scenario: Continuous alphanumeric input in fields
  Given the user is on the login page
  When the user continuously inputs alphanumeric characters "user123" in the username field and "pass123" in the password field
  Then the inputs should be accepted without error

@LowPriority
Scenario: Single character in each field
  Given the user is on the login page
  When the user enters a single character "u" in the username field and "p" in the password field
  Then the inputs should be accepted without error