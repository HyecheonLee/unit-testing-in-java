package com.hyecheon.tdd.iloveyouboss;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProfileTest {
    private Profile profile;
    private BooleanQuestion questionIsThereRelocation;
    private Answer answerThereIsRelocation;

    @Before
    public void createProfile() throws Exception {
        profile = new Profile();
    }

    @Before
    public void createQuestionAndAnswer() throws Exception {
        questionIsThereRelocation = new BooleanQuestion(1, "Relocation package?");
        answerThereIsRelocation = new Answer(questionIsThereRelocation, Bool.TRUE);
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
}
