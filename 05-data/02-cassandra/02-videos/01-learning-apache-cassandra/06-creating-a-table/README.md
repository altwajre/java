# Creating A Table

```
cqlsh> USE home_security;
cqlsh:home_security> CREATE TABLE activity (home_id text, datetime timestamp, event text, code_used text, PRIMARY KEY (home_id, datetime)) WITH CLUSTERING ORDER BY (datetime DESC);
cqlsh:home_security> DESCRIBE TABLE activity;
cqlsh:home_security> DESCRIBE TABLES;
```

## Lab

```
cqlsh:home_security> CREATE TABLE home (home_id text, address text, city text, state text, zip text, contact_name text, phone text, alt_phone text, phone_password text, email text, main_code text, guest_code text, PRIMARY KEY (home_id));
cqlsh:home_security> DESCRIBE TABLE home;
cqlsh:home_security> DESC TABLE home;
```
