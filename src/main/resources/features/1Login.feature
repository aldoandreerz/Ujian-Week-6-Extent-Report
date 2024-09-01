Feature: Login functionality on SauceDemo

  Scenario: Successful login with valid credentials
    Given User is on login page for login
    When User enters valid username and password for login
    And User clicks login button on login page
    Then User should be redirected to the product page

  Scenario: Unsuccessful login with invalid username
    Given User is on login page for login
    When User enters invalid username and valid password
    And User clicks login button
    Then User should see an error message for login

  Scenario: Unsuccessful login with invalid password
    Given User is on login page for login
    When User enters valid username and invalid password for login
    And User clicks login button
    Then User should see an error message for login

  Scenario: Unsuccessful login with empty username and password
    Given User is on login page for login
    When User enters empty username and empty password for login
    And User clicks login button
    Then User should see an error message for login
