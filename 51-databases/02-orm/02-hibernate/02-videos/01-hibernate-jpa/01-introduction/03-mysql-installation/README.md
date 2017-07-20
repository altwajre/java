# MySQL Installation

## MySQL Workbench

```
click "Create a new Schema" button, Name: ifinances, click "Apply" and "Finish"
click "Users and Privileges" on the left, click "Add Account", Login Name: infinite, Password: skills
click "Schema Privileges" tag, click "Add Entry...", click "Selected schema:" and select "ifinances", click "Select All", click "Apply"
```

### Setup New Connection

```
click "Home", click "Setup New Connection" button
Connection Name: ifinances  Username: infinite  Password: skills (Store in Keychain...)
Login to ifinances connection
Select database - use ifinances;
copy /source-code/Chapter 1/InfiniteFinancesSchema.sql to Query 1 window
click "Execute" button, click refresh, you will see schemas is built under ifinances
```
