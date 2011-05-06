package com.lewdlistings.web.propertyeditor;

import com.lewdlistings.entity.PhoneNumber;

import java.beans.PropertyEditorSupport;

import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class PhoneNumberPropertyEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        Object value = getValue();
        if (value == null)
            return EMPTY;
        return value.toString();
    }

    @Override
    public void setAsText(String string) throws IllegalArgumentException {
        if (isNotBlank(string)) {
            setValue(PhoneNumber.parse(string));
        } else {
            setValue(new PhoneNumber());
        }
    }
}
