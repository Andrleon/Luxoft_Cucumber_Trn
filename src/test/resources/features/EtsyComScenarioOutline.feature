@smoke
Feature: Search for items on Etsy
  As a user
  I want to search for different items will be filtered by price
  So that I will be able to buy necessary items fast

  Scenario Outline: Search for particular item
    Given I am on the main page
    When I set page language to "English"
    And I search for "<item>" items
    Then I see search results
    Then I get search results for "<item>" items
    When I set minimum price to "<price from>"
    And I set maximum price to "<price to>"
    And I filter items by price
    Then next price filtering tag "<price tag>" is visible

    Examples:
      |item       | price from | price to | price tag|
      |leather bag|20          |50        |USD 20 – USD 50|
      |iphone     |200         |1000      |USD 200 – USD 1,000|