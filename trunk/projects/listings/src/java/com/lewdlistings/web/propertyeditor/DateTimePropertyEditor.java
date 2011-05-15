package com.lewdlistings.web.propertyeditor;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.beans.PropertyEditorSupport;

public class DateTimePropertyEditor extends PropertyEditorSupport {

    private final String pattern;
    private final DateTimeZone timeZone;
    private final boolean allowEmpty;

    public DateTimePropertyEditor(String pattern, DateTimeZone dateTimeZone, boolean allowEmpty) {
        this.pattern = pattern;
        this.timeZone = dateTimeZone;
        this.allowEmpty = allowEmpty;
    }

    @Override
    public String getAsText() {
        if (getValue() == null) return "";

        DateTime value = (DateTime) getValue();
        return value != null ?
                value.withZone(timeZone).toString(pattern) : "";
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isBlank(text)) {
            if (allowEmpty) {
                setValue(null);
            } else {
                throw new IllegalArgumentException(
                        "The specified DateTime string cannot be null");
            }
        } else {
            setValue(DateTimeFormat.forPattern(pattern).withZone(timeZone)
                    .parseDateTime(text));
        }
    }

}