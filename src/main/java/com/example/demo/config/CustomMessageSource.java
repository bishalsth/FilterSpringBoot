package com.example.demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Component
public class CustomMessageSource {
@Autowired
private final MessageSource messageSource;

    public CustomMessageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(5);
        this.messageSource = messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(this.messageSource);
        return bean;
    }

    public String get(String code) {
        return messageSource.getMessage(code, null, Locale.ENGLISH);
    }

    public String get(String code, Object... objects) {
        return messageSource.getMessage(code, objects, Locale.ENGLISH);
    }
}
