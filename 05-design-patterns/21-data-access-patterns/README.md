# Data Access / Dao Design Pattern

https://www.tutorialspoint.com/design_pattern/data_access_object_pattern.htm

http://tutorials.jenkov.com/java-persistence/dao-design-pattern.html

https://www.youtube.com/watch?v=H1mePFyqqiE

## DAO vs Repository

DAO is an abstraction of data persistence. Repository is an abstraction of a collection of objects. 
DAO would be considered closer to the database, often table-centric. 
Repository would be considered closer to the Domain, dealing only in Aggregate Roots.

## Repository Pattern

http://martinfowler.com/eaaCatalog/repository.html

By Martin Fowler: it "mediates between the domain and data mapping layers using a collection-like interface for accessing domain objects"

## Unit of Work Pattern

http://martinfowler.com/eaaCatalog/unitOfWork.html

by Martin Fowler: "Maintains a list of objects affected by a business transaction and coordinates the writing out of changes and the resolution of concurrency problems".

## Data Transfer Object (DTO)

http://martinfowler.com/eaaCatalog/dataTransferObject.html

An object that carries data between processes in order to reduce the number of method calls.

Using a DTO will help ensure that you send back only the details you intend to.
Use AutoMapper to map the domain to data-transfer object and vice versa.
