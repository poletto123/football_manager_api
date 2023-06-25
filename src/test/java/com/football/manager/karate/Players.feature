Feature: Players API Test

  Background:
    * url 'http://localhost:8080/footballmanager'

  Scenario: Get list of players filtered by name
    Given path 'player/Neymar'
    And param page = 0
    And param size = 5
    When method get
    Then status 200
    * def first = response.content[0].name.substring(0, 1)
    * match response.content[0].name == "Neymar Jr"

  Scenario: Update players by team ID
    Given path 'team/players'
    And request {"id": 1,"players":[{"name":"Adam Jakubech","position":"G"},{"name":"Benoît Costil","position":"G"},{"name":"Lucas Chevalier","position":"G"},{"name":"Tiago Djaló","position":"D"},{"name":"Alexsandro","position":"D"},{"name":"Gabriel Gudmundsson","position":"D"},{"name":"José Fonte Capitaine","position":"D"},{"name":"Leny Yoro","position":"D"},{"name":"Bafodé Diakité","position":"D"},{"name":"Ismaily","position":"D"},{"name":"Jonas Martin","position":"M"},{"name":"Rémy Cabella","position":"M"},{"name":"Angel Gomes","position":"M"},{"name":"Benjamin André","position":"M"},{"name":"André Gomes","position":"M"},{"name":"Carlos Baleba","position":"M"},{"name":"Jonathan Bamba","position":"F"},{"name":"Jonathan David","position":"F"},{"name":"Adam Ounas","position":"F"},{"name":"Timothy Weah","position":"F"},{"name":"Edon Zhegrova","position":"F"},{"name":"Alan Virginius","position":"F"},{"name":"Mohamed Bayo","position":"F"}]}/**/
    When method patch
    Then status 200
    * match response.id == 1
    * match response.players[0].name == 'Adam Jakubech'