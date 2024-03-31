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

  @Positive
  Scenario: Guest User accesses product ' Breed + Health Cat DNA Test' for Cats
    Given user clicks on 'For Cats' header menu
    And user clicks on product:
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
    And user clicks on product:
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
    And user clicks on product:
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