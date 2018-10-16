# Indexes
https://www.youtube.com/watch?v=EZ3jBam2IEA&list=PL_c9BZzLwBRK0Pc28IdvPQizD2mJlgoID&index=40
`Indexes` it is an extremely important step of database design. Without Indexes database queries can take a substantial amount of time and hog system resources.

The main two classifications of indexes are clustered and non-clustered indexes. 
`Clustered indexes` are usually the primary key and determine how the data is actually stored in the table. These are the fastest and most effective. 
`Non-clustered indexes` sort a reference to data that is still fast but doesn't actually determine how the data is structured. 

`Don't over use indexes`. Having loads of indexes on columns that are barely ever in a `where clause or join` can bog down the database. 
This is because every time the table is updated the index must also be updated! 

When a database looks through a table for certain where conditions on a column that does not have an index, it does an entire `table scan`. 
This is not a good practice for large databases (or even small databases but not as bad). 
With an index the database will do an index seek and quickly find the data you are looking for.

`Composite indexes` consist of two or more columns within one index. Use these when you plan on using the two columns together for a database query WHERE clause.
