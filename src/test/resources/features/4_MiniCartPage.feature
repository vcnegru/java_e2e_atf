@MiniCart
Feature: Validate scenarios on MiniCart Page

  Background:
    Given user accesses BasePaws page
    And Popup 'Exclusive Offer' is displayed
    And user clicks on 'Close Offer Popup' button
    And Popup 'Cookies' is displayed
    And user clicks on 'Accept Cookies' button

  @Positive
  Scenario: Guest User adds product 'Breed + Health Dog DNA Test' for Dogs to cart
    Given user clicks on 'For Dogs' header menu
    And user clicks on product:
      | Breed + Health Dog DNA Test |
    When user selects '1 test' set of product
    And user change quantity to '1' product
    And user adds 'Breed + Health Dog DNA Test' product to cart
    Then mini cart is displayed
    And user clicks on Close mini cart button
    And header cart icon has '1' items
    When user selects '2 tests' set of product
    And user change quantity to '1' product
    And user adds 'Breed + Health Dog DNA Test' product to cart
    And mini cart is displayed
    And user clicks on Close mini cart button
    And header cart icon has '2' items
    When user selects '3 tests' set of product
    And user change quantity to '1' product
    And user adds 'Breed + Health Dog DNA Test' product to cart
    And mini cart is displayed
    And mini cart has following items:
      | product[0].name     | Breed + Health Dog DNA Test |
      | product[0].count    | 1                           |
      | product[0].quantity | 1 test                      |
      | product[0].subtotal | 149.00                      |
      | product[1].name     | Breed + Health Dog DNA Test |
      | product[1].count    | 1                           |
      | product[1].quantity | 2 tests                     |
      | product[1].subtotal | 198.00                      |
      | product[2].name     | Breed + Health Dog DNA Test |
      | product[2].count    | 1                           |
      | product[2].quantity | 3 tests                     |
      | product[2].subtotal | 267.00                      |
    And mini cart total quantities are as follow:
      | cart_total_price     | 614.00 |
      | cart_total_list_size | 3      |
      | cart_products_count  | 3      |
    And user clicks on Close mini cart button
    And header cart icon has '3' items