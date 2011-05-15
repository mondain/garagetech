package com.lewdlistings.entity.validator;

import com.lewdlistings.entity.PostAttribute;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.apache.commons.lang.StringUtils.isAlphanumericSpace;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

public class PostLinkValidator implements Validator {

    //private final UrlValidator urlValidator;

    public PostLinkValidator() {
        //urlValidator = new UrlValidator();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PostAttribute.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        PostAttribute link = (PostAttribute) obj;

        rejectIfEmptyOrWhitespace(errors, "stringValue", "error.link.url.empty");
        if (isNotBlank(link.getName())) {
            /*if (!urlValidator.isValid(link.getUrl())) {
                errors.rejectValue("url", "error.link.url.invalid");
            }*/
        }

        if (isNotBlank(link.getName())) {
            if (!isAlphanumericSpace(link.getName())) {
                errors.rejectValue("name", "error.link.alias.illegal.chars", new Object[] { link.getStringValue() }, "Url");
            }
        }
    }
}
