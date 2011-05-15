package com.lewdlistings.entity.validator;

import com.lewdlistings.entity.Availability;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.isNumeric;
import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

public class AvailabilityValidator implements Validator {

    private boolean isPrebook;

    public AvailabilityValidator(boolean isPrebook) {
        this.isPrebook = isPrebook;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Availability.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Availability availability = (Availability) obj;
        if (availability != null) {
            if (!isPrebook) {
                rejectIfEmptyOrWhitespace(errors, "zipCode", "error.currentAvailability.zipCode.empty");
            }
            if (isNotBlank(availability.getZipCode()) && !isNumeric(availability.getZipCode())) {
                errors.rejectValue("zipCode", "error.currentAvailability.zipCode.illegal.chars");
            }
            if (availability.getStart() != null && availability.getEnd() != null) {
                if (availability.getStart().isAfter(availability.getEnd())) {
                    errors.rejectValue("start", "error.currentAvailability.invalid.dates");
                }
            }
        }
    }
}
