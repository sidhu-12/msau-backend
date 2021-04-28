@tag
Feature: Deletion of onboardee

  @Scenario1
  Scenario: Deletion successful
    When the user calls /delete
    Then the user gets the status code of 200 for deletion of onboardee

    