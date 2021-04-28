
@tag
Feature: Updation of onboardee

  @Scenario1
  Scenario: Updation successful
    When the user calls /update
    Then the user gets the status code of 200 for updation

