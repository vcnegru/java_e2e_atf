Feature: For Dogs Menu

  Background:
    Given user accesses BasePaws page
#    And Popup 'Exclusive Offer' is displayed
#    And user clicks on 'Close Offer Popup' button
#    And Popup 'Cookies' is displayed
#    And user clicks on 'Accept Cookies' button

  @Positive
  Scenario: Guest User accesses list of products for Dogs
    Given user clicks on 'For Dogs' header menu
    Then Dog products are displayed:
      | Breed + Health Dog DNA Test |
