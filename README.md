## Welcome to the BooksInfo application.
With the BooksInfo web application, you can find all information about authors and their books.

Program has next functions:

- add/update/delete author.
- add/update/delete book.
- view all books by the author.
- show most selling book by the author.
- show most published book by the author.
- show a list of the best-selling books by part of the author's name.
- show a list of the most published books by part of the author's name.
- show a list of the most successful books by the part of the author's name.
- show the most successful author. 
---

## This application has a layered architecture.

- Controller 
- Repository
- Service
---

## Technologies
- Java 11
- Spring boot
  - Spring mvc
- MySQL database
- H2 database
- Query Language - HQL 
- Swagger
- Lombok
- JUnit 5 Test
- Maven

## How to run "BooksInfo"
- Clone this repository.
- Run this project on yours IntelliJ IDEA.
- Fill properties spring.datasource.username and spring.datasource.password in [application.properties](src/main/resources/application.properties) to connect application to database.
- Follow the link [http://localhost:8080/swagger-ui/#/](http://localhost:8080/swagger-ui/#/) where you can test APi.