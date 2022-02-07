package com.christinagorina.endpoint;

import com.christina.gorina.xmlModels.Constructor;
import com.christina.gorina.xmlModels.Currency;
import com.christina.gorina.xmlModels.InputInfoResponse;
import com.christinagorina.config.ValidationHelper;
import com.christinagorina.config.ValidationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.xml.transform.StringSource;
import org.springframework.xml.validation.XmlValidator;
import org.w3c.dom.Element;
import org.xml.sax.SAXParseException;

import javax.xml.transform.TransformerException;

import java.io.IOException;

import static com.christinagorina.utils.NamespaceUtils.addNamespace;

@Endpoint
@RequiredArgsConstructor
@EnableConfigurationProperties(ValidationProperties.class)
@Slf4j
public class InputEndpoint {
    private static final String NAMESPACE_URI = "http://christina.gorina.com";

    private final ValidationProperties validationProperties;
    private final ValidationHelper validationHelper;
    private final XmlValidator validatorExternal;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "inputInfoRequest")
    @ResponsePayload
    public InputInfoResponse getConstructor(@RequestPayload Element element) throws TransformerException, IOException, SAXParseException {
        log.info("getConstructor element = " + element);
        if(validationProperties.isEnabled()){
            log.info("validationProperties.isEnabled");
            StringSource source = addNamespace(element);
            log.info("source = " + source);
            validationHelper.externalValidation(source, validatorExternal);

        }

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


























