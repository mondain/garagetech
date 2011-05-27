package com.lewdlistings.web.propertyeditor;


import java.beans.PropertyEditorSupport;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;
import static org.apache.commons.lang.StringEscapeUtils.escapeJavaScript;
import static org.apache.commons.lang.StringEscapeUtils.escapeSql;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class EscapedStringPropertyEditor extends PropertyEditorSupport {

    private boolean escapeHtml;
    private boolean escapeJavaScript;
    private boolean escapeSql;

    public EscapedStringPropertyEditor() {
        super();
    }

    public EscapedStringPropertyEditor(boolean escapeHtml, boolean escapeJavaScript, boolean escapeSql) {
        super();
        this.escapeHtml = escapeHtml;
        this.escapeJavaScript = escapeJavaScript;
        this.escapeSql = escapeSql;
    }

    @Override
    public String getAsText() {
        Object value = getValue();
        return value != null && isNotBlank(value.toString()) ? value.toString() : "";
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (isBlank(text)) {
            setValue(null);
        } else {
            String value = text;
            if (escapeHtml) {
                value = escapeHtml(value);
            }
            if (escapeJavaScript) {
                value = escapeJavaScript(value);
            }
            if (escapeSql) {
                value = escapeSql(value);
            }
            setValue(value);
        }
    }
}
