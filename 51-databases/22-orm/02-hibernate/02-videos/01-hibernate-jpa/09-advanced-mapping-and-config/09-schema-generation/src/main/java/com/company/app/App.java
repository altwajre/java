package com.company.app;

import com.company.app.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209975.html?autoStart=True

> META-INF/persistence.xml - hbm2ddl.auto
  <!--For POC and development, use hibernate.hbm2ddl.auto drops tables and recreate new tables-->
  <property name="hibernate.hbm2ddl.auto" value="create"/>
  <!--<property name="hibernate.hbm2ddl.auto" value="create-drop"/>-->
  <!--<property name="hibernate.hbm2ddl.auto" value="validate"/>-->
  <!--<property name="hibernate.hbm2ddl.auto" value="update"/>-->

 */
public class App {
  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;

    try{
      entityManagerFactory = Persistence.createEntityManagerFactory("infinite-finances");
      entityManager = entityManagerFactory.createEntityManager();
      entityManager.getTransaction().begin();

      Person person = new Person();
      person.setName("Tom");

      entityManager.persist(person);
      entityManager.getTransaction().commit();
    }
    catch (Exception e){
      entityManager.getTransaction().rollback();
      e.printStackTrace();
    }
    finally {
      entityManager.close();
      entityManagerFactory.close();
    }
  }
}
/*
Hibernate: drop table if exists hibernate_sequence
Hibernate: drop table if exists Person
Hibernate: create table hibernate_sequence (next_val bigint) engine=MyISAM
Hibernate: insert into hibernate_sequence values ( 1 )
Hibernate: create table Person (id bigint not null, Name varchar(255), primary key (id)) engine=MyISAM
Fri Oct 06 21:20:41 PDT 2017 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
Hibernate: select next_val as id_val from hibernate_sequence for update
Hibernate: update hibernate_sequence set next_val= ? where next_val=?
Hibernate: insert into Person (Name, id) values (?, ?)
 */
