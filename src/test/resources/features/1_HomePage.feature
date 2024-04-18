
Feature: Home Page

#  Background:
#    Given user accesses BasePaws page

  @Positive @run
  Scenario: User accesses BasePaws Home page as guest user
    When user accesses BasePaws page
    And Popup 'Exclusive Offer' is displayed
    And user clicks on 'Close Offer Popup' button
    And Popup 'Cookies' is displayed
    And user clicks on 'Accept Cookies' button
    Then 'Home' page header menu elements are displayed
      | Logo         |
      | For Dogs     |
      | For Cats     |
      | Learn        |
      | About        |
      | Register Kit |
      | Sign Up      |
      | Mini Cart    |
    And Header 'Announcement Bar' is displayed
    And 'Home' page is displayed with following details:
      | header      | New beginnings, healthier pets                                                        |
      | description | Embrace spring with a leap into your pet's DNA. Discover their unique needs and start |

