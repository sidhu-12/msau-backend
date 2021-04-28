
@tag
Feature: Creation of onboardee

  @Scenario1
  Scenario: Creation successful
    When the user calls /create
    Then the user gets the status code of 200

    