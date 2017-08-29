package com.company.app;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ProfileMatcherTest {
  private BooleanQuestion question;
  private Criteria criteria;
  private ProfileMatcher matcher;
  private Profile matchingProfile;
  private Profile nonMatchingProfile;

  @Before
  public void create() {
    System.out.println("@Before: create()");
    question = new BooleanQuestion(1, "");
    criteria = new Criteria();
    criteria.add(new Criterion(matchingAnswer(), Weight.MustMatch));
    matchingProfile = createMatchingProfile("matching");
    nonMatchingProfile = createNonMatchingProfile("nonMatching");
  }

  @Before
  public void createMatcher() {
    System.out.println("@Before createMatcher()");
    matcher = new ProfileMatcher();
  }

  private Profile createNonMatchingProfile(String name) {
    Profile profile = new Profile(name);
    profile.add(nonMatchingAnswer());
    return profile;
  }

  private Profile createMatchingProfile(String name) {
    Profile profile = new Profile(name);
    profile.add(matchingAnswer());
    return profile;
  }

  private Answer matchingAnswer() {
    return new Answer(question, Bool.TRUE);
  }

  private Answer nonMatchingAnswer() {
    return new Answer(question, Bool.FALSE);
  }

  @Test
  public void collectsMatchSets() {
    matcher.add(matchingProfile);
    matcher.add(nonMatchingProfile);

    final List<MatchSet> sets = matcher.collectMatchSets(criteria);
    sets.forEach(s -> {
      System.out.println("MatchSet.matches()=" + s.matches());
    });

    final Set<String> actual = sets.stream()
        .map(set -> set.getProfileId())
        .collect(Collectors.toSet());
    final Matcher<Set<String>> matchers = equalTo(new HashSet<>(Arrays.asList(matchingProfile.getId(), nonMatchingProfile.getId())));
    System.out.println("matchers: " + matchers);
    assertThat(actual, matchers);
  }
/*
@Before createMatchListener()
@Before createMatcher()
@Before: create()
MatchSet.matches()=false
MatchSet.matches()=true
matchers: <[nonMatching, matching]>
 */

  private MatchListener listener;

  @Before
  public void createMatchListener() {
    System.out.println("@Before createMatchListener()");
    // 1. use Mockito.mock() to create a MatchListener mock instance.
    listener = mock(MatchListener.class);
  }

  @Test
  public void processNotifiesListenerOnMatch() {
    // 2. add a matching profile to the matcher
    matcher.add(matchingProfile);
    // 3. get matched criteria MatchSet
    final MatchSet set = matchingProfile.getMatchSet(criteria);
    System.out.println("MatchSet.matches()=" + set.matches());

    // 4. run matcher.process(), passing in the mock listener and the match set.
    matcher.process(listener, set);

    // 5. verify the MatchListener.foundMatch() was called with the matching arguments
    verify(listener, times(1)).foundMatch(matchingProfile, set);
  }
/*
@Before createMatchListener()
@Before createMatcher()
@Before: create()
MatchSet.matches()=true
 */

  @Test
  public void processDoesNotNotifyListenerWhenNoMatch() {
    matcher.add(nonMatchingProfile);
    final MatchSet set = nonMatchingProfile.getMatchSet(criteria);
    matcher.process(listener, set);
    verify(listener, never()).foundMatch(nonMatchingProfile, set);
  }

  @Test
  public void gathersMatchingProfiles() {
    // TODO
  }

}
