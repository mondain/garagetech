package com.lewdlistings.web.propertyeditor;

import java.beans.PropertyEditorSupport;

import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.isBlank;

public class EnumPropertyEditor extends PropertyEditorSupport {

    private Class<? extends Enum> enumType;

    public EnumPropertyEditor(Class<? extends Enum> enumType) {
        this.enumType = enumType;
    }

    @Override
    public String getAsText() {
        Object value = getValue();
        if (value == null)
            return EMPTY;
        return ((Enum) value).name();
    }

    @Override
    public void setAsText(String string) throws IllegalArgumentException {
        if (isBlank(string)) {
            setValue(null);
            return;
        }
        setValue(Enum.valueOf(enumType, string));
    }
}