Feature: Checkout process on SauceDemo

  Scenario: Successful checkout with valid information
    Given User has added two products to the cart
    When User proceeds to checkout
    And User enters valid checkout information
    And User clicks the continue button
    Then User should proceed to the next step in checkout

  Scenario: Unsuccessful checkout with empty information
    Given User has added two products to the cart
    When User proceeds to checkout
    And User leaves the checkout information empty
    And User clicks the continue button
    Then User should see an error message on checkout page