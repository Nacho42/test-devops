Feature: This service will register a bean in DB and call jenkins api

  Scenario: Execute build correctly
    Given a valid request
      | externalId | name | repositoryPath| version |
      |     100    | bean | /beans/bean   | 1.0.0   |
    When call build service
    Then result is OK
