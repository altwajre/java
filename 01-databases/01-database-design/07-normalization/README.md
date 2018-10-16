# Introduction to Database Normalization
https://www.youtube.com/watch?v=RZ9lvPRxwpg&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=36
`Normalization` is a process organizing data in a database. This includes creating tables and establishing relationships between those tables according to 
                rules designed both to protect the data and to make the database more flexible by eliminating redundancy and inconsistent dependency.

## 1NF First Normal Form
https://www.youtube.com/watch?v=JjwEhK4QxRo&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=37
`1NF` making every column and value atomic.

Example: 1nf.txt

## 2NF Second Normal Form
https://www.youtube.com/watch?v=WSKuxoAN35g&index=38&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID
`2NF` remove partial dependencies. A partial dependency is when a column depends on only part of the primary key. This only makes sense if you have a 
      primary key that consists of multiple columns (composite/compound key).
      a table has only `one column primary key` is already in `2NF`

Example: 2nf.txt

## 3NF Third Normal Form
https://www.youtube.com/watch?v=6A41gVJ3g2g&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=39
`3NF` remove transitive dependencies, a transitive dependency is when a column depends upon a column that depends upon the primary key.

Example: 3nf.txt
