# JPA API

> Java Persistence API (JPA)

- Configure a JPA application
- Discover JPA interfaces
- Learn to invoke persistence manager methods that manage crud operations and transactions
- Understand the similarities between Session and EntityManager

> EntityManager vs Session

| EntityManager              | Session                   |
|----------------------------|---------------------------|
| persist()                  | save()                    |
| merge()                    | merge()                   |
| remove()                   | delete()                  |
| detach(), close(), clear() | evict(), close(), clear() |
| find(), getReference()     | get(), load()             |
| flush()                    | flush()                   |

> Important Lesson Notes

- Hibernate is our JPA implementation
- Persistence Lifecycle
- Application Managed/Resource Local Transactions
- populate.sql
