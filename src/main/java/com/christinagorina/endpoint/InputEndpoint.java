package com.christinagorina.endpoint;

import com.christina.gorina.xmlModels.*;
import com.christinagorina.config.ValidationHelper;
import com.christinagorina.config.ValidationProperties;
import com.christinagorina.model.Answer;
import com.christinagorina.model.Requisition;
import com.christinagorina.service.GatewayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;
import org.springframework.xml.transform.TransformerHelper;
import org.springframework.xml.validation.XmlValidator;
import org.w3c.dom.Element;
import org.xml.sax.SAXParseException;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

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
    private final TransformerHelper transformerHelper = new TransformerHelper();
    private final com.christinagorina.service.GatewayService gatewayService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "inputInfoRequest")
    @ResponsePayload
    public InputInfoResponse getConstructor(@RequestPayload Element element) throws TransformerException, IOException, SAXParseException, JAXBException, ExecutionException, InterruptedException {
        if (validationProperties.isEnabled()) {
            StringSource source = addNamespace(element);
            validationHelper.externalValidation(source, validatorExternal);

        }
        InputInfoRequest inputInfoRequest = JAXB.unmarshal(new DOMSource(element), InputInfoRequest.class);
        log.info("inputInfoRequest = " + inputInfoRequest);
        StringResult result = new StringResult();
        transformerHelper.transform(new JAXBSource(JAXBContext.newInstance(InputInfoRequest.class), inputInfoRequest), result);
        Constructor constructor = inputInfoRequest.getConstructor();
        Requisition requisition = Requisition.builder()
                .name(constructor.getName())
                .income(constructor.getIncome())
                .isConstant(constructor.isIsConstant())
                .currency(constructor.getCurrency().toString())
                .build();

        Answer answer = gatewayService.gatewayInput(requisition);
        log.info("answer = " + answer);

        InputInfoResponse inputInfoResponse = new InputInfoResponse();
        ConstructorAnswer constructorAnswer = new ConstructorAnswer();
        constructorAnswer.setName(answer.getName());
        constructorAnswer.setCheckResult(answer.getCheckResult());
        constructorAnswer.setInputInfo(result.toString());

        inputInfoResponse.setConstructorAnswer(constructorAnswer);
        log.info("inputInfoResponse = " + inputInfoResponse);
        return inputInfoResponse;
    }
}


























