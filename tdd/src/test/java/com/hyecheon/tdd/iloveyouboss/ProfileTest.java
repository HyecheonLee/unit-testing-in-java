package com.hyecheon.tdd.iloveyouboss;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class ProfileTest {
    @Test
    public void matchesNothingWhenProfileEmpty() {
        final Profile profile = new Profile();
        final Question question = new BooleanQuestion(1, "Relocation package?");
        final Criterion criterion = new Criterion(new Answer(question, Bool.TRUE, Weight.DontCare));
        boolean result = profile.matches(criterion);
        assertFalse(result);
    }
}
