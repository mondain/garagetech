package com.lewdlistings.web.post;

import com.lewdlistings.entity.PostAttribute;
import com.lewdlistings.entity.PostLink;
import com.lewdlistings.entity.validator.PhoneNumberValidator;
import com.lewdlistings.entity.validator.PostAttributeValidator;
import com.lewdlistings.entity.validator.PostLinkValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

public class PostFormValidator implements Validator {

    private final PhoneNumberValidator phoneNumberValidator;
    private final PostAttributeValidator postAttributeValidator;
    private final PostLinkValidator postLinkValidator;

    public PostFormValidator() {
        phoneNumberValidator = new PhoneNumberValidator();
        postAttributeValidator = new PostAttributeValidator();
        postLinkValidator = new PostLinkValidator();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PostForm.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        PostForm form = (PostForm) obj;

        rejectIfEmptyOrWhitespace(errors, "location", "error.post.location.empty");
        rejectIfEmptyOrWhitespace(errors, "summary", "error.post.summary.empty");
        rejectIfEmptyOrWhitespace(errors, "content", "error.post.content.empty");

        if (StringUtils.isNotBlank(form.getSummary())) {
            if (form.getSummary().length() > 200) {
                errors.rejectValue("summary", "error.post.summary.too.long");
            }
        }

        if (StringUtils.isNotBlank(form.getContent())) {
            if (form.getContent().length() > 2000) {
                errors.rejectValue("content", "error.post.content.too.long");
            }
        }

        validatePhone(errors, form);
        validateAttributes(errors, form);
        validateLinks(errors, form);
    }

    private void validatePhone(Errors errors, PostForm form) {
        try {
            errors.pushNestedPath("phone");
            ValidationUtils.invokeValidator(phoneNumberValidator, form.getPhone(), errors);
        } finally {
            errors.popNestedPath();
        }
    }

    private void validateAttributes(Errors errors, PostForm form) {
        int index = 0;
        for (PostAttribute attribute : form.getAttributes()) {
            try {
                errors.pushNestedPath("attributes[" + index++ + "]");
                ValidationUtils.invokeValidator(postAttributeValidator, attribute, errors);
            } finally {
                errors.popNestedPath();
            }
        }
    }

    private void validateLinks(Errors errors, PostForm form) {
        int index = 0;
        for (PostLink link : form.getLinks()) {
            try {
                errors.pushNestedPath("links[" + index++ + "]");
                ValidationUtils.invokeValidator(postLinkValidator, link, errors);
            } finally {
                errors.popNestedPath();
            }
        }
    }
}
