package com.company.app.data;

import com.company.app.data.entities.User;
import org.hibernate.Session;

import java.util.Date;

public class App {
    public static void main(String... args){
        Session session = HibernateUtil.getSessionFactory().openSession();
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

        session.close();
    }
}
