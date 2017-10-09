# Criteria API

https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209984.html

- Explore the Hibernate & JPA criteria APIs
- Apply filter to criteria queries
- Paginate query results

> Criteria APIs

- Allows complex queries to be created programmatically via the Criteria (Hibernate) and CriteriaQuery (JPA) interfaces
- Beneficial for writing dynamic queries
- JPA Criteria API provides typesafe queries

> Criteria API

String jpqlHql = "select t from Transaction t";

vs

CriteriaBuilder cb = em.getCriteraBuilder();
CriteriaQuery<Transaction> criteriaQuery = cb.createQuery(Transaction.class);
Root<Transaction> root = criteriaQuery.from(Transaction.class);
criteriaQuery.select(root);
