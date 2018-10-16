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
Note:
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
