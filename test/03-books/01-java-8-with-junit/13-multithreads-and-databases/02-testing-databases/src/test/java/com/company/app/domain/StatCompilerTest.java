package com.company.app.domain;

import com.company.app.controller.QuestionController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

public class StatCompilerTest {
  @Mock
  private QuestionController controller;
  @InjectMocks
  private StatCompiler statCompiler;

  @Before
  public void setup() {
    statCompiler = new StatCompiler();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void questionTextDoesStuffBDD() {
    // given
    given(controller.find(1))
        .willReturn(new BooleanQuestion("text1"));
    given(controller.find(2))
        .willReturn(new BooleanQuestion("text2"));
    List<BooleanAnswer> answers = new ArrayList<>();
    answers.add(new BooleanAnswer(1, true));
    answers.add(new BooleanAnswer(2, true));

    // when
    final Map<Integer, String> questionText = statCompiler.questionText(answers);
    System.out.println(questionText);

    // then
    Map<Integer, String> expected = new HashMap<>();
    expected.put(1, "text1");
    expected.put(2, "text2");
    then(questionText).isEqualTo(expected);
  }

  @Test
  public void questionTextDoesStuff() {
    // Arrange
    when(controller.find(1))
        .thenReturn(new BooleanQuestion("text1"));
    when(controller.find(2))
        .thenReturn(new BooleanQuestion("text2"));
    List<BooleanAnswer> answers = new ArrayList<>();
    answers.add(new BooleanAnswer(1, true));
    answers.add(new BooleanAnswer(2, true));

    // Act
    final Map<Integer, String> questionText = statCompiler.questionText(answers);
    System.out.println(questionText);

    // Assert
    Map<Integer, String> expected = new HashMap<>();
    expected.put(1, "text1");
    expected.put(2, "text2");
    assertThat(questionText, equalTo(expected));
  }
}