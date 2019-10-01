package com.hyecheon.iloveyouboss.domain;

import com.hyecheon.iloveyouboss.controller.QuestionController;
import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class StartCompilerTest {
    QuestionController controller;

    @Before
    public void setUp() throws Exception {
        controller = new QuestionController();
    }

    @Test
    public void responsesByQuestionAnswersCountsByQuestionText() {
        final StartCompiler stats = new StartCompiler();
        final List<BooleanAnswer> answers = new ArrayList<>();
        answers.add(new BooleanAnswer(1, true));
        answers.add(new BooleanAnswer(1, true));
        answers.add(new BooleanAnswer(1, true));
        answers.add(new BooleanAnswer(1, false));
        answers.add(new BooleanAnswer(2, true));
        answers.add(new BooleanAnswer(2, true));
        Map<Integer, String> questions = new HashMap<>();
        questions.put(1, "Tuition reimbursement?");
        questions.put(2, "Relocation package?");

        final Map<String, Map<Boolean, AtomicInteger>> responses = stats.responsesByQuestion(answers, questions);

        assertThat(responses.get("Tuition reimbursement?").get(Boolean.TRUE).get(), equalTo(3));
        assertThat(responses.get("Tuition reimbursement?").get(Boolean.FALSE).get(), equalTo(1));
        assertThat(responses.get("Relocation package?").get(Boolean.TRUE).get(), equalTo(2));
        assertThat(responses.get("Relocation package?").get(Boolean.FALSE).get(), equalTo(0));
    }

    @Test
    public void questionAnswersDateAdded() {
        final Instant now = new Date().toInstant();
        controller.setClock(Clock.fixed(now, ZoneId.of("America/Denver")));
        int id = controller.addBooleanQuestion("text");
        final Question question = controller.find(id);
        assertThat(question.getCreateTimestamp(), equalTo(now));
    }
}