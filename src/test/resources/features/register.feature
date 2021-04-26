@tag
Feature: Registration

  @Scenario1
  Scenario: Register Successful
    When the user clicks the /register
    Then the client receives the status code of 200
