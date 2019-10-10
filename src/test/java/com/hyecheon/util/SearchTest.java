package com.hyecheon.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.logging.Level;

import static com.hyecheon.util.ContainsMatches.containsMatches;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class SearchTest {
    private static final String A_TITLE = "1";
    private InputStream stream;

    @Before
    public void setUp() throws Exception {
        Search.LOGGER.setLevel(Level.OFF);
    }

    @After
    public void tearDown() throws Exception {
        stream.close();
    }

    @Test
    public void testSearch() throws IOException {
        stream = streamOn("There are certain queer times and occasions "
                + "in this strange mixed affair we call life when a man "
                + "takes this whole universe for a vast practical joke, "
                + "though the wit thereof he but dimly discerns, and more "
                + "than suspects that the joke is at nobody's expense but "
                + "his own.");
        // search
        Search search = new Search(stream, "practical joke", A_TITLE);
        Search.LOGGER.setLevel(Level.OFF);
        search.setSurroundingCharacterCount(10);
        search.execute();
        assertFalse(search.errored());
        assertThat(search.getMatches(), containsMatches(new Match[]{
                new Match(A_TITLE, "practical joke", "or a vast practical joke, though t")
        }));
    }

    public void noMatchesReturnedWhenSearchStringNotInContent() throws IOException {
        // negative
        URLConnection connection = new URL("http://bit.ly/15sYPA7").openConnection();
        stream = connection.getInputStream();
        Search search = new Search(stream, "smelt", A_TITLE);
        search.execute();
        assertTrue(search.getMatches().isEmpty());
    }

    private InputStream streamOn(String pageContent) {
        return new ByteArrayInputStream(pageContent.getBytes());
    }
}