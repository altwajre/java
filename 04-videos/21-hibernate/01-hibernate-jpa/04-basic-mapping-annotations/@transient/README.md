# @transient

## Project

resources

```
hibernate.cfg.xml
hibernate.properties
```

ignore mapping, so this field does not show up in table column

```
    @Transient
    private boolean valid;
```

## Workbench

verify the inserted record

```
use ifinances;
select * from FINANCES_USER;
```
