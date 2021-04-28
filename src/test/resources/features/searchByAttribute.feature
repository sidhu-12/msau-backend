@tag
Feature: Search values based on the attribute:value

  @Scenario1
  Scenario: Search Successful
    When the user calls /searchResult
    Then the user gets the status code of 200 for search value