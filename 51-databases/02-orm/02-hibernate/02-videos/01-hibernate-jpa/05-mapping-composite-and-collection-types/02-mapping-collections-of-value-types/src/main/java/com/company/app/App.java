package com.company.app;

import com.company.app.model.Bank;
import com.company.app.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

public class App {
  public static void main(String[] args) {
    final SessionFactory sessionFactory = SessionFactoryBuilder.get();
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    Bank bank = new Bank();
    bank.setName("Federal Trust");
    bank.setAddressLine1("33 Wall Street");
    bank.setAddressLine2("Suite 233");
    bank.setCity("New York");
    bank.setState("NY");
    bank.setZipCode("12345");
    bank.setInternational(false);
    bank.setCreatedBy("Kevin");
    bank.setCreatedDate(new Date());
    bank.setLastUpdatedBy("Kevin");
    bank.setLastUpdatedDate(new Date());

    session.save(bank);

//    User user = new User();
//    user.setBirthDate(new Date());
//    user.setCreatedBy("kevin");
//    user.setCreatedDate(new Date());
//    user.setEmailAddress("kmb385@yahoo.com");
//    user.setFirstName("Kevin");
//    user.setLastName("Bowersox");
//    user.setLastUpdatedBy("kevin");
//    user.setLastUpdatedDate(new Date());
//
//    session.save(user);


    session.getTransaction().commit();

    sessionFactory.close();
  }
}
