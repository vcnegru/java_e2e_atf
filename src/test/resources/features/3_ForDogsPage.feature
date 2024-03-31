Feature: For Dogs Menu

  Background:
    Given user accesses BasePaws page
#    And Popup 'Exclusive Offer' is displayed
#    And user clicks on 'Close Offer Popup' button
#    And Popup 'Cookies' is displayed
#    And user clicks on 'Accept Cookies' button

#  @Positive
#  Scenario: Guest User view list of products for Dogs from Header menu
#    Given user clicks on 'For Dogs' header menu
#    Then Dog products are displayed:
#      | Breed + Health Dog DNA Test |
#
  @Positive
  Scenario: Guest User accesses product for Dogs
    Given user clicks on 'For Dogs' header menu
    And user clicks on product:
      | Breed + Health Dog DNA Test |
    Then 'Breed + Health Dog DNA Test' dog product page is displayed with details:
      | Dog Breed Product Image        |
      | Dog Breed Product Suptile      |
      | Dog Breed Product Detail Title |
      | Old price                      |
      | New price                      |
      | Special Offer Button           |
      | Product Count details          |
      | Add to cart                    |