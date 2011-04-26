package com.lewdlistings.web.post;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PostFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PostForm.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        PostForm form = (PostForm) obj;
    }
}
