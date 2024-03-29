package com.hyecheon;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProfileTest {
    private Profile profile;
    private BooleanQuestion question;
    private Criteria criteria;

    @Before
    public void setUp() throws Exception {
        profile = new Profile("Bull Hockey, Inc.");
        question = new BooleanQuestion(1, "Got bonuses?");
        criteria = new Criteria();
    }

    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        profile.add(new Answer(question, Bool.FALSE));
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));

        final boolean matches = profile.getMatchSet(criteria).matches();

        assertFalse(matches);
    }

    @Test
    public void matchAnswersTrueForAnyDontCareCriteria() {
        profile.add(new Answer(question, Bool.FALSE));
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));

        final boolean matches = profile.getMatchSet(criteria).matches();

        assertTrue(matches);
    }

    @Test
    public void findsAnswersBasedOnPredicate() {
        profile.add(new Answer(new BooleanQuestion(1, "1"), Bool.FALSE));
        profile.add(new Answer(new PercentileQuestion(2, "2", new String[]{}), 0));
        profile.add(new Answer(new PercentileQuestion(3, "3", new String[]{}), 0));

//        final List<Answer> answers = profile.find(a -> a.getQuestion().getClass() == PercentileQuestion.class);

//        assertThat(ids(answers), equalTo(new int[]{2, 3}));
    }

    private int[] ids(Collection<Answer> answers) {
        return answers.stream().mapToInt(a -> a.getQuestion().getId()).toArray();
    }

    @Test
    public void findAnswers() {
        int dataSize = 5000;
        for (int i = 0; i < dataSize; i++) {
            profile.add(new Answer(new BooleanQuestion(i, String.valueOf(i)), Bool.FALSE));
        }
        profile.add(new Answer(new PercentileQuestion(dataSize, String.valueOf(dataSize), new String[]{}), 0));
        int numberOfTimes = 1000;
//        long elapsedMs = run(numberOfTimes, () -> profile.find(a -> a.getQuestion().getClass() == PercentileQuestion.class));
//        assertTrue(elapsedMs < 1000);
    }

    private long run(int times, Runnable func) {
        final long start = System.nanoTime();
        for (int i = 0; i < times; i++) {
            func.run();
        }
        final long stop = System.nanoTime();
        return (stop - start) / 1000000;
    }
}