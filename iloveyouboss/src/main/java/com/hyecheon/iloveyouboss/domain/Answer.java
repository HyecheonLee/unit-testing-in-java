package com.hyecheon.iloveyouboss.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {
    private Question question;
    private int i;

    public Answer(Question question, String matchingValue) {
        this.question = question;
        this.i = question.indexOf(matchingValue);
    }

    public String getQuestionText() {
        return question.getText();
    }

    public boolean match(int expected) {
        return question.match(expected, i);
    }

    public boolean match(Answer otherAnswer) {
        return question.match(i, otherAnswer.i);
    }

}
