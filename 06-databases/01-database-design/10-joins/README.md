# Introduction to Joins
https://www.youtube.com/watch?v=agC06TR_MJU&index=42&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID
`Joining` is the process of taking data from multiple tables and putting it into one generated view.

A join statement is put within a SELECT and at the end you will give a join condition, which tells the database how to connect your data. 
The column given within the join condition must be preceded by a table name if the column name is the same in both tables. 
When you precede a column with a table name it is known as a qualified column. 

The relationship that you use within the join condition is often already defined in your database (usually fk-pk connection) but doesn't have to be. 
If it is not already in your database it is known as an ad hoc relationship.

## Inner Join
https://www.youtube.com/watch?v=2lrNZdY3fE4&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=43
`Inner Join` selects all rows from both tables as long as there is a match between the columns in both tables.
http://www.w3schools.com/sql/sql_join_inner.asp

Example: inner-join.sql

## Inner Join on 3 Tables
https://www.youtube.com/watch?v=B47Idwxi3jI&index=45&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID
Inner joins across 3 or more tables will only return results that meet all join requirements.

Example: inner-join-3-tables.sql

## Introduction to Outer Joins
https://www.youtube.com/watch?v=vPnajSbemUM&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=46
Outer joins will take all rows from either the left, right or both tables. This is good for when you want to return all of the rows for only one side of join,
but not the other. For example, you could return every single user, but only return some of the rows of an associated table.

Outer Joins
1, `Left Join`
http://www.w3schools.com/sql/sql_join_left.asp
2, `Right Join`
http://www.w3schools.com/sql/sql_join_right.asp
3, `Full Join`
http://www.w3schools.com/sql/sql_join_full.asp
Note: MySQL version of Northwind database: https://github.com/dalers/mywind

Example: outer-left-join.sql

## Right Outer Join
https://www.youtube.com/watch?v=NHexrFjeO6o&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=47
Right outer joins are similar to left outer joins. They basically do the same thing. Left is right and right is left and the same effect can be rendered by merely flipping the tables. 
Right outer joins are in no way deprecated they are just not all too common. For consistency’s sake it is a common practice to use left outer joins instead of right outer joins.

## Outer Join Across 3 Tables
https://www.youtube.com/watch?v=EqSI9_03qRM&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=49

Example: outer-join-3-tables.sql

## Alias
https://www.youtube.com/watch?v=wlTRroeVkj0&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=50
`Alias` is another name for a column or table. This is useful for when you want to change the way a query looks, make it easier to type out, or change the way the view is presented.

## Self Join
https://www.youtube.com/watch?v=W0p8KP0o8g4&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=51
`Self join` is when you join a table with itself. Make a table to be two tables and join them. 
            This can be useful for replacing values within the table or having more than one of the same column for some reason.
http://www.tutorialspoint.com/sql/sql-self-joins.htm

Example: self-join.sql
