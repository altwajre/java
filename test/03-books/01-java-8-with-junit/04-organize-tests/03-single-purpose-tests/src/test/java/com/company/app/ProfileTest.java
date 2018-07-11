package com.company.app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProfileTest {
  private Profile profile;
  private Criteria criteria;

  private Question questionReimbursesTuition;
  private Answer answerReimbursesTuition;
  private Answer answerDoesNotReimburseTuition;

  private Question questionIsThereRelocation;
  private Answer answerThereIsRelocation;
  private Answer answerThereIsNoRelocation;

  @Before
  public void setup(){
    // Arrange
    profile = new Profile("Bull Hockey, Inc.");
    criteria = new Criteria();

    questionReimbursesTuition = new BooleanQuestion(1, "Reimburses tuition?");
    answerReimbursesTuition = new Answer(questionReimbursesTuition, Bool.TRUE);
    answerDoesNotReimburseTuition = new Answer(questionReimbursesTuition, Bool.FALSE);

    questionIsThereRelocation =
        new BooleanQuestion(1, "Relocation package?");
    answerThereIsRelocation =
        new Answer(questionIsThereRelocation, Bool.TRUE);
    answerThereIsNoRelocation =
        new Answer(questionIsThereRelocation, Bool.FALSE);  }

  @Test
  public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
    // Arrange
    profile.add(answerDoesNotReimburseTuition);
    criteria.add(
        new Criterion(answerReimbursesTuition, Weight.MustMatch));

    // Act
    boolean matches = profile.matches(criteria);

    // Assert
    assertFalse(matches);
  }

  @Test
  public void matchAnswersTrueForAnyDontCareCriteria() {
    // Arrange
    profile.add(answerDoesNotReimburseTuition);
    criteria.add(
        new Criterion(answerReimbursesTuition, Weight.DontCare));

    // Act
    boolean matches = profile.matches(criteria);

    // Assert
    assertTrue(matches);
  }

  @Test
  public void matchAnswersTrueWhenAnyOfMultipleCriteriaMatch() {
    profile.add(answerThereIsRelocation);
    profile.add(answerDoesNotReimburseTuition);
    criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
    criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));

    boolean matches = profile.matches(criteria);

    assertTrue(matches);
  }

  @Test
  public void matchAnswersFalseWhenNoneOfMultipleCriteriaMatch() {
    profile.add(answerThereIsNoRelocation);
    profile.add(answerDoesNotReimburseTuition);
    criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
    criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));

    boolean matches = profile.matches(criteria);

    assertFalse(matches);
  }

}