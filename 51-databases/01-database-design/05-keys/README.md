# Introduction to Keys
https://www.youtube.com/watch?v=AAREmlb4sTs&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=21
Keys keep everything unique and are used to make connections between tables.
Primary Keys
1, Unique
2, Never changing
3, Never NULL

## Primary Key Index
https://www.youtube.com/watch?v=AI4ifO-6-mo&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=22
Primary key will automatically create an index used for database lookup optimization.
`index` makes search fast like book index.
we should have `index` for SELECT `WHERE` and `JOIN`.

## Look up Table
https://www.youtube.com/watch?v=sCCBJBb4HQA&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=23
A table with a list of all possible values for a column in a different table. These values are then accessed through a foreign key.
This is often used for one to many relationships where the many has only so many possibilities.
`lookup-table: memberships` and `members` is one-to-many relationship
What do keys do?
1, Integrity - protect integrity
2, Unique
3, Improves functionality of database
4, makes update easy, less work
5, allows for adding complexity
6, FK constraint

Example: look-up-table.sql

## Superkey and Candidate Key
https://www.youtube.com/watch?v=kAXP8a1zuHo&index=24&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID
`SuperKey` is any number of columns that forces every row to be unique.
`Candidate Key` is taking the superkey and removing unnecessary columns to get the least number of columns possible for row uniqueness.

## Primary Key and Alternate Key
https://www.youtube.com/watch?v=u77YwdnleS0&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=25
`Primary Key` is the candidate key that you select to be used as the main key for that table. The Primary must be specified upon table creation.
`Alternate Key` all other candidate keys can be assigned as an alternate key. The alternate keys may be an offical type of key in your RDBMS or
                it may be defined as simply a unique index.

## Surrogate Key and Natural Key
https://www.youtube.com/watch?v=GCA63syHvsk&index=26&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID
`Surrogate Key` is auto incremented number that have no real world meaning. Auto incremented means that each row will have the next highest number.
`Natural Key` is created from the table column has real world meaning already present within the table.

## Surrogate Key vs Natural Key
https://www.youtube.com/watch?v=wcuqwXUF-gY&index=27&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID
`Natural Key` it is from column that already exist in the table
  Pros: no need to define new data, store less information.
  Cons: not easy to figure out the Natural Key, Natural Key may change because it has real world meaning.  
`Surrogate Key` is added auto incremented number
  Pros: it is usually number that easy to work with. Never changing.
  Cons: need to add a new column to the table
`Which one should I use?` pick one and use it through out the database. I prefer to use `surrogate key` through out the database because it is simple, just add a new id column.

## Foreign Key
https://www.youtube.com/watch?v=qvxpTUc4nX0&index=28&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID
`Foreign Key` a foreign key column references a primary key value of another/same table. This is used to make connections between tables within database

## NOT NULL Foreign Key
https://www.youtube.com/watch?v=tANmR6BJSDg&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=29
`NOT NULL Foreign Key` foreign key columns with NOT NULL as a column characteristic will prevent any rows that do not have a parent primary key. This will force a relationship for every column.

## Foreign Key Constraints
https://www.youtube.com/watch?v=rOyZtFo4qaU&index=30&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID
`Foreign Key Constraints` are used for referential integrity. Referential integrity protects the connections between tables.
`ON DELETE` and `ON UPDATE` refers to foreign key constraints. When we update the `parent`, we want the `children` to do something.

`One-to-Many`
```
Child-a \            / Child-1
Child-b -> Parent <- Child-2
Child-c /     PK     \ Child-3
   FK                       FK
```

1, `RESTRICT` equivalent to `NO ACTION`
      `ON DELETE` error when try to delete parent entity, so the parent entity is not deleted.
      `ON UPDATE` error when try to change parent entity primary key, so the parent entity primary key won't change.
2, `CASCADE` will do whatever we do to the parent to the children
      `ON DELETE` will delete child entities when delete parent entity.
      `ON UPDATE` when update parent entity primary keys, will update child entities FKs that reference the parent primary key.
3, `SET NULL` set child entities to null to that specific value. the children can not have `NOT NULL` characteristic.
      `ON DELETE` will set child entities FKs that reference parent primary key to `null` when delete parent entity.
      `ON UPDATE` will set child entities FKs that reference parent primary key to `null` when update parent entity primary keys.

Examples:
1, foreign-key-constraints/01-restrict-on-delete.sql
2, foreign-key-constraints/02-cascade-on-delete.sql
3, foreign-key-constraints/03-set-null-on-delete.sql

## Simple Key, Composite Key, Compound Key
https://www.youtube.com/watch?v=vsGDtnBCwgg&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=31
`Simple Key` is key with only one column
`Composite Key` is key with multiple columns where at least one column is not a key in itself
`Compound Key` is key where all columns are themselves keys. Compound keys are most commonly seen in intermediary table (junction tables).
