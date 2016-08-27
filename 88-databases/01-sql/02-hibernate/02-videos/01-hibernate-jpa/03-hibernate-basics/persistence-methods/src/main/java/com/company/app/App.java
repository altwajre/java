package com.company.app;

import com.company.app.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class App
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory = configuration
                .buildSessionFactory(new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build());
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        User user = new User();
        user.setBirthDate(new Date());
        user.setCreatedBy("tom");
        user.setCreatedDate(new Date());
        user.setEmailAddress("tom@gmail.com");
        user.setFirstName("Tom");
        user.setLastName("Lee");
        user.setLastUpdatedBy("tom");
        user.setLastUpdatedDate(new Date());

        session.save(user);
        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
    }
}
/*
output:
Hibernate: insert into FINANCES_USER (BIRTH_DATE, CREATED_BY, CREATED_DATE, EMAIL_ADDRESS, FIRST_NAME, LAST_NAME, LAST_UPDATED_BY, LAST_UPDATED_DATE) values (?, ?, ?, ?, ?, ?, ?, ?)
 */
