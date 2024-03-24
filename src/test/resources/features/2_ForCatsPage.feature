Feature: For Cats Menu

  Background:
    Given user accesses BasePaws page
#    And Popup 'Exclusive Offer' is displayed
#    And user clicks on 'Close Offer Popup' button
#    And Popup 'Cookies' is displayed
#    And user clicks on 'Accept Cookies' button

  @Positive
  Scenario: Guest User accesses list of products for Cats
    Given user clicks on 'For Cats' header menu
    Then Cat products are displayed:
      | Breed + Health Cat DNA Test |
      | Oral Health Test for Cats   |
      | Whole Genome Test           |