package com.lewdlistings.web;

//import org.apache.commons.validator.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.apache.commons.lang.StringUtils.isAlphanumeric;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

public class JoinFormValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(JoinFormValidator.class);

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
        rejectIfEmptyOrWhitespace(errors, "confirm", "error.confirm.empty");
        // Insure the inputs don't contain any illegal characters.
        if (!isAlphanumeric(form.getUsername()))
            errors.rejectValue("username", "error.username.illegal.chars");
        if (!isAlphanumeric(form.getPassword()))
            errors.rejectValue("password", "error.password.illegal.chars");
        // Insure that the entries are within the valid length range.
        if (isNotBlank(form.getUsername()) && form.getUsername().length() < 4)
            errors.rejectValue("username", "error.username.too.short");
        if (isNotBlank(form.getPassword()) && form.getPassword().length() < 6)
            errors.rejectValue("password", "error.password.too.short");
        // Insure the password and confirmation match.
        if (isNotBlank(form.getPassword()) && isNotBlank(form.getConfirm())) {
            if (!form.getPassword().equals(form.getConfirm())) {
                errors.reject("error.password.mismatch");
            }
        }
        // Insure the email address is valid.
        /*if (isNotBlank(form.getEmail())) {
            EmailValidator ev = EmailValidator.getInstance();
            if (!ev.isValid(form.getEmail())) {
                errors.rejectValue("email", "error.email.invalid");
            }
        }*/
        // Insure that the terms of use have been accepted.
        if (!form.getAgreeToTerms()) {
            errors.rejectValue("agreeToTerms", "error.agree.to.terms");
        }
    }
}