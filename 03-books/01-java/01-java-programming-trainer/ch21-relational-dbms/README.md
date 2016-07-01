# Derby

## download

http://db.apache.org/derby/derby_downloads.html

> download .zip, unzip it into root directory on mac, rename the folder as derby

> add following two lines to `.bash_profile`

```
export PATH=~/derby/bin:$PATH
export DERBY_HOME=~/derby
```

> `startNetworkServer`

> `ij`

```
connect 'jdbc:derby://localhost:1527/Lesson21;create=true';
CREATE TABLE Employee (
>   EMPNO int NOT NULL,
>   ENAME varchar (50) NOT NULL,
>   JOB_TITLE varchar (150) NOT NULL
> );
INSERT INTO Employee values (7369, 'John Smith', 'Clerk'), (7499, 'Joe Allen', 'Salesman'), (7521, 'Mary Lou', 'Director');
select * from Employee;
```
