package com.company.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@Data
@AllArgsConstructor
class Student {
  private String name;
  private int age;
  private String course;
  private int score;
}

@FunctionalInterface
interface Database {
  String get(String name, int age);
}
class ScoreRetriever {
  private Database database;
  public Student retrieve(String name, int age, String course){
    int score = Integer.valueOf(database.get(name, age));
    final Student student = new Student(name, age, course, score);
    return student;
  }
}
public class DependencyInjectionTest {
  @Mock
  private Database database;
  @InjectMocks
  private ScoreRetriever retriever;

  @Before
  public void setup() {
    retriever = new ScoreRetriever();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void retrieverAppropriateScoreForValidCourseBDD() {
    // given
    given(database.get(contains("Tom"), eq(18)))
        .willReturn("88");

    // when
    final Student student = retriever.retrieve("Tommy", 18, "math");
    System.out.println(student);

    // then
    then(student.getScore()).isEqualTo(88);
  }
/*
Student(name=Tommy, age=18, course=math, score=88)
 */
}
