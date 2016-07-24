# Performing Basic Queries in Hive

*cloudera*

- `Query Editors`, `Hive`

- select queries

```
select * from customers;
select acctno from transactions where partno='A56-7W';
```
- union

```
create table AllTransactions as
select * from
(select * from transactions a
 union all
 select * from moretransactions b)
 tmp;
select * from alltransactions;
```

- create table from select query

```
create table customers_ca as
select * from customers where state='CA';
select * from customers_ca;
```

- join

```
create table main as
select * from customers
join alltransactions
on (customers.acct_no = alltransactions.acctno);
select * from main;
```


