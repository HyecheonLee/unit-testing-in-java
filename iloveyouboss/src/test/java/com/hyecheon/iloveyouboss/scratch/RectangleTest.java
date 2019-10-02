package com.hyecheon.iloveyouboss.scratch;

import org.junit.After;
import org.hamcrest.CoreMatchers.*;
import org.junit.Test;

import static com.hyecheon.iloveyouboss.scratch.ConstrainsSidesTo.constrainsSidesTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class RectangleTest {
    private Rectangle rectangle;

//    @After
    public void ensureInvariant() throws Exception {
        assertThat(rectangle, constrainsSidesTo(100));
    }

    @Test
    public void answersArea() {
        final Rectangle rectangle = new Rectangle(new Point(5, 5), new Point(15, 10));
        assertThat(rectangle.area(), equalTo(50));
    }

    @Test
    public void allowsDynamicallyChangingSize() {
        final Rectangle rectangle = new Rectangle(new Point(5, 5));
        rectangle.setOppositeCorner(new Point(130, 130));
        assertThat(rectangle.area(), equalTo(15625));
    }
}