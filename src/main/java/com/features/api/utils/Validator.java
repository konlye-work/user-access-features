package com.features.api.utils;

import org.apache.commons.validator.routines.EmailValidator;

public interface Validator {

    EmailValidator validator = EmailValidator.getInstance();

    static boolean validEmail(String email){
        return validator.isValid(email);
    }
}
