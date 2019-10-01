package com.hyecheon;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProfileTest {
    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        final Profile profile = new Profile("Bull Hockey, Inc.");
        final Question question = new BooleanQuestion(1, "Got bonuses?");
        final Answer profileAnswer = new Answer(question, Bool.FALSE);
        profile.add(profileAnswer);
        final Criteria criteria = new Criteria();
        final Answer criteriaAnswer = new Answer(question, Bool.TRUE);
        final Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
        criteria.add(criterion);
        final boolean matches = profile.matches(criteria);
        assertFalse(matches);
    }

    @Test
    public void matchAnswersTrueForAnyDontCareCriteria() {
        final Profile profile = new Profile("Bull Hockey, Inc.");
        final Question question = new BooleanQuestion(1, "Got bonuses?");
        final Answer profileAnswer = new Answer(question, Bool.FALSE);
        profile.add(profileAnswer);
        final Criteria criteria = new Criteria();
        final Answer criteriaAnswer = new Answer(question, Bool.TRUE);
        final Criterion criterion = new Criterion(criteriaAnswer, Weight.DontCare);
        criteria.add(criterion);
        final boolean matches = profile.matches(criteria);
        assertFalse(matches);
    }
}