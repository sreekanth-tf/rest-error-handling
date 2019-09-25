package com.tf.errorhandling.config.error.enhancerway;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Component
@ConditionalOnProperty(value = "error.attribute.enhancer", havingValue = "true")
public class PacErrorAttribute extends DefaultErrorAttributes {

    //This probably can be stored and retrieved from DB or properties
    private static final Map<Integer, String> ERROR_MESSAGE_STORE = new HashMap<>();

    static {
        ERROR_MESSAGE_STORE.put(500, "Some server error");
        ERROR_MESSAGE_STORE.put(404, "I can't serve that");
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorData = super.getErrorAttributes(webRequest, includeStackTrace);
        errorData.put("enhanced", true);
        errorData.put("message", ERROR_MESSAGE_STORE.get(errorData.get("status")));
        return errorData;
    }
}
