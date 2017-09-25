package com.company.app;

import com.company.app.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Calendar;
import java.util.Date;

/*
https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209930.html

> calculated column and dynamic assign value to it
	@Formula("lower(datediff(curdate(), birth_date)/365)")

Note: @Formula may tightly couple to database
 */
public class App {
  public static void main(String[] args) {
    final SessionFactory sessionFactory = SessionFactoryBuilder.get();
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    User user = new User();
    user.setBirthDate(getMyBirthday());
    user.setCreatedBy("kevin");
    user.setCreatedDate(new Date());
    user.setEmailAddress("kmb385@yahoo.com");
    user.setFirstName("Kevin");
    user.setLastName("Bowersox");
    user.setLastUpdatedBy("kevin");
    user.setLastUpdatedDate(new Date());

    session.save(user);
    session.getTransaction().commit();

    session.refresh(user);
    System.out.println(user.getAge());

    session.close();
    sessionFactory.close();
  }

  private static Date getMyBirthday() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, 1984);
    calendar.set(Calendar.MONTH, 6);
    calendar.set(Calendar.DATE, 19);
    return calendar.getTime();
  }
}
/*
Hibernate: insert into finances_user (BIRTH_DATE, CREATED_BY, CREATED_DATE, EMAIL_ADDRESS, FIRST_NAME, LAST_NAME, LAST_UPDATED_BY, LAST_UPDATED_DATE) values (?, ?, ?, ?, ?, ?, ?, ?)
Hibernate: select user0_.USER_ID as USER_ID1_0_0_, user0_.BIRTH_DATE as BIRTH_DA2_0_0_, user0_.CREATED_BY as CREATED_3_0_0_, user0_.CREATED_DATE as CREATED_4_0_0_, user0_.EMAIL_ADDRESS as EMAIL_AD5_0_0_, user0_.FIRST_NAME as FIRST_NA6_0_0_, user0_.LAST_NAME as LAST_NAM7_0_0_, user0_.LAST_UPDATED_BY as LAST_UPD8_0_0_, user0_.LAST_UPDATED_DATE as LAST_UPD9_0_0_, lower(datediff(curdate(), user0_.birth_date)/365) as formula0_0_ from finances_user user0_ where user0_.USER_ID=?
33
 */
