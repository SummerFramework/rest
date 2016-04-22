package com.github.summerframework.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * Created by renan on 23/02/16.
 */
@Configuration
@SpringBootApplication
public class AppApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

}
