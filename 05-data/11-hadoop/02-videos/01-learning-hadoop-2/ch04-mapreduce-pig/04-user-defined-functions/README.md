# Using User-defined Functions in Pig

*cloudera*

- `Query Editors`, `Pig`

- enter following script, and click `Play` button

```
register /usr/lib/pig/datafu.jar;
define Quartile datafu.pig.stats.StreamingQuantile('0.0','0.5','1.0');
trans = load 'Results/part-r-00000' as (AcctNo:chararray,PartNo:chararray,Price:chararray,Qty:int);
filtered = foreach trans generate Qty;
qty_quartile = foreach (group filtered all) generate Quartile(filtered.Qty);
store qty_quartile into 'Quartiles';
```

- wait for it to be completed

- `File Browser`, `Quartiles/part-m-00000`

```
(1.0,34.0,556.0)
```
