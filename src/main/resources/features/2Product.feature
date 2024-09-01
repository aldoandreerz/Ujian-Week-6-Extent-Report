Feature: Adding products to the cart

  Scenario: Add two products to the cart
    Given User is on login page for product
    When User enters valid username and password for product
    And User clicks login button for product
    Then User should be redirected to the product page
    When User adds two products to the cart
    And User goes to the cart
    Then User should see two products in the cart
