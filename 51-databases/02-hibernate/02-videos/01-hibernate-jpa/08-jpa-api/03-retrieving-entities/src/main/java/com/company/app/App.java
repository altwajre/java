package com.company.app;

import com.company.app.model.Bank;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209960.html

first arg is the class of the entity, second arg is the identifier
entityManager.find(Bank.class, 1L) = session.get(Bank.class, 1L)
entityManager.getReference(Bank.class, 1L) = session.load(Bank.class, 1L)
 */
public class App {
  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("infinite-finances");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    findExistId(entityManager);

    findNotExistId(entityManager);

    getRefExistId(entityManager);

    getRefNotExistId(entityManager);

    entityManager.getTransaction().commit();
    entityManager.close();
    entityManagerFactory.close();
  }

  private static void findExistId(EntityManager entityManager) {
    Bank bank = entityManager.find(Bank.class, 1L);
    System.out.println("entityManager.contains(bank)=" + entityManager.contains(bank)); // bank entity is in persistence context
    System.out.println(bank.getName());
  }
/*
Hibernate: select bank0_.BANK_ID as BANK_ID1_1_0_, bank0_.ADDRESS_LINE_1 as ADDRESS_2_1_0_, bank0_.ADDRESS_LINE_2 as ADDRESS_3_1_0_, bank0_.CITY as CITY4_1_0_, bank0_.STATE as STATE5_1_0_, bank0_.ZIP_CODE as ZIP_CODE6_1_0_, bank0_.CREATED_BY as CREATED_7_1_0_, bank0_.CREATED_DATE as CREATED_8_1_0_, bank0_.IS_INTERNATIONAL as IS_INTER9_1_0_, bank0_.LAST_UPDATED_BY as LAST_UP10_1_0_, bank0_.LAST_UPDATED_DATE as LAST_UP11_1_0_, bank0_.NAME as NAME12_1_0_ from bank bank0_ where bank0_.BANK_ID=?
true
Second National Trust
 */

  private static void findNotExistId(EntityManager entityManager) {
    try{
      Bank bank = entityManager.find(Bank.class, 123L);
      System.out.println("entityManager.contains(bank)=" + entityManager.contains(bank)); // bank entity is in persistence context
      System.out.println(bank.getName());
    }
    catch (NullPointerException e) {
      e.printStackTrace();
    }
  }
/*
Hibernate: select bank0_.BANK_ID as BANK_ID1_1_0_, bank0_.ADDRESS_LINE_1 as ADDRESS_2_1_0_, bank0_.ADDRESS_LINE_2 as ADDRESS_3_1_0_, bank0_.CITY as CITY4_1_0_, bank0_.STATE as STATE5_1_0_, bank0_.ZIP_CODE as ZIP_CODE6_1_0_, bank0_.CREATED_BY as CREATED_7_1_0_, bank0_.CREATED_DATE as CREATED_8_1_0_, bank0_.IS_INTERNATIONAL as IS_INTER9_1_0_, bank0_.LAST_UPDATED_BY as LAST_UP10_1_0_, bank0_.LAST_UPDATED_DATE as LAST_UP11_1_0_, bank0_.NAME as NAME12_1_0_ from bank bank0_ where bank0_.BANK_ID=?
java.lang.NullPointerException
	at com.company.app.App.main(App.java:20)
entityManager.contains(bank)=false
 */

  private static void getRefExistId(EntityManager entityManager) {
    Bank bank = entityManager.getReference(Bank.class, 1L);
    System.out.println("entityManager.contains(bank)=" + entityManager.contains(bank)); // bank entity is in persistence context
    System.out.println(bank.getName());
  }
/*
entityManager.contains(bank)=true
Hibernate: select bank0_.BANK_ID as BANK_ID1_1_0_, bank0_.ADDRESS_LINE_1 as ADDRESS_2_1_0_, bank0_.ADDRESS_LINE_2 as ADDRESS_3_1_0_, bank0_.CITY as CITY4_1_0_, bank0_.STATE as STATE5_1_0_, bank0_.ZIP_CODE as ZIP_CODE6_1_0_, bank0_.CREATED_BY as CREATED_7_1_0_, bank0_.CREATED_DATE as CREATED_8_1_0_, bank0_.IS_INTERNATIONAL as IS_INTER9_1_0_, bank0_.LAST_UPDATED_BY as LAST_UP10_1_0_, bank0_.LAST_UPDATED_DATE as LAST_UP11_1_0_, bank0_.NAME as NAME12_1_0_ from bank bank0_ where bank0_.BANK_ID=?
Second National Trust
 */

  private static void getRefNotExistId(EntityManager entityManager) {
    try{
      Bank bank = entityManager.getReference(Bank.class, 123L);
      System.out.println("entityManager.contains(bank)=" + entityManager.contains(bank)); // bank entity is in persistence context
      System.out.println(bank.getName());
    }catch (EntityNotFoundException e){
      e.printStackTrace();
    }
  }
/*
entityManager.contains(bank)=true
Hibernate: select bank0_.BANK_ID as BANK_ID1_1_0_, bank0_.ADDRESS_LINE_1 as ADDRESS_2_1_0_, bank0_.ADDRESS_LINE_2 as ADDRESS_3_1_0_, bank0_.CITY as CITY4_1_0_, bank0_.STATE as STATE5_1_0_, bank0_.ZIP_CODE as ZIP_CODE6_1_0_, bank0_.CREATED_BY as CREATED_7_1_0_, bank0_.CREATED_DATE as CREATED_8_1_0_, bank0_.IS_INTERNATIONAL as IS_INTER9_1_0_, bank0_.LAST_UPDATED_BY as LAST_UP10_1_0_, bank0_.LAST_UPDATED_DATE as LAST_UP11_1_0_, bank0_.NAME as NAME12_1_0_ from bank bank0_ where bank0_.BANK_ID=?
javax.persistence.EntityNotFoundException: Unable to find com.company.app.model.Bank with id 123
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl$JpaEntityNotFoundDelegate.handleEntityNotFound(EntityManagerFactoryBuilderImpl.java:158)
	at org.hibernate.proxy.AbstractLazyInitializer.checkTargetState(AbstractLazyInitializer.java:235)
	at org.hibernate.proxy.AbstractLazyInitializer.initialize(AbstractLazyInitializer.java:157)
	at org.hibernate.proxy.AbstractLazyInitializer.getImplementation(AbstractLazyInitializer.java:259)
	at org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer.invoke(JavassistLazyInitializer.java:73)
	at com.company.app.model.Bank_$$_jvst805_1.getName(Bank_$$_jvst805_1.java)
	at com.company.app.App.main(App.java:33)
 */

}
