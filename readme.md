## General Info
This is an API for retrieving and manipulating paginated information about french soccer clubs

The endpoints are as follows:

http://localhost:8080/footballmanager

### /team

``GET`` - gets all teams

#### Arguments

- Integer page (page number)
- Integer size (elements per page)
- String[] sort (optional, e.g. ["name"], ["name","acronym"])

#### Returns 

- Page<Team>

``POST`` - create new team

#### Arguments

- Team (object with name, acronym, players (optional) and budget)

#### Returns

- Team

### /team/{name}

``GET`` - get teams by name

#### Arguments

- Integer page (page number)
- Integer size (elements per page)
- String[] sort (optional, e.g. ["name"], ["name","acronym"])
- Name of the team

#### Returns

- Page<Team>

### /player/{name}

``GET`` - get players by name

#### Arguments

- Integer page (page number)
- Integer size (elements per page)
- String[] sort (optional, e.g. ["name"], ["name","acronym"])
- Name of the player

#### Returns

- Page<Player>

### /team/players

``PATCH`` - update players by team ID

#### Arguments

- PlayersUpdateDTO object (teamId, list of players)

#### Returns

- Team

### Technologies

* Java 17
* Spring Boot 3.1.1
* Junit 5
* Karate 1.4.0
* Spring Data JPA / Hibernate
* H2 database (embedded database)

### Tests
* 100% unit test coverage for service layer
* Plus karate tests for API/controller
* TDD was used

### Install guide

* Build with Maven in your IDE and run the Spring Boot project
* Feel free to run the unit tests and the integration API tests (Karate)
* You can interact with the embedded database with this url - http://localhost:8080/h2-console (username: sa, no password)

### Notes

* An embedded database was used for quicker development. This can be changed to a more robust database if needed.
* A SQL database was used to better demonstrate the relationships, and also since high speed wasn't a requirement
* An embedded database could still be interesting for unit testing, even if the main database is changed later. Currently, the data layer is not being mocked, instead being used directly for integration testing. This is possible since H2 is launched upon application startup and populated with ``data.sql``
* Spring Data JPA was used for quicker development. If more granular control is required, this could be replaced by specific implementations and exposure to only a few CRUD methods
* Not all CRUD methods were implemented for a more speedy approach to development, focusing on the more important functionalities.
* Karate could be integrated directly with Spring Boot. Currently its tests need to be run manually after application startup
* A global exception handler was created for general error treatment. It would be beneficial in the future to have more specific error handling, including treating with try/catch specific exceptions that could happen in the application
* A logger was used in one of the services just for demonstration, before throwing the exception
* Only one DTO was created, since it was necessary. Entities were used directly in Controller to avoid code duplication
* A more granular access to the API and each method could be built using Spring Security and role-based access, especially if this was meant for managers of football clubs. It all depends on the scope of the project.