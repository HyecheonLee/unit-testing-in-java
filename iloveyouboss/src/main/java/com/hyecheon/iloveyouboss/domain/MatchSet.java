package com.hyecheon.iloveyouboss.domain;

import java.util.Map;
import java.util.stream.StreamSupport;

public class MatchSet {
    private Map<String, Answer> answers;
    private Criteria criteria;
    private int score = 0;

    public MatchSet(Map<String, Answer> answers, Criteria criteria) {
        this.answers = answers;
        this.criteria = criteria;
        calculateScore(criteria);
    }

    public boolean matches() {
        if (desNotMeetAnyMustMatchCriterion(criteria)) {
            return false;
        }
        return anyMatches(criteria);
    }

    private boolean desNotMeetAnyMustMatchCriterion(Criteria criteria) {
        return StreamSupport.stream(criteria.spliterator(), false)
                .anyMatch(criterion ->
                        !criterion.matches(answerMatching(criterion)) &&
                                criterion.getWeight() == Weight.MustMatch);
    }

    private boolean anyMatches(Criteria criteria) {
        boolean anyMatches = false;
        for (Criterion criterion : criteria) {
            anyMatches |= criterion.matches(answerMatching(criterion));
        }
        return anyMatches;
    }

    private void calculateScore(Criteria criteria) {
        score = StreamSupport.stream(criteria.spliterator(), false)
                .filter(criterion -> criterion.matches(answerMatching(criterion)))
                .mapToInt(criterion -> criterion.getWeight().getValue()).sum();
    }

    private Answer answerMatching(Criterion criterion) {
        return answers.get(criterion.getAnswer().getQuestionText());
    }

    public int getScore() {
        return score;
    }
}
