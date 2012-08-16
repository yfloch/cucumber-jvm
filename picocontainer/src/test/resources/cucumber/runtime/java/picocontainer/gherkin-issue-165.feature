@gherkin-issue-165
Feature: Add two numbers

  Scenario Outline: Add two numbers in Scenario Outline
    Given two numbers <number_1> and <number_2>
    When I add them
    Then Result is <number_3>

    Examples:
      | number_1 | number_2 | number_3 |
      | 2        | 3        | 5        |
      | 1        | 2        | 3        |

  Scenario: Add two numbers in Scenario
    Given two numbers 1 and 1
    When I add them
    Then Result is 2