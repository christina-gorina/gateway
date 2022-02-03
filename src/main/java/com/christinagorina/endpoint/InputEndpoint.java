package com.christinagorina.endpoint;

import com.christinagorina.countries.Country;
import com.christinagorina.countries.Currency;
import com.christinagorina.countries.GetCountryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
@Slf4j
public class InputEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service"; // может быть любой, главное что бы был указан в dto и настройках

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload Element request) {
        log.info("getCountry qwe request = " + request);
        GetCountryResponse getCountryResponse = new GetCountryResponse();
        Country country = new Country();
        country.setName("FGFHJJGFJHGHGG");
        country.setCapital("rerterterter");
        country.setPopulation(4);
        country.setCurrency(Currency.GBP);
        getCountryResponse.setCountry(country);
        log.info("getCountry qwe getCountryResponse = " + getCountryResponse);
        return getCountryResponse;
    }
}


























