package com.company.app;

import com.company.app.model.AccountType;
import com.company.app.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.Properties;

/*
hibernate_sequence

1, Before using session, need to run entityManager to create hibernate_sequence table
 */
public class App {

  public static void main(String[] args) {
    entityManager(); // SELECT * FROM ifinances.Person;

    session(); // SELECT * FROM ifinances.ACCOUNT_TYPE;
  }

  private static void entityManager() {
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    final Person person = new Person();
    person.setName("Tom");
    entityManager.getTransaction().begin();
    entityManager.persist(person);
    entityManager.getTransaction().commit();

    entityManager.close();
    entityManagerFactory.close();
  }

  private static void session() {
  /* Configuration */
    Configuration configuration = new Configuration();

    configuration.addAnnotatedClass(AccountType.class);
    configuration.addAnnotatedClass(Person.class);

    configuration.setProperties(new Properties() {
      {
        put("hibernate.connection.username", "infinite");
        put("hibernate.connection.password", "skills");
        put("hibernate.connection.driver_class",
            "com.mysql.cj.jdbc.Driver");
        put("hibernate.connection.url",
            "jdbc:mysql://localhost:3306/ifinances");
      }
    });

		/* Building SessionFactory */
    SessionFactory sessionFactory = configuration
        .buildSessionFactory(new StandardServiceRegistryBuilder(
        ).applySettings(configuration.getProperties()).build());

		/* Obtain Session and Call Persistence Methods */
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    AccountType type = new AccountType();
    type.setName("Checking");
    type.setCreatedDate(new Date());
    type.setLastUpdatedDate(new Date());
    type.setCreatedBy("kevinbowersox");
    type.setLastUpdatedBy("kevinbowersox");
    session.save(type);

    session.getTransaction().commit();
    session.close();
    sessionFactory.close();
  }
}
/*
Hibernate: drop table if exists ACCOUNT_TYPE
Hibernate: drop table if exists hibernate_sequence
Hibernate: drop table if exists Person
Hibernate: create table ACCOUNT_TYPE (ACCOUNT_TYPE_ID bigint not null auto_increment, LAST_UPDATED_DATE datetime, CREATED_BY varchar(255), CREATED_DATE datetime, LAST_UPDATED_BY varchar(255), NAME varchar(255), primary key (ACCOUNT_TYPE_ID)) engine=MyISAM
Hibernate: create table hibernate_sequence (next_val bigint) engine=MyISAM
Hibernate: insert into hibernate_sequence values ( 1 )
Hibernate: create table Person (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
Mon Oct 02 17:04:00 PDT 2017 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: insert into Person (name, id) values (?, ?)
 */
