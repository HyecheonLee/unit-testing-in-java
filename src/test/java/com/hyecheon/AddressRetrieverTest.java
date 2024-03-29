package com.hyecheon;

import com.hyecheon.util.Http;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressRetrieverTest {
    @Mock
    private Http http;
    @InjectMocks
    private AddressRetriever retriever;

    @Before
    public void setUp() throws Exception {
        retriever = new AddressRetriever();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void answersAppropriateAddressForValidCoordinates() throws IOException, ParseException {
        when(http.get(contains("lat=38.000000&lon=-104.000000"))).thenReturn("{\"address\":{"
                + "\"house_number\":\"324\","
                + "\"road\":\"North Tejon Street\","
                + "\"city\":\"Colorado Springs\","
                + "\"state\":\"Colorado\","
                + "\"postcode\":\"80903\","
                + "\"country_code\":\"us\"}"
                + "}");
        Address address = retriever.retrieve(38.0, -104.0);

        assertThat(address.houseNumber, equalTo("324"));
        assertThat(address.road, equalTo("North Tejon Street"));
        assertThat(address.city, equalTo("Colorado Springs"));
        assertThat(address.state, equalTo("Colorado"));
        assertThat(address.zip, equalTo("80903"));
    }

}