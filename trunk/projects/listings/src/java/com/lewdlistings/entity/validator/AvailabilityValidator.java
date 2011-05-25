package com.lewdlistings.entity.validator;

import com.lewdlistings.entity.Availability;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AvailabilityValidator implements Validator {

    private boolean required;

    public AvailabilityValidator(boolean required) {
        this.required = required;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Availability.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Availability availability = (Availability) obj;
        if (availability != null) {
            try {
                errors.pushNestedPath("location");
                ValidationUtils.invokeValidator(new LocationValidator(required), availability.getLocation(), errors);
            } finally {
                errors.popNestedPath();
            }
            if (availability.getStart() != null && availability.getEnd() != null) {
                if (availability.getStart().isAfter(availability.getEnd())) {
                    errors.rejectValue("start", "error.currentAvailability.invalid.dates");
                }
            }
        }
    }
}
