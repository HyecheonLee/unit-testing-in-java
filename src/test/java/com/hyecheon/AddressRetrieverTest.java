package com.hyecheon;

import com.hyecheon.util.Http;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class AddressRetrieverTest {
    @Test
    public void answersAppropriateAddressForValidCoordinates() throws IOException, ParseException {
        Http http = (String url) -> {
            if (!url.contains("lat=38.000000&lon=-104.000000")) {
                fail("url " + url + " does not contains correct params");
            }
            return "{\"address\":{"
                    + "\"house_number\":\"324\","
                    + "\"road\":\"North Tejon Street\","
                    + "\"city\":\"Colorado Springs\","
                    + "\"state\":\"Colorado\","
                    + "\"postcode\":\"80903\","
                    + "\"country_code\":\"us\"}"
                    + "}";
        };
        AddressRetriever retriever = new AddressRetriever(http);

        Address address = retriever.retrieve(38.0, -104.0);

        assertThat(address.houseNumber, equalTo("324"));
        assertThat(address.road, equalTo("North Tejon Street"));
        assertThat(address.city, equalTo("Colorado Springs"));
        assertThat(address.state, equalTo("Colorado"));
        assertThat(address.zip, equalTo("80903"));
    }

}