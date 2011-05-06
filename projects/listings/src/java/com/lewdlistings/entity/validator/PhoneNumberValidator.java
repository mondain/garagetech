package com.lewdlistings.entity.validator;


import com.lewdlistings.entity.PhoneNumber;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.apache.commons.lang.StringUtils.isNumeric;
import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

public class PhoneNumberValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PhoneNumber.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        PhoneNumber number = (PhoneNumber) obj;

        rejectIfEmptyOrWhitespace(errors, "areaCode", "error.phone.areaCode.empty");
        rejectIfEmptyOrWhitespace(errors, "prefix", "error.phone.prefix.empty");
        rejectIfEmptyOrWhitespace(errors, "suffix", "error.phone.suffix.empty");

        if (!isNumeric(number.getAreaCode())) {
            errors.rejectValue("areaCode", "error.phone.areaCode.illegal.chars");
        }
        if (!isNumeric(number.getPrefix())) {
            errors.rejectValue("prefix", "error.phone.prefix.illegal.chars");
        }
        if (!isNumeric(number.getSuffix())) {
            errors.rejectValue("suffix", "error.phone.suffix.illegal.chars");
        }
    }
}
