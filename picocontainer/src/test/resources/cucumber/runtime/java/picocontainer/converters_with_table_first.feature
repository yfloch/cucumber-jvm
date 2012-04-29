Feature: Converters with table first

  As a happy cucumber-jvm user
  I want to mix and match xstream converters in plain steps and in data tables
  So that I can use the same framework in both cases

  Scenario: do some stuff with data table first
    Given I have some stuff in a data table:
      | lowercaseFruit | person | lowercaseCity |
      | BANANA         | Joan   | LONDON        |

  Scenario: some lower case person
    Given I have some lower case person named "charlie"

  Scenario: some fruit
    Given I have some fruit named "Citrus"

  Scenario: some city
    Given I have some city holder named "Chicago"
