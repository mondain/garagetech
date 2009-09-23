/*
 * Copyright (c) 2009 Carl Sziebert
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * $Id$
 */
package net.sziebert.tutorials.web;

import net.sziebert.tutorials.entity.User;
import net.sziebert.tutorials.security.BCrypt;
import static org.apache.commons.lang.StringUtils.isAlphanumeric;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;
import org.springframework.validation.Validator;

public class ChangePasswordFormValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(ChangePasswordFormValidator.class);

    private final User user;

    public ChangePasswordFormValidator(User user) {
        this.user = user;
    }

    public boolean supports(Class clazz) {
        return ChangePasswordForm.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
        logger.debug("Validating change password form.");
        ChangePasswordForm form = (ChangePasswordForm) obj;
        // Insure that a value with specified.
        rejectIfEmptyOrWhitespace(errors, "original", "error.original.password.empty");
        rejectIfEmptyOrWhitespace(errors, "password", "error.password.empty");
        rejectIfEmptyOrWhitespace(errors, "confirm", "error.confirm.empty");
        // Insure the inputs don't contain any illegal characters.
        if (!isAlphanumeric(form.getPassword()))
            errors.rejectValue("password", "error.password.illegal.chars");
        // Insure that the entries are within the valid length range.
        if (isNotBlank(form.getPassword()) && form.getPassword().length() < 6)
            errors.rejectValue("password", "error.password.too.short");
        // Insure the password and confirmation match.
        if (isNotBlank(form.getPassword()) && isNotBlank(form.getConfirm())) {
            if (!form.getPassword().equals(form.getConfirm())) {
                errors.rejectValue("password", "error.password.mismatch");
            }
        }
        // Insure the specified original password actually matches the performer's current password.
        if (isNotBlank(form.getPassword())) {
            if (!BCrypt.checkpw(form.getOriginal(), user.getPassword())) {
                errors.rejectValue("original", "error.original.password.mismatch");
            }
        }
    }
}