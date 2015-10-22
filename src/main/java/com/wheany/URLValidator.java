package com.wheany;

import com.vaadin.data.validator.AbstractStringValidator;

import java.net.MalformedURLException;
import java.net.URL;

public class URLValidator extends AbstractStringValidator {

    public URLValidator() {
        super("Invalid URL: '{0}'");
    }

    @Override
    protected boolean isValidValue(String value) {
        try {
            new URL(value);
        } catch (MalformedURLException e) {
            return false;
        }
        return true;
    }
}
