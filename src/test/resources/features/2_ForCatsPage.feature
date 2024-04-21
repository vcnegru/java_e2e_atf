@Cat
Feature: For Cats Menu

  Background:
    Given user accesses BasePaws page
    And Popup 'Exclusive Offer' is displayed
    And user clicks on 'Close Offer Popup' button
    And Popup 'Cookies' is displayed
    And user clicks on 'Accept Cookies' button

  @Positive
  Scenario: Guest User accesses list of products for Cats
    Given user clicks on 'For Cats' header menu
    Then Cat products are displayed:
      | Breed + Health Cat DNA Test |
      | Oral Health Test for Cats   |
      | Whole Genome Test           |

  @Positive
  Scenario: Guest User accesses product ' Breed + Health Cat DNA Test' for Cats
    Given user clicks on 'For Cats' header menu
    When user clicks on product:
      | Breed + Health Cat DNA Test |
    Then 'Breed + Health Cat DNA Test' cat product page is displayed with details:
      | Cat Breed Product Image        |
      | Cat Product Suptile            |
      | Cat Breed Product Detail Title |
      | Old price                      |
      | New price                      |
      | Special Offer Button           |
      | Product Count details          |
      | Add to cart                    |

  @Positive
  Scenario: Guest User accesses product 'Oral Health Test for Cats' for Cats
    Given user clicks on 'For Cats' header menu
    When user clicks on product:
      | Oral Health Test for Cats |
    Then 'Oral Health Test for Cats' cat product page is displayed with details:
      | Cat Oral Product Image        |
      | Cat Product Suptile           |
      | Cat Oral Product Detail Title |
#      | Old price                      |
      | New price                     |
#      | Special Offer Button           |
      | Product Count details         |
      | Add to cart                   |

  @Positive
  Scenario: Guest User accesses product 'Whole Genome Test ' for Cats
    Given user clicks on 'For Cats' header menu
    When user clicks on product:
      | Whole Genome Test |
    Then 'Whole Genome Sequence' cat product page is displayed with details:
      | Cat Genome Product Image        |
      | Cat Product Suptile             |
      | Cat Genome Product Detail Title |
#      | Old price                      |
      | New price                       |
#      | Special Offer Button           |
      | Product Count details           |
      | Add to cart                     |

  @Positive
  Scenario: Guest User adds product 'Breed + Health Cat DNA Test' for Cats to cart
    Given user clicks on 'For Cats' header menu
    And user clicks on product:
      | Breed + Health Cat DNA Test |
    When user selects '1 test' set of product
    And user change quantity to '2' product
#    And user change quantity to '1' product
    And user adds 'Breed + Health Cat DNA Test' product to cart
    And mini cart is displayed
    And user clicks on Close mini cart button
    Then header cart icon has '2' items
    When user selects '2 tests' set of product
    And user change quantity to '1' product
    And user adds 'Breed + Health Cat DNA Test' product to cart
    And mini cart is displayed
    And user clicks on Close mini cart button
    Then header cart icon has '3' items
    When user selects '3 tests' set of product
    And user change quantity to '1' product
    And user adds 'Breed + Health Cat DNA Test' product to cart
    And mini cart is displayed
    Then mini cart has following items:
      | product[0].name     | Breed + Health Cat DNA Test |
      | product[0].count    | 2                           |
      | product[0].quantity | 1 test                      |
      | product[0].subtotal | 318.00                      |
      | product[1].name     | Breed + Health Cat DNA Test |
      | product[1].count    | 1                           |
      | product[1].quantity | 2 tests                     |
      | product[1].subtotal | 208.00                      |
      | product[2].name     | Breed + Health Cat DNA Test |
      | product[2].count    | 1                           |
      | product[2].quantity | 3 tests                     |
      | product[2].subtotal | 277.00                      |
    And mini cart total quantities are as follow:
      | cart_total_price     | 803.00 |
      | cart_total_list_size | 3      |
      | cart_products_count  | 4      |
    And user clicks on Close mini cart button
    And header cart icon has '4' items

  @Positive
  Scenario: Guest User adds product 'Whole Genome Test' for Cats to cart
    Given user clicks on 'For Cats' header menu
    And user clicks on product:
      | Whole Genome Test |
    When user selects '1 test' set of product
    And user change quantity to '1' product
    And user adds 'Whole Genome Sequencing' product to cart
    And mini cart is displayed
    And mini cart has following items:
      | product[0].name     | Whole Genome Sequencing |
      | product[0].count    | 1                       |
      | product[0].quantity | 1 test                  |
      | product[0].subtotal | 499.00                  |
    And mini cart total quantities are as follow:
      | cart_total_price     | 499.00 |
      | cart_total_list_size | 1      |
      | cart_products_count  | 1      |
    And user clicks on Close mini cart button
    And header cart icon has '1' items
    And user change quantity to '2' product
    And user adds 'Whole Genome Sequencing' product to cart
    And mini cart is displayed
    And mini cart has following items:
      | product[0].name     | Whole Genome Sequencing |
      | product[0].count    | 3                       |
      | product[0].quantity | 1 test                  |
      | product[0].subtotal | 1,497.00                |
    And mini cart total quantities are as follow:
      | cart_total_price     | 1,497.00 |
      | cart_total_list_size | 1        |
      | cart_products_count  | 3        |
    And user clicks on Close mini cart button
    And header cart icon has '3' items

  @Positive
  Scenario: Guest User adds product 'Oral Health Test for Cats' for Cats to cart
    Given user clicks on 'For Cats' header menu
    And user clicks on product:
      | Oral Health Test for Cats |
    When user selects '1 test' set of product
    And user change quantity to '1' product
    And user adds 'Oral Health Test for Cats' product to cart
    And mini cart is displayed
    And mini cart has following items:
      | product[0].name     | Oral Health Test for Cats |
      | product[0].count    | 1                         |
      | product[0].quantity | 1 test                    |
      | product[0].subtotal | 99.00                     |
    And mini cart total quantities are as follow:
      | cart_total_price     | 99.00 |
      | cart_total_list_size | 1     |
      | cart_products_count  | 1     |
    And user clicks on Close mini cart button
    And header cart icon has '1' items
    And user change quantity to '2' product
    And user adds 'Oral Health Test for Cats' product to cart
    And mini cart is displayed
    And mini cart has following items:
      | product[0].name     | Oral Health Test for Cats |
      | product[0].count    | 3                         |
      | product[0].quantity | 1 test                    |
      | product[0].subtotal | 297.00                    |
    And mini cart total quantities are as follow:
      | cart_total_price     | 297.00 |
      | cart_total_list_size | 1      |
      | cart_products_count  | 3      |
    And user clicks on Close mini cart button
    And header cart icon has '3' items

  @Positive @Checkout
  Scenario: User Checks out 'Breed + Health Cat DNA Test' product
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
    When user clicks on Open mini cart button
    And user clicks checkout button
    Then Checkout Page is displayed with order details:
      | product[0].name     | Breed + Health Cat DNA Test |
      | product[0].count    | 2                           |
      | product[0].quantity | 1 test                      |
      | product[0].subtotal | 318.00                      |

