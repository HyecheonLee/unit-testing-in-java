package com.hyecheon;

import java.util.stream.StreamSupport;

public class MatchSet {
    private AnswerCollection answers;
    private Criteria criteria;

    public MatchSet(AnswerCollection answers, Criteria criteria) {
        this.answers = answers;
        this.criteria = criteria;
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
                        !criterion.matches(answers.answerMatching(criterion)) &&
                                criterion.getWeight() == Weight.MustMatch);
    }

    private boolean anyMatches(Criteria criteria) {
        boolean anyMatches = false;
        for (Criterion criterion : criteria) {
            anyMatches |= criterion.matches(answers.answerMatching(criterion));
        }
        return anyMatches;
    }


    public int getScore() {
        return StreamSupport.stream(criteria.spliterator(), false)
                .filter(criterion -> criterion.matches(answers.answerMatching(criterion)))
                .mapToInt(criterion -> criterion.getWeight().getValue()).sum();
    }
}
