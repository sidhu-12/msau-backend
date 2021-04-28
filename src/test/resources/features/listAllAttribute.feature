
@tag
Feature: List the values of the given attribute

  @Scenario1
  Scenario: List All successful
    When the user calls /listAllAttribute
    Then the user gets the status code of 200 for list value for given attribute