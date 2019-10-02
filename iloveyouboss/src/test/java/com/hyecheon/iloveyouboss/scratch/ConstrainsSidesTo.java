package com.hyecheon.iloveyouboss.scratch;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ConstrainsSidesTo extends TypeSafeMatcher<Rectangle> {
    private int length;

    public ConstrainsSidesTo(int length) {
        this.length = length;
    }

    @Override
    protected boolean matchesSafely(Rectangle item) {
        return Math.abs(item.origin().x - item.opposite().x) <= length &&
                Math.abs(item.origin().y - item.opposite().y) <= length;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("both side must be <=" + length);
    }

    @Factory
    public static <T> Matcher<Rectangle> constrainsSidesTo(int length) {
        return new ConstrainsSidesTo(length);
    }
}
