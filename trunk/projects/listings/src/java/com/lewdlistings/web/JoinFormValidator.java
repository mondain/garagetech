package com.lewdlistings.web;

import com.lewdlistings.validation.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.apache.commons.lang.StringUtils.isAlphanumeric;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

public class JoinFormValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(JoinFormValidator.class);

    private EmailValidator emailValidator = new EmailValidator();

    public boolean supports(Class clazz) {
        return JoinForm.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
        logger.debug("Validating join form.");
        JoinForm form = (JoinForm) obj;
        // Insure that a value with specified.
        rejectIfEmptyOrWhitespace(errors, "username", "error.username.empty");
        rejectIfEmptyOrWhitespace(errors, "email", "error.email.empty");
        rejectIfEmptyOrWhitespace(errors, "password", "error.password.empty");
        // Insure the inputs don't contain any illegal characters.
        if (isNotBlank(form.getUsername()) && !isAlphanumeric(form.getUsername())) {
            errors.rejectValue("username", "error.username.illegal.chars");
        }
        // Insure that the entries are within the valid length range.
        if (isNotBlank(form.getUsername()) && form.getUsername().length() < 4) {
            errors.rejectValue("username", "error.username.too.short");
        }
        if (isNotBlank(form.getPassword()) && form.getPassword().length() < 6) {
            errors.rejectValue("password", "error.password.too.short");
        }
        // Insure the email address is valid.
        if (isNotBlank(form.getEmail())) {
            if (!emailValidator.validate(form.getEmail())) {
                errors.rejectValue("email", "error.email.invalid");
            }
        }
        // Insure that the terms of use have been accepted.
        if (!form.getAgreeToTerms()) {
            errors.rejectValue("agreeToTerms", "error.agree.to.terms");
        }
    }
}