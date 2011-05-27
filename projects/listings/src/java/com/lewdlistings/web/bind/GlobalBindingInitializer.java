package com.lewdlistings.web.bind;

import com.lewdlistings.web.propertyeditor.EscapedStringPropertyEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class GlobalBindingInitializer implements WebBindingInitializer {

    private static final Logger logger = LoggerFactory.getLogger(GlobalBindingInitializer.class);

    @Override
    public void initBinder(WebDataBinder binder, WebRequest request) {
        logger.debug("Registering global web data binder");
        binder.registerCustomEditor(String.class, new EscapedStringPropertyEditor(true, true, true));
    }
}
