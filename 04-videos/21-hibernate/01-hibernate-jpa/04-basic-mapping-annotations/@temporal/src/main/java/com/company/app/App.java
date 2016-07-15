package com.company.app;

import com.company.app.model.TimeTest;
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

        TimeTest test = new TimeTest(new Date());
        session.save(test);
        session.getTransaction().commit();

        session.refresh(test);

        System.out.println(test.toString());

        session.close();
        sessionFactory.close();
    }
}
/*
output: to have better time format

 timeTestId=1,
 datetimeColumn=2016-07-15 11:49:25.0,
 timestampColumn=2016-07-15 11:49:25.0,
 dateColumn=2016-07-15,
 timeColumn=11:49:25,
 sqlDatetimeColumn=2016-07-15 11:49:25.0,
 sqlTimestampColumn=2016-07-15 11:49:25.0,
 sqlDateColumn=2016-07-15,
 sqlTimeColumn=11:49:25]
 */
