package com.hyecheon.iloveyouboss.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Profile {

    private Map<String, Answer> answers = new HashMap<>();

    private int score;
    private String name;

    public Profile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add(Answer answer) {
        answers.put(answer.getQuestionText(), answer);
    }

    public boolean matches(Criteria criteria) {
        final MatchSet matchSet = new MatchSet(answers, criteria);
        score = matchSet.getScore();
        return matchSet.matches();
    }

    public int score() {
        return score;
    }

    public List<Answer> classicFind(Predicate<Answer> pred) {
        List<Answer> results = new ArrayList<>();
        for (Answer answer : answers.values())
            if (pred.test(answer))
                results.add(answer);
        return results;
    }

    @Override
    public String toString() {
        return name;
    }

    public List<Answer> find(Predicate<Answer> pred) {
        return answers.values().stream()
                .filter(pred)
                .collect(Collectors.toList());
    }
}
