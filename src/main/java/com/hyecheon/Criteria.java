package com.hyecheon;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Criteria implements Iterable<Criterion> {
    @Override
    public Iterator<Criterion> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Criterion> consumer) {

    }

    @Override
    public Spliterator<Criterion> spliterator() {
        return null;
    }
}
