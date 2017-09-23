package com.company.app;

import com.company.app.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209922.html

NOT working because unable to find User due to config mapping error
 */
public class App {
  public static void main(String[] args) {
    final SessionFactory sessionFactory = SessionFactoryBuilder.get();
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    User user = new User();
    user.setBirthDate(new Date());
    user.setCreatedBy("kevin");
    user.setCreatedDate(new Date());
    user.setEmailAddress("kmb385@yahoo.com");
    user.setFirstName("Kevin");
    user.setLastName("Bowersox");
    user.setLastUpdatedBy("kevin");
    user.setLastUpdatedDate(new Date());

    session.save(user);
    session.getTransaction().commit();

    sessionFactory.close();

  }
}
