package com.hyecheon.tdd.iloveyouboss;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProfileTest {
    private Profile profile;
    private BooleanQuestion questionIsThereRelocation;
    private Answer answerThereIsRelocation;
    private Answer answerTHereIsNotRelocation;

    @Before
    public void createProfile() throws Exception {
        profile = new Profile();
    }

    @Before
    public void createQuestionAndAnswer() throws Exception {
        questionIsThereRelocation = new BooleanQuestion(1, "Relocation package?");
        answerThereIsRelocation = new Answer(questionIsThereRelocation, Bool.TRUE);
        answerTHereIsNotRelocation = new Answer(questionIsThereRelocation, Bool.FALSE);
    }

    @Test
    public void doesNotMatchWhenNoMatchingAnswer() {
        profile.add(answerTHereIsNotRelocation);
        final Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);

        final boolean result = profile.matches(criterion);

        assertFalse(result);
    }

    @Test
    public void matchesNothingWhenProfileEmpty() {
        final Criterion criterion = new Criterion(answerThereIsRelocation, Weight.DontCare);

        boolean result = profile.matches(criterion);

        assertFalse(result);
    }

    @Test
    public void matchesWhenProfileContainsMatchingAnswer() {
        profile.add(answerThereIsRelocation);
        final Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);

        final boolean result = profile.matches(criterion);

        assertTrue(result);
    }

    @Test
    public void matchesWhenContainsMultipleAnswers() {
        profile.add(answerThereIsRelocation);
        profile.add(answerTHereIsNotRelocation);
        final Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);

        final boolean result = profile.matches(criterion);

        assertTrue(result);
    }
}
