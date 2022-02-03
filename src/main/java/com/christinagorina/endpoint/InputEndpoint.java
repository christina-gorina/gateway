package com.christinagorina.endpoint;

import com.christinagorina.config.CountryRepository;
import com.christinagorina.countries.Country;
import com.christinagorina.countries.Currency;
import com.christinagorina.countries.GetCountryRequest;
import com.christinagorina.countries.GetCountryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/*
@Endpoint
@RequiredArgsConstructor
@Slf4j
public class InputEndpoint {

    @PayloadRoot(localPart = "MainInfo")
    @ResponsePayload
    @RequestMapping("/input")
    public Answer inputInformation(@RequestPayload Element element) {
        log.info("inputInformation");
        log.info("inputInformation element = " + element);
        Answer answer = new Answer();
        answer.setAnswer("It is answer");
        return answer;
    }
}
*/
@Endpoint
@Slf4j
public class InputEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private CountryRepository countryRepository;

    @Autowired
    public InputEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    //public GetCountryResponse getCountry(@RequestPayload Element request) {
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        log.info("getCountry qwe request = " + request.getName());
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


























