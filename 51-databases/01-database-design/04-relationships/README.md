# Relationships
https://www.youtube.com/watch?v=2ko_rBZXTjE&index=12&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID

There are three kind of relationships (logically) are one-to-one, one-to-many, and many-to-many.

## One-to-One Relationship - 1:1
https://www.youtube.com/watch?v=jKfZiotyRhk&index=13&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID
One entity has connection with another one entity like `husband` and `wife`, one husband can have only one wife, and one wife can only have one husband.
`Person` and `social security number` is also `one-to-one` relationship because one `person` can supposed to have only one `SSN`.

## One-to-Many Relationship - 1:M
https://www.youtube.com/watch?v=fCUrCNiA0j0&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=14
One `parent` entity can have a relationship with multiple `child` entities. A `child` entity can have a relationship only with one `parent` entity.
`User` and `Comment` is one-to-many relationship because one `user` can have multiple `comments` on youtube, and a `comment` is owned by one `person`.

## Many-to-Many Relationship - M:N
https://www.youtube.com/watch?v=pv7P9R9JI-E&index=15&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID
`class` and `student` is `many-to-many` relationship because one class can have multiple students and a student can have multiple classes.
`Many-to-Many` relationships cannot be stored in a database practically, we need a intermediate table like `class_student` to be a child table, 
so both `class` and `student` tables could be parent tables and they will have one-to-many to the intermediate table `class_student`

## Designing One-to-One Relationship
`Person` and `Social security number`, one `Person: Tom` has only one `SSN: 123`, and `SSN: 123` is exclusive to `Tom`.
We can often store one-to-one relationship as attribute rather than entities, `SSN` can be stored as attribute of a Person.

Example: one-to-one.sql

## Designing One-to-Many Relationship
https://www.youtube.com/watch?v=DzchNE1pbrk&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=17

Example: one-to-many.sql

## Parent Tables and Child Tables
https://www.youtube.com/watch?v=ZtK89kVLPzM&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=18

parent-child-tables.txt

## Designing Many-to-Many Relationship
https://www.youtube.com/watch?v=GJbtDTGmOGY&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=19
M:N
`Classes` and `Students`, one `class` has many `students`, and one `student` has many `classes`
We need to an `intermediary` table (`junction` table) to break a many-to-many relationship into two one-to-many to store data in database
  
Example: many-to-many.sql

## Summary of Relationship
https://www.youtube.com/watch?v=o2aAU6wQZSw&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=20