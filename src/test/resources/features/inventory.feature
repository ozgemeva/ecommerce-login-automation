Feature: Inventory Page Functionality

  Background:
    Given I am on the login page
    And I enter the correct username and password
    When I click the login button
    Then I should be redirected to the inventory page

  @smoke
  Scenario: Verify that all inventory items are listed on the page
    When the inventory page has fully loaded
    Then all products should be visible
    And the number of products should be greater than 0

  @smoke
 Scenario Outline: Select Product Functionality
  Given the inventory page has fully loaded
  Then all products should be visible
  When I click the Add to cart button for the "<Product Name>" product
  And I navigate to the cart page
  Then the product "<Product Name>" should be added to the basket
  And the Add to cart button should change to Remove button

Examples:
  | Product Name             |
  | Sauce Labs Backpack      |
  | Sauce Labs Bolt T-Shirt  |
