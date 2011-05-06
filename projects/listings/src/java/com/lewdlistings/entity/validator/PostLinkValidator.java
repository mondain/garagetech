package com.lewdlistings.entity.validator;

import com.lewdlistings.entity.PostLink;
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
        return PostLink.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        PostLink link = (PostLink) obj;

        rejectIfEmptyOrWhitespace(errors, "url", "error.link.url.empty");
        if (isNotBlank(link.getUrl())) {
            /*if (!urlValidator.isValid(link.getUrl())) {
                errors.rejectValue("url", "error.link.url.invalid");
            }*/
        }

        if (isNotBlank(link.getAlias())) {
            if (!isAlphanumericSpace(link.getAlias())) {
                errors.rejectValue("alias", "error.link.alias.illegal.chars", new Object[] { link.getAlias() }, "Url");
            }
        }
    }
}
