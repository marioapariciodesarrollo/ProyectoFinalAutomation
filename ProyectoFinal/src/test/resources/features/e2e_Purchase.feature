Feature: End to End Purchase

  Scenario: Successful login and product purchase
    Given I navigate to the login page
    When I enter valid credentials
    And I click the login button
    Then I should see the user logged in
    When I click on Men Category
    Then I should be redirected to Men page
    When I select a random item and click it
    Then I should be able to see the product page
    When I choose a random size
    And I choose a random color
    And I click the Add to Cart button
    Then I should see a success message
    When I click on Shopping cart button
    And I click on Checkout button
    Then I should be in the Checkout first step
    When I click on Next Button
    Then I should be redirected to Checkout Payment Page
    When I click on Place Order Button
    Then I should see the order completed
    When I click on Continue Shopping Button
