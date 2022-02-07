package com.christinagorina.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.xml.transform.StringSource;
import org.springframework.xml.validation.XmlValidator;
import org.xml.sax.SAXParseException;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidationHelper {

    private final ValidationErrorHandler handler;

    public void externalValidation(StringSource source, XmlValidator validator) throws IOException, SAXParseException {
        SAXParseException[] exceptions = validator.validate(source);
        handler.checkErrors(exceptions);

    }

}
