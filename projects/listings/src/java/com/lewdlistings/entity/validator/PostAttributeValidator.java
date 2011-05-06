package com.lewdlistings.entity.validator;

import com.lewdlistings.entity.PostAttribute;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

public class PostAttributeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PostAttribute.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        PostAttribute attribute = (PostAttribute) obj;

        rejectIfEmptyOrWhitespace(errors, "name", "error.attribute.name.empty", new Object[]{attribute.getName()});
        if (StringUtils.isNotBlank(attribute.getName())) {
            rejectIfEmptyOrWhitespace(errors, "stringValue", "error.attribute.value.empty", new Object[]{attribute.getName()});
        }
        if (!StringUtils.isAlphanumericSpace(attribute.getName())) {
            errors.rejectValue("name", "error.attribute.name.illegal.chars", new Object[]{attribute.getName()}, "Attribute");
        }
    }
}
