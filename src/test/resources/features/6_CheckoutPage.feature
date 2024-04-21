@CheckoutPage
Feature: Checkout Page

  Background:
    Given user accesses BasePaws page
    And Popup 'Exclusive Offer' is displayed
    And user clicks on 'Close Offer Popup' button
    And Popup 'Cookies' is displayed
    And user clicks on 'Accept Cookies' button

  @Positive
  Scenario: User Checks out 'Breed + Health Dog DNA Test' product
    Given user clicks on 'For Dogs' header menu
    And user clicks on product:
      | Breed + Health Dog DNA Test |
    And user selects '1 test' set of product
    And user change quantity to '2' product
    And user adds 'Breed + Health Dog DNA Test' product to cart
    And mini cart is displayed
    And mini cart has following items:
      | product[0].name     | Breed + Health Dog DNA Test |
      | product[0].count    | 2                           |
      | product[0].quantity | 1 test                      |
      | product[0].subtotal | 298.00                      |
    And mini cart total quantities are as follow:
      | cart_total_price     | 298.00 |
      | cart_total_list_size | 1      |
      | cart_products_count  | 2      |
    And user clicks on Close mini cart button
    And header cart icon has '2' items
    When user clicks on Open mini cart button
    And user clicks checkout button
    Then Checkout Page is displayed with order details:
      | product[0].name     | Breed + Health Dog DNA Test |
      | product[0].count    | 2                           |
      | product[0].quantity | 1 test                      |
      | product[0].subtotal | 298.00                      |
    And Checkout Page is displayed with total section details:
      | subtotal | 298.00 |
#      | shipping |        |
      | total    | 298.00 |

  @Positive @Checkout
  Scenario: User Checks out 'Breed + Health Cat DNA Test' product with discount code
    Given user clicks on 'For Cats' header menu
    And user clicks on product:
      | Breed + Health Cat DNA Test |
    And user selects '1 test' set of product
    And user change quantity to '2' product
    And user adds 'Breed + Health Cat DNA Test' product to cart
    And mini cart is displayed
    And mini cart has following items:
      | product[0].name     | Breed + Health Cat DNA Test |
      | product[0].count    | 2                           |
      | product[0].quantity | 1 test                      |
      | product[0].subtotal | 318.00                      |
    And mini cart total quantities are as follow:
      | cart_total_price     | 318.00 |
      | cart_total_list_size | 1      |
      | cart_products_count  | 2      |
    And user clicks on Close mini cart button
    And header cart icon has '2' items
    And user clicks on Open mini cart button
    And user clicks checkout button
    And Checkout Page is displayed with order details:
      | product[0].name     | Breed + Health Cat DNA Test |
      | product[0].count    | 2                           |
      | product[0].quantity | 1 test                      |
      | product[0].subtotal | 318.00                      |
    When user applies 'Discount code' on Checkout Page
    Then Checkout Page is displayed with order details:
      | product[0].name     | Breed + Health Cat DNA Test |
      | product[0].count    | 2                           |
      | product[0].quantity | 1 test                      |
      | product[0].subtotal | 3.18                        |
    And Checkout Page is displayed with total section details:
      | subtotal | 3.18 |
#    |shipping||
      | total    | 3.18 |
#    |total savings||

  @Negative @Ignore
  Scenario: Validate that Error is displayed for required field that is not provided
    Given user clicks on 'For Cats' header menu
    And user clicks on product:
      | Breed + Health Cat DNA Test |
    And user selects '1 test' set of product
    And user change quantity to '1' product
    And user adds 'Breed + Health Cat DNA Test' product to cart
    And mini cart is displayed
#    And user clicks on Close mini cart button
#    And header cart icon has '1' items
#    And user clicks on Open mini cart button
    And user clicks checkout button
    When customer clicks on 'Pay Now' button
    Then on Checkout Page errors are displayed for required fields:
      | email     | Enter an email            |
      | firstName | Enter a first name        |
      | lastName  | Enter a last name         |
      | address   | Enter an address          |
#      | country        | ???                                                  |
# TODO fail on Jenkins for city field
#      | city      | Enter a city              |
      | state     | Select a state / province |
      | zip       | Enter a ZIP / postal code |
# TODO - manage iframe content
#      And in Card Number section errors are displayed for required fields:
#      | cardNumber     | Enter a card number                                  |
#      | expirationDate | Enter a valid expiration date                        |
#      | cvv            | Enter the CVV or security code on your card          |
#      | nameOnCard     | Enter your name exactly as it’s written on your card |

  @Positive @Ignore
  Scenario: Customer provides all required details for Checkout Page
    Given user clicks on 'For Cats' header menu
    And user clicks on product:
      | Breed + Health Cat DNA Test |
    And user selects '1 test' set of product
    And user change quantity to '1' product
    And user adds 'Breed + Health Cat DNA Test' product to cart
    And mini cart is displayed
#    And user clicks on Close mini cart button
#    And header cart icon has '1' items
#    And user clicks on Open mini cart button
    And user clicks checkout button
    When Customer provides all required data for Checkout page
      | email     | basepaws_test@gmail.com |
      | firstName | John                    |
      | lastName  | Doe                     |
      | address   | Enter an address        |
#      | country        | ??                                                  |
      | city      | Michigan City           |
      | state     | Indiana                 |
      | zip       | 46360                   |
# TODO - manage iframe content
    And Card Number section is populated with fields:
      | cardNumber     | 4444444444444448 |
#      | expirationDate | 1229             |
      | cvv            | 112              |
      | nameOnCard     | JOHN DOE         |
