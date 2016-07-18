/*
Problem
You need to create a table with a name guaranteed not to exist.

Solution
If you create a TEMP table, it doesn’t matter whether a permanent table with that name exists. Otherwise, try to
generate a value that is unique to your client program and incorporate it into the table name.

MySQL is a multiple-client database server, so if a given script that creates a transient table might be invoked by
several clients simultaneously, take care that multiple invocations of the script do not fight over the same table name.
If the script creates tables using CREATE TEMP table, there is no problem because different clients can create temporary
tables having the same name without clashing.

If you cannot or do not want to use a TEMP table, make sure that each invocation of the script creates a uniquely named
table and drops the table when it is no longer needed. To accomplish this, incorporate into the name some value
guaranteed to be unique per invocation. A timestamp won’t work if it’s possible for two instances of a script to be
invoked within the timestamp resolution. A random number may be better, but random numbers only reduce the possibility
of name clashes, not eliminate it. Process ID (PID) values are a better source of unique values. PIDs are reused over
time, but never for two processes at the same time, so a given PID is guaranteed to be unique among the set of currently
executing processes.

The PID approach should not be used in contexts such as scripts run within multithreaded web servers in which all
threads share the same process ID.

Connection identifiers are another source of unique values. The MySQL server reuses these numbers over time, but no two
simultaneous connections to the server have the same ID. To get your connection ID, execute this statement and retrieve
the result:
*/

SELECT CONNECTION_ID();
/*
+-----------------+
| CONNECTION_ID() |
+-----------------+
|              76 |
+-----------------+
1 row in set (0.00 sec)
*/
