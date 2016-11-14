# Importing from Database Using Sqoop

- `$ sudo service mysqld status` - check mysql is running

- `$ mysql -u root -p`, password: `cloudera`

```
mysql> 
```

- `mysql> SHOW DATABASES;`

```
+--------------------+
| Database           |
+--------------------+
| information_schema |
| cm                 |
| firehose           |
| hue                |
| metastore          |
| mysql              |
| nav                |
| navms              |
| oozie              |
| retail_db          |
| rman               |
| sentry             |
+--------------------+
12 rows in set (0.01 sec)
```

```
mysql> CREATE DATABASE xyzcorp;
Query OK, 1 row affected (0.01 sec)

mysql> use xyzcorp;
Database changed
mysql> CREATE TABLE Customers
    -> (Acct_no TEXT,Acct_name TEXT,Address TEXT,City TEXT,State TEXT,ZIP TEXT);
Query OK, 0 rows affected (0.00 sec)

mysql> describe Customers;
+-----------+------+------+-----+---------+-------+
| Field     | Type | Null | Key | Default | Extra |
+-----------+------+------+-----+---------+-------+
| Acct_no   | text | YES  |     | NULL    |       |
| Acct_name | text | YES  |     | NULL    |       |
| Address   | text | YES  |     | NULL    |       |
| City      | text | YES  |     | NULL    |       |
| State     | text | YES  |     | NULL    |       |
| ZIP       | text | YES  |     | NULL    |       |
+-----------+------+------+-----+---------+-------+
6 rows in set (0.00 sec)

mysql> LOAD DATA LOCAL INFILE
    -> '/home/cloudera/Downloads/Customers.csv'
    -> INTO TABLE Customers
    -> FIELDS TERMINATED BY ','
    -> LINES TERMINATED BY '\r';
Query OK, 10 rows affected (0.00 sec)
Records: 10  Deleted: 0  Skipped: 0  Warnings: 0

mysql> SELECT * FROM Customers;
+---------+--------------------+----------------------+-----------------+-------+-------+
| Acct_no | Acct_name          | Address              | City            | State | ZIP   |
+---------+--------------------+----------------------+-----------------+-------+-------+
| 1000245 | Toms Produce       | 100 Toms Lane        | Charlotte       | NC    | 28201 |
| 1000356 | Contruction Depot  | 102 Peachtree Road   | Atlanta         | GA    | 30350 |
| 1000567 | Jupiter Networks   | 203 Packet Road      | Sunnyvale       | CA    | 94085 |
| 1000890 | Scottish Petroleum | 304 Scottish Blvd    | Houston         | TX    | 77079 |
| 1000446 | Yummy Restaurants  | 405 Cardinal Avenue  | Louisville      | KY    | 40203 |
| 1000386 | Andicle            | 506 Database Drive   | Redwood City    | CA    | 94061 |
| 1000336 | Banana Computer    | 607 Infinity Road    | Cupertino       | CA    | 94024 |
| 1000998 | McDougals          | 708 Burgerton Avenue | Oak Brook       | IL    | 60523 |
| 1000326 | Jimsung            | 809 Electronic Lane  | Ridgefield Park | NJ    | 07660 |
| 1000269 | Dash Cellular      | 900 Dash Drive       | Overland Park   | KS    | 66204 |
+---------+--------------------+----------------------+-----------------+-------+-------+
10 rows in set (0.00 sec)

mysql> exit
Bye
[cloudera@quickstart ~]$ sqoop import \
> --connect jdbc:mysql://127.0.01:3306/xyzcorp \
> --table Customers \
> --username root -P \
> --direct -m 1;
Warning: /usr/lib/sqoop/../accumulo does not exist! Accumulo imports will fail.
Please set $ACCUMULO_HOME to the root of your Accumulo installation.
16/07/22 20:52:18 INFO sqoop.Sqoop: Running Sqoop version: 1.4.6-cdh5.7.0
Enter password: cloudera
16/07/22 20:53:02 INFO manager.MySQLManager: Preparing to use a MySQL streaming resultset.
16/07/22 20:53:02 INFO tool.CodeGenTool: Beginning code generation
...
[cloudera@quickstart ~]$ hdfs dfs -ls
Found 5 items
drwxr-xr-x   - cloudera cloudera          0 2016-07-22 17:59 .Trash
drwxr-xr-x   - cloudera cloudera          0 2016-07-22 20:53 Customers
```

