@tag
Feature: Purchase the order from Ecommerce Website

  Background: 
    Given I landed on Ecommerce page


  @Regression
  Scenario Outline: Postive test of Submitting the Order
    Given Logged in with username <name> and password <password>
    When I add product <productName> from cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." is display in ConfirmationPage

    Examples: 
      | name                | password | productName  |
      | postman12@gmail.com | Ab123456 | ZARA COAT 3  |
