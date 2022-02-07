package com.christinagorina.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXParseException;

@Slf4j
@Component
public class ValidationErrorHandler {

    private static final String ERROR_MESSAGE = "cvc-complex-type.2.4.a: Invalid content was starting with element";

    public void checkErrors(SAXParseException[] exceptions) throws SAXParseException {
        for(SAXParseException exception : exceptions){
            String message = exception.getMessage();
            if(!message.startsWith(ERROR_MESSAGE)){
                throw exception;
            }
            log.warn("New tag {}", message.substring(ERROR_MESSAGE.length() + 1, message.indexOf("}'") + 1));
        }
    }

}
