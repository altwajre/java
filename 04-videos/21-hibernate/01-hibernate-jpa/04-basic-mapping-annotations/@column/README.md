# @column

## Project
resources

```
hibernate.cfg.xml
hibernate.properties
```

set updatable to false for create field

```
    @Column(name = "CREATED_DATE", updatable = false)
    private Date createdDate;
```

set nullable to false for a field

```
    @Column(name = "BIRTH_DATE", nullable = false)
    private Date birthDate;
```

## Workbench

verify the inserted record

```
use ifinances;
select * from FINANCES_USER;
```
