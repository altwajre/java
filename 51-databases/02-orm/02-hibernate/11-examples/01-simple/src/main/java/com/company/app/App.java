package com.company.app;

import io.vertx.core.Vertx;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

public class App {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    System.out.println("#createPerson");
    createPerson(entityManager);

    // wait for 1 second before get the saved record
    vertx.setTimer(1000, t -> {
      System.out.println("#getPerson");
      getPerson(entityManager);

      entityManager.close();
      entityManagerFactory.close();
      vertx.close();
    });
  }

  private static void getPerson(EntityManager entityManager) {
    final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);

    final Root<Person> root = criteriaQuery.from(Person.class);
    final Path<Long> idPath = root.get("id");
    criteriaQuery.select(root).where(
        criteriaBuilder.equal(idPath, 1));
    final TypedQuery<Person> query = entityManager.createQuery(criteriaQuery);
    final List<Person> resultList = query.getResultList();
    resultList.forEach(p -> {
      System.out.println(p.getName());
    });
  }

  private static void createPerson(EntityManager entityManager) {
    final Person person = new Person();
    person.setName("Tom");
    entityManager.getTransaction().begin();
    entityManager.persist(person);
    entityManager.getTransaction().commit();
  }
}
/*
Hibernate: drop table if exists hibernate_sequence
Hibernate: drop table if exists Person
Hibernate: create table hibernate_sequence (next_val bigint) engine=MyISAM
Hibernate: insert into hibernate_sequence values ( 1 )
Hibernate: create table Person (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
#createPerson
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: insert into Person (name, id) values (?, ?)
#getPerson
Hibernate: select person0_.id as id1_0_, person0_.name as name2_0_ from Person person0_ where person0_.id=1
Tom
 */
