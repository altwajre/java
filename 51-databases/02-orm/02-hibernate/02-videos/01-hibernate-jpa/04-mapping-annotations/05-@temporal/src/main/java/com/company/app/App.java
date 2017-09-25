package com.company.app;

import com.company.app.model.TimeTest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

/*

https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/video209929.html

Date and Time format

  @Temporal(TemporalType.TIMESTAMP)
  @Temporal(TemporalType.DATE)
  @Temporal(TemporalType.TIME)

 */
public class App {
  public static void main(String[] args) {
    final SessionFactory sessionFactory = SessionFactoryBuilder.get();
    final Session session = sessionFactory.openSession();

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
Hibernate: insert into time_test (DATE_COLUMN, DATETIME_COLUMN, SQL_DATE_COLUMN, SQL_DATETIME_COLUMN, SQL_TIME_COLUMN, SQL_TIMESTAMP_COLUMN, TIME_COLUMN, TIMESTAMP_COLUMN) values (?, ?, ?, ?, ?, ?, ?, ?)
Hibernate: select timetest0_.TIME_TEST_ID as TIME_TES1_1_0_, timetest0_.DATE_COLUMN as DATE_COL2_1_0_, timetest0_.DATETIME_COLUMN as DATETIME3_1_0_, timetest0_.SQL_DATE_COLUMN as SQL_DATE4_1_0_, timetest0_.SQL_DATETIME_COLUMN as SQL_DATE5_1_0_, timetest0_.SQL_TIME_COLUMN as SQL_TIME6_1_0_, timetest0_.SQL_TIMESTAMP_COLUMN as SQL_TIME7_1_0_, timetest0_.TIME_COLUMN as TIME_COL8_1_0_, timetest0_.TIMESTAMP_COLUMN as TIMESTAM9_1_0_ from time_test timetest0_ where timetest0_.TIME_TEST_ID=?
TimeTest(timeTestId=4, datetimeColumn=2017-09-24 21:55:36.0, timestampColumn=2017-09-24 21:55:36.0, dateColumn=2017-09-24, timeColumn=20:55:36, sqlDatetimeColumn=2017-09-24 21:55:36.0, sqlTimestampColumn=2017-09-24 21:55:36.0, sqlDateColumn=2017-09-24, sqlTimeColumn=20:55:36)
 */
