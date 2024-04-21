@Dog
Feature: For Dogs Menu

  Background:
    Given user accesses BasePaws page
    And Popup 'Exclusive Offer' is displayed
    And user clicks on 'Close Offer Popup' button
    And Popup 'Cookies' is displayed
    And user clicks on 'Accept Cookies' button

  @Positive
  Scenario: Guest User accesses product 'Breed + Health Dog DNA Test' for Dogs
    Given user clicks on 'For Dogs' header menu
    When user clicks on product:
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


