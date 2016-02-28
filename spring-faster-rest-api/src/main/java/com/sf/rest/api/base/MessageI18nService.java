package com.sf.rest.api.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by renan on 28/02/16.
 */
@Component
public class MessageI18nService {

    @Autowired
    MessageSource messageSource;

    public String get(String key){
        return this.messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }
}
