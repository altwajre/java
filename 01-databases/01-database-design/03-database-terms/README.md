# Database Terms
https://www.youtube.com/watch?v=pShj3gtYQik&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=9

`Data` is anything that can be stored in database like a list of customers.
`Database` is used to store large amounts of data. It differs from a spreadsheet in that it can be manipulated and managed in so many ways. 
           It will give us security control, flexibility, and backups for our data.
`Relational database` is a database that stores all information in relations (tables). 
`DBMS` manages database using code to control our database
`RDBMS` control relational database table, and values in table 
`NULL` no value or no data for table entity attribute
`Anomalies` error in data integrity. for example, update ten instead of one row.
`Entity integrity` uniqueness among of entities
`Referential integrity` reference id from one table to another table, keep connection across multiple tables.
`Domain integrity` acceptable value for a table column, Data Type - put limit on what we allow to store like INT, VARCHAR(20)

`Table` is a physically representation of a relation. It will have rows and columns organizing all of our information.
`Relation` is physically represented by a table. It is a table.
`File` is another name for a table

`Row` is individual entry for a table
`Record` is another name for a row
`Entry` is another name for a row
`Tuple` is a row. All attributes about an entity.

`Column` is a specific attribute of an entity
`Attribute` thing what we store about like username, username, password.
`Field` is another name for a column

`Entity` is what we store data about like a user is a table type or a specific entity (user Tom) is a row. 
`Value` is specific information we put in a column cell
`Database Design` the process to design a table to remove anomalies to have data integrity
`Schema` the draw out structure of database
`Normalization` the steps to get the best database design
`Naming Convention` make thing consistent, name table, column a certain way, draw database schema a certain way

`Key` a thing that makes every row unique in the table
`SuperKey` is any number of columns that forces every row to be unique.
`Candidate Key` is taking the superkey and removing unnecessary columns to get the least number of columns possible for row uniqueness.
`Primary Key` is the candidate key that you select to be used as the main key for that table. The Primary must be specified upon table creation.
`Alternate Key` all other candidate keys can be assigned as an alternate key. The alternate keys may be an offical type of key in your RDBMS or 
                it may be defined as simply a unique index.
`Foreign Key` a foreign key column references a primary key value of another table. This is used to make connections between tables within database
`Surrogate Key` is added auto incremented number
`Natural Key` it is from column that already exist in the table
`Simple Key` is key with only one column
`Composite Key` is key with multiple columns where at least one column is not a key in itself
`Compound Key` is key where all columns are themselves keys. Compound keys are most commonly seen in intermediary table (junction tables). 

## More Terms
https://www.youtube.com/watch?v=3-qSONSuZq0&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=10&spfreload=1

`SQL` structure query language - a language used to talk to any relational database management system.
`DDL` data definition language; define structure of database: CREATE TABLE and table columns like user_id, username, password
`DML` data manipulation language: manipudate value in database like INSERT, UPDATE, DELETE
`SQL Keywords` reserved words that you not supposed to use like SELECT; don't name table to any keyword.
`Frontend` what the user see; Frontends access Backend 
`Backend` what's going on behind the scenes; server side code that communicates to database
`Client` client is access server side database
`Server` server serves the instance of database to clients
`Server Side Scripting Language` communicating server side and client side 
`View` use data in database to illustrate in a different way
`Join` connect data from multiple tables using ids (PK, FK)

## Atomic Values
https://www.youtube.com/watch?v=Uhy75wsS96s&index=11&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID

It stores one thing. column is about one thing, all of values within that column is about one thing.
name is not atomic, we need to break this up into first name, middle name, last name