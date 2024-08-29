Feature: User Login Functionality

Scenario: User login with valid credentials
Given User is at the login page
When User enters valid username 'user123' and password 'pass123'
And User clicks on the login button
Then User should be redirected to the home page

Scenario: User login with invalid credentials
Given User is at the login page
When User enters invalid username 'user321' and password 'pass321'
And User clicks on the login button
Then an error message should be displayed

Scenario: User login with empty credentials
Given User is at the login page
When User leaves both username and password fields empty
And User clicks on the login button
Then an error message should be displayed

Scenario: Submit button state with empty credentials
Given User is at the login page
When User leaves both username and password fields empty
Then Submit button should be disabled

Scenario: Validate alphanumeric values in input text boxes
Given User is at the login page
When User enters non-alphanumeric characters in username and password fields
And User tries to submit
Then an error message should be displayed

Scenario: Validate empty space in input text boxes
Given User is at the login page
When User enters multiple spaces in username and password fields
And User clicks on the login button
Then an error message should be displayed