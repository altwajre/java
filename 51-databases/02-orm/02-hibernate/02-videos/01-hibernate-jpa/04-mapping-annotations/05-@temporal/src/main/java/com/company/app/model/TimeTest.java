package com.company.app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Entity
@Table(name = "time_test")
public class TimeTest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TIME_TEST_ID")
  private Long timeTestId;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "DATETIME_COLUMN")
  private Date datetimeColumn;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "TIMESTAMP_COLUMN")
  private Date timestampColumn;

  @Temporal(TemporalType.DATE)
  @Column(name = "DATE_COLUMN")
  private Date dateColumn;

  @Temporal(TemporalType.TIME)
  @Column(name = "TIME_COLUMN")
  private Date timeColumn;

  @Column(name = "SQL_DATETIME_COLUMN")
  private java.sql.Timestamp sqlDatetimeColumn;

  @Column(name = "SQL_TIMESTAMP_COLUMN")
  private java.sql.Timestamp sqlTimestampColumn;

  @Column(name = "SQL_DATE_COLUMN")
  private java.sql.Date sqlDateColumn;

  @Column(name = "SQL_TIME_COLUMN")
  private java.sql.Time sqlTimeColumn;

  public TimeTest(){

  }

  public TimeTest(Date date){
    this.datetimeColumn = date;
    this.timestampColumn = date;
    this.timeColumn = date;
    this.dateColumn = date;

    this.sqlDatetimeColumn = new java.sql.Timestamp(date.getTime());
    this.sqlTimestampColumn = new java.sql.Timestamp(date.getTime());
    this.sqlDateColumn = new java.sql.Date(date.getTime());
    this.sqlTimeColumn = new java.sql.Time(date.getTime());
  }
}
