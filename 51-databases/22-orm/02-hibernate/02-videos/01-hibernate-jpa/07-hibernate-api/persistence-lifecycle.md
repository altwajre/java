# Persistence Lifecycle

> Entity States

- Transient - object constructed with the new operator that is not associated with any database row
- Persistent - any entity instance associated with a database row and contained within a persistence context
- Removed - an object scheduled for deletion
- Detached - object references to an entity once associated with a closed persistence context

> Persistence Context

- Cache of all persistent entity instances
- Corresponds with a session

```
SessionFactory factory = HibernateUtil.getSessionFactory();
Session session = factory.openSession();
// perform operations on entities
session.close();
```

## Hibernate API Actions

|   | Initial State | Action                   | End State  |
|---|---------------|--------------------------|------------|
| 1 | None          | get(), load()            | Persistent |
| 2 | Transient     | save(), saveOrUpdate()   | Persistent |
| 3 | Persistent    | delete()                 | Removed    |
| 4 | Detached      | update(), saveOrUpdate() | Persistent |
| 5 | Persistent    | evict()                  | Detached   |

> Example

```
Bank bank = new Bank(); // (2) Initial State - transient
SessionFactory factory = HibernateUtil.getSessionFactory();
Session session = factory.openSession();
Transaction transaction = session.beginTransaction();
session.save(bank); // (2) End State - persistent
transaction.commit();
session.close();

// (3) Initial State - Persistent
Session session2 = factory.openSession();
Transaction transaction2 = session2.beginTransaction();
session2.delete(bank); // (3) End State - Removed 
```
