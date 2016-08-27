# Manual Import

$ wget https://raw.github.com/pwillhan/java/master/05-data/11-hadoop/02-videos/01-learning-hadoop-2/source-code/Customers.csv

$ wget https://raw.github.com/pwillhan/java/master/05-data/11-hadoop/02-videos/01-learning-hadoop-2/source-code/Transactions.csv

$ wget https://raw.github.com/pwillhan/java/master/05-data/11-hadoop/02-videos/01-learning-hadoop-2/source-code/MoreTransactions.csv

$ wget https://raw.github.com/pwillhan/java/master/05-data/11-hadoop/02-videos/01-learning-hadoop-2/source-code/Names.txt

**Load files into `hdfs`**

- `File Browser`, `Upload`, `Files`, select `MoreTransactions.csv` from to Downloads folder to upload

- `$ hdfs dfs -ls` - list files in hdfs

```
Found 3 items
drwxr-xr-x   - cloudera cloudera          0 2016-07-22 17:59 .Trash
-rw-r--r--   1 cloudera cloudera      34804 2016-07-22 17:59 MoreTransactions.csv
```

- `$ hdfs dfs -copyFromLocal Transactions.csv` - copy file into hdfs

- `$ hdfs dfs -ls` - list files in hdfs

```
Found 3 items
drwxr-xr-x   - cloudera cloudera          0 2016-07-22 17:59 .Trash
-rw-r--r--   1 cloudera cloudera      34804 2016-07-22 17:59 MoreTransactions.csv
-rw-r--r--   1 cloudera cloudera      40502 2016-07-22 18:04 Transactions.csv
```

