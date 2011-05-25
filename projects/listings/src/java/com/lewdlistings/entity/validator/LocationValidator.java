package com.lewdlistings.entity.validator;

import com.lewdlistings.entity.Location;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.isNumeric;
import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

public class LocationValidator implements Validator {

    private boolean required;

    public LocationValidator(boolean required) {
        this.required = required;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Location.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Location location = (Location) obj;
        if (required) {
            rejectIfEmptyOrWhitespace(errors, "zipCode", "error.location.zipcode.empty");
        }
        if (location != null && isNotBlank(location.getZipCode()) && !isNumeric(location.getZipCode())) {
            errors.rejectValue("zipCode", "error.location.zipcode.illegal.chars");
        }
    }
}
