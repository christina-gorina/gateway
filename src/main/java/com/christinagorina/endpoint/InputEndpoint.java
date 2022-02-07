package com.christinagorina.endpoint;

import com.christina.gorina.xmlModels.Constructor;
import com.christina.gorina.xmlModels.Currency;
import com.christina.gorina.xmlModels.InputInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
@Slf4j
public class InputEndpoint {
    private static final String NAMESPACE_URI = "http://christina.gorina.com";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "inputInfoRequest")
    @ResponsePayload
    public InputInfoResponse getConstructor(@RequestPayload Element request) {
        log.info("getConstructor request = " + request);
        InputInfoResponse inputInfoResponse = new InputInfoResponse();
        Constructor constructor = new Constructor();
        constructor.setName("Stiv");
        constructor.setIncome(1000000);
        constructor.setCurrency(Currency.GBP);
        constructor.setIsConstant(true);
        inputInfoResponse.setConstructor(constructor);
        log.info("inputInfoResponse = " + inputInfoResponse);
        return inputInfoResponse;
    }
}


























