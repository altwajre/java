# @temporal

## Project

resources

```
hibernate.cfg.xml
hibernate.properties
```

to have better time format

```
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIMESTAMP_COLUMN")
    private Date timestampColumn;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_COLUMN")
    private Date dateColumn;
```

## Workbench

verify the inserted record

```
use ifinances;
select * from time_test;
```
