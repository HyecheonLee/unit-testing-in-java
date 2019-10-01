package com.hyecheon;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ScoreCollectionTest {
    @Test
    public void answersArithmeticMeanOfTwoNumbers() {
        //준비
        final ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);

        //실행
        final int actualResult = collection.arithmeticMean();

        //단언
        assertThat(actualResult, equalTo(6));
    }
}