package com.github.summerframework.sample;

import org.springframework.context.ApplicationContext;

/**
 * Created by renan on 20/02/16.
 */
public class SpringApplicationContext {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext (ApplicationContext applicationContext){
        SpringApplicationContext.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}