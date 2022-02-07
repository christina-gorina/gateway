package com.christinagorina.utils;

import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;
import org.springframework.xml.transform.TransformerHelper;
import org.w3c.dom.Element;

import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;

public final class NamespaceUtils {

    private NamespaceUtils() {throw new IllegalStateException("Utility class");}

    public static final String NAMESPACE_URI = "http://christina.gorina.com";

    public static StringSource addNamespace(Element element) throws TransformerException {
        DOMSource source = new DOMSource(element);

        StringResult stringResult = new StringResult();
        TransformerHelper helper = new TransformerHelper();
        helper.transform(source, stringResult);
        String request = stringResult.toString();
        //request = request.replace()

        return new StringSource(request);
    }


}
