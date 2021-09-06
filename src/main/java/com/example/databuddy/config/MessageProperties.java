package com.example.databuddy.config;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Locale;

@Component
public class MessageProperties implements MessageSourceAware {

    private MessageSource messageSource;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key, Object... params) {
        if (messageSource != null) {
            Locale currentLocale = Locale.getDefault();
            return messageSource.getMessage(key, params, currentLocale);
        }
        return null;
    }
}
