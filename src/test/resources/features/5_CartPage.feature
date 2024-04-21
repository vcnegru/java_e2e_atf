@CartPage
Feature: Cart Page

  Background:
    Given user accesses BasePaws page
    And Popup 'Exclusive Offer' is displayed
    And user clicks on 'Close Offer Popup' button
    And Popup 'Cookies' is displayed
    And user clicks on 'Accept Cookies' button

  @Positive @CartPage
  Scenario: User validates added products on Cart Page
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
    When user clicks on 'For Dogs' header menu
    And user clicks on product:
      | Breed + Health Dog DNA Test |
    And user selects '1 test' set of product
    And user change quantity to '1' product
    And user adds 'Breed + Health Dog DNA Test' product to cart
    Then mini cart is displayed
    And mini cart has following items:
      | product[0].name     | Breed + Health Cat DNA Test |
      | product[0].count    | 2                           |
      | product[0].quantity | 1 test                      |
      | product[0].subtotal | 318.00                      |
      | product[1].name     | Breed + Health Dog DNA Test |
      | product[1].count    | 1                           |
      | product[1].quantity | 1 test                      |
      | product[1].subtotal | 149.00                      |
    And mini cart total quantities are as follow:
      | cart_total_price     | 467.00 |
      | cart_total_list_size | 2      |
      | cart_products_count  | 3      |
    When customer clicks on View Cart button
    Then Cart Page is displayed
    And Cart Page contains following products:
      | product[0].name     | Breed + Health Cat DNA Test |
      | product[0].count    | 2                           |
      | product[0].quantity | 1 test                      |
      | product[0].subtotal | 318.00                      |
      | product[1].name     | Breed + Health Dog DNA Test |
      | product[1].count    | 1                           |
      | product[1].quantity | 1 test                      |
      | product[1].subtotal | 149.00                      |
    And Cart Page contains following total details:
      | total    | 467.00                       |
#      | cart_total_savings | 2                            |
      | shipping | Free U.S. Shipping & Returns |

  @Positive @CartPage
  Scenario: Validate Discount coupon is applied on Cart Page
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
    And customer clicks on View Cart button
    And Cart Page is displayed
    And Cart Page contains following products:
      | product[0].name     | Breed + Health Cat DNA Test |
      | product[0].count    | 2                           |
      | product[0].quantity | 1 test                      |
      | product[0].subtotal | 318.00                      |
    And Cart Page contains following total details:
      | total    | 318.00                       |
#      | cart_total_savings | 2                            |
      | shipping | Free U.S. Shipping & Returns |
    When user applies 'Discount code' on Cart Page
    Then Cart Page contains following products:
      | product[0].name     | Breed + Health Cat DNA Test |
      | product[0].count    | 2                           |
      | product[0].quantity | 1 test                      |
      | product[0].subtotal | 3.18                        |
    And Cart Page contains following total details:
      | total    | 3.18                         |
      | shipping | Free U.S. Shipping & Returns |
    And label for applied discount code is displayed

  @Positive @CartPage
  Scenario: Validate when Discount coupon is removed on Cart Page prices are recalculated
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
    And customer clicks on View Cart button
    And Cart Page is displayed
    And Cart Page contains following products:
      | product[0].name     | Breed + Health Cat DNA Test |
      | product[0].count    | 2                           |
      | product[0].quantity | 1 test                      |
      | product[0].subtotal | 318.00                      |
    And Cart Page contains following total details:
      | total    | 318.00                       |
#      | cart_total_savings | 2                            |
      | shipping | Free U.S. Shipping & Returns |
    And  user applies 'Discount code' on Cart Page
    And Cart Page contains following products:
      | product[0].name     | Breed + Health Cat DNA Test |
      | product[0].count    | 2                           |
      | product[0].quantity | 1 test                      |
      | product[0].subtotal | 3.18                        |
    And Cart Page contains following total details:
      | total    | 3.18                         |
      | shipping | Free U.S. Shipping & Returns |
    And label for applied discount code is displayed
    When Customer clicks on remove coupon on Cart Page
    Then Cart Page contains following products:
      | product[0].name     | Breed + Health Cat DNA Test |
      | product[0].count    | 2                           |
      | product[0].quantity | 1 test                      |
      | product[0].subtotal | 318.00                      |
    And Cart Page contains following total details:
      | total    | 318.00                       |
      | shipping | Free U.S. Shipping & Returns |
