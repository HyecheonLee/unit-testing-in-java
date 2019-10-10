package com.hyecheon;

import com.hyecheon.util.Http;
import com.hyecheon.util.HttpImpl;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class AddressRetriever {
    private Http http = new HttpImpl();

    public AddressRetriever() {
    }

    public Address retrieve(double latitude, double longitude) throws ParseException, IOException {
        final String params = String.format("lat=%.6f&lon=%.6f", latitude, longitude);
        final String response = http.get("http://open.mapquestapi.com/nominatim/v1/reverse?format=json&" + params);
        final JSONObject obj = (JSONObject) new JSONParser().parse(response);
        final JSONObject address = (JSONObject) obj.get("address");
        final String country = (String) address.get("country_code");
        if (!country.equals("us")) {
            throw new UnsupportedOperationException("cannot support non-US addresses at this time");
        }

        final String houseNumber = (String) address.get("house_number");
        final String road = (String) address.get("road");
        final String city = (String) address.get("city");
        final String state = (String) address.get("state");
        final String zip = (String) address.get("postcode");
        return new Address(houseNumber, road, city, state, zip);
    }
}
