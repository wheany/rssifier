package com.wheany;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import com.vaadin.data.validator.AbstractStringValidator;

import java.net.MalformedURLException;
import java.net.URL;

public class URLValidator extends AbstractStringValidator {

    public URLValidator() {
        super("Invalid URL: '{0}'");
    }

    @Override
    protected boolean isValidValue(String value) {
        if(Strings.isNullOrEmpty(value)) {
            return true;
        }
        try {
            new URL(value);
        } catch (MalformedURLException e) {
            return false;
        }
        return true;
    }
}
