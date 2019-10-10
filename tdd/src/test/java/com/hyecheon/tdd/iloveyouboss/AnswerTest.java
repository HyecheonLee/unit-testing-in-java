package com.hyecheon.tdd.iloveyouboss;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnswerTest {
    @Test
    public void matchAgainstNullAnswerReturnsFalse() {
        final BooleanQuestion booleanQuestion = new BooleanQuestion(0, "");
        assertFalse(new Answer(booleanQuestion, Bool.TRUE).match(null));
    }
}