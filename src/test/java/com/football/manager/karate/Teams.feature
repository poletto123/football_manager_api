Feature: Teams API test

  Background:
    * url 'http://localhost:8080/footballmanager'

  Scenario: Get list of teams
    Given path 'team'
    And param page = 0
    And param size = 2
    When method get
    Then status 200
    * match response.content[0].id == 1
    * match response.content[1].id == 2

  Scenario: Get list of teams filtered by name
    Given path 'team/Nice'
    And param page = 0
    And param size = 5
    When method get
    Then status 200
    * match response.content[0].name == "Olympique Gymnaste Club de Nice"

  Scenario: Get list of teams sorted by name, ascending
    Given path 'team'
    And param page = 0
    And param size = 5
    And param sort = ['name']
    When method get
    Then status 200
    * def first = response.content[0].name.substring(0, 1)
    * def last = response.content[1].name.substring(0, 1)
    * def isOrderCorrect = first <= last
    * match isOrderCorrect == true

  Scenario: Get list of teams sorted by budget, descending
    Given path 'team'
    And param page = 0
    And param size = 5
    And param sort = ['-budget']
    When method get
    Then status 200
    * def first = response.content[0].budget
    * def last = response.content[1].budget
    * def isOrderCorrect = first >= last
    * match isOrderCorrect == true

  Scenario: Create a team without players
    Given path 'team'
    And request { "name": "Lille OSC", "acronym": "LIL",  "budget": "100000000" }
    When method post
    Then status 201
    * def isIdBiggerThanZero = response.id > 0
    * match isIdBiggerThanZero == true
    * match response.name == "Lille OSC"
