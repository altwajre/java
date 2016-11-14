# Importing Data from HDFS into Hive

*cloudera*

- `Query Editors`, `Pig`

## create database

- enter following query and click `Execute`

```
create database rsk;
use rsk;
```

- click refresh, select `rsk` database

## create table

**internal table**

- click `New query`, enter following query 

```
create table transactions (AcctNo string,PartNo string,Price decimal(9,2),Qty int)
row format delimited
fields terminated by ','
stored as textfile;
```

- click refresh, select `transactions` table

*load data into table*

- click `New query`, enter following query

```
load data inpath '/user/cloudera/Transactions.csv' into table transactions;
```

- `File Browser`, `/user/hive/warehouse/rsk.db/transactions/Transactions.csv`

**external table**

- click `New query`, enter following query 

```
create external table moretransactions (AcctNo string,PartNo string,Price decimal(9,2),Qty int)
row format delimited
fields terminated by ','
stored as textfile
location '/user/cloudera';
```

*load data into table*

- click `New query`, enter following query

```
load data inpath '/user/cloudera/MoreTransactions.csv' into table moretransactions;
```
