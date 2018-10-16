package com.company.app;

import com.company.app.model.Account;
import com.company.app.model.Credential;
import com.company.app.model.Transaction;
import com.company.app.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public enum SessionFactoryBuilder {

  INSTANCE;

  private static final SessionFactory sessionFactory = buildSessionFactory();

  private static SessionFactory buildSessionFactory() {
    try {
      Configuration configuration = new Configuration();
      configuration.addAnnotatedClass(Account.class);
      configuration.addAnnotatedClass(User.class);
      configuration.addAnnotatedClass(Transaction.class);
      configuration.addAnnotatedClass(Credential.class);
      configuration.configure();
      return configuration
          .buildSessionFactory(new StandardServiceRegistryBuilder()
              .applySettings(configuration.getProperties()) // load xml config
              .build());
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("There was an error building the factory");
    }
  }

  public static SessionFactory get(){
    return sessionFactory;
  }
}
