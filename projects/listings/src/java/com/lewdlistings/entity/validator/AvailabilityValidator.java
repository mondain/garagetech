package com.lewdlistings.entity.validator;

import com.lewdlistings.entity.Availability;
import com.lewdlistings.entity.Location;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.isNumeric;
import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

public class AvailabilityValidator implements Validator {

    public AvailabilityValidator() {}

    @Override
    public boolean supports(Class<?> clazz) {
        return Availability.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Availability availability = (Availability) obj;
        if (availability != null) {
            validateCurrent(availability, errors);
            validatePrebook(availability, errors);
        }
    }

    private void validateCurrent(Availability availability, Errors errors) {
        try {
            errors.pushNestedPath("location");
            Location location = availability.getLocation();
            rejectIfEmptyOrWhitespace(errors, "zipCode", "error.location.zipcode.empty");
            if (location != null && isNotBlank(location.getZipCode()) && !isNumeric(location.getZipCode())) {
                errors.rejectValue("zipCode", "error.location.zipcode.illegal.chars");
            }
        } finally {
            errors.popNestedPath();
        }
        if (availability.getStart() != null && availability.getEnd() != null) {
            if (availability.getStart().isAfter(availability.getEnd())) {
                errors.rejectValue("start", "error.availability.invalid.dates");
            }
        }
    }

    private void validatePrebook(Availability availability, Errors errors) {
        try {
            errors.pushNestedPath("prebookLocation");
            Location prebookLocation = availability.getPrebookLocation();
            if (prebookLocation != null && isNotBlank(prebookLocation.getZipCode()) && !isNumeric(prebookLocation.getZipCode())) {
                errors.rejectValue("zipCode", "error.prebookLocation.zipcode.illegal.chars");
            }
        } finally {
            errors.popNestedPath();
        }
        if (availability.getPrebookStart() != null && availability.getPrebookEnd() != null) {
            if (availability.getPrebookStart().isAfter(availability.getPrebookEnd())) {
                errors.rejectValue("prebookStart", "error.prebookAvailability.invalid.dates");
            }
            if (availability.getPrebookStart().isBefore(availability.getEnd())) {
                errors.rejectValue("prebookStart", "error.prebookAvailability.invalid.dates");
            }
        }
    }
}
