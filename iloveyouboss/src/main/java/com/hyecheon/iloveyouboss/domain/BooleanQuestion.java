package com.hyecheon.iloveyouboss.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Arrays;
import java.util.List;

@Entity
@NoArgsConstructor
public class BooleanQuestion extends Question {
    private static final long serialVersionUID = 1L;

    public BooleanQuestion(String text) {
        super(text);
    }

    @Override
    public List<String> getAnswerChoices() {
        return Arrays.asList("No", "Yes");
    }

    @Override
    public boolean match(int expected, int actual) {
        return expected == actual;
    }
}
