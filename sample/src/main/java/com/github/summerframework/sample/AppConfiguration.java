package com.github.summerframework.sample;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import com.github.summerframework.core.data.BaseJpaRepositoryImp;

import javax.sql.DataSource;
import java.util.Locale;

/**
 * Created by renan on 23/02/16.
 */

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(repositoryBaseClass = BaseJpaRepositoryImp.class, basePackages={"org.summerframework.*","com.myapp.*"})
@ComponentScan(basePackages={"com.github.summerframework.*","com.myapp.*"})
@EntityScan(basePackages = {"com.github.summerframework.*","com.myapp.*"})
@EnableTransactionManagement
public class AppConfiguration {

    @Autowired
    private Environment env;

    @Autowired
    private MessageSource messageSource;

    @Bean(name = "dataSource", destroyMethod = "close")
    public DataSource dataSource (){

        try{

            BasicDataSource ds = new BasicDataSource();
            ds.setUsername(env.getProperty("spring.datasource.username") );
            ds.setPassword(env.getProperty("spring.datasource.password").replace("EMPTY", "").trim());
            ds.setUrl(env.getProperty("spring.datasource.url"));
            ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));

            ds.setMaxTotal((Integer.valueOf(env.getProperty("spring.datasource.max-active", "20"))));
            ds.setMinIdle(Integer.valueOf(env.getProperty("spring.datasource.min-idle", "5")));
            ds.setInitialSize(Integer.valueOf(env.getProperty("spring.datasource.initial-size", "5")));
            ds.setValidationQuery(env.getProperty("spring.datasource.validation-query", "SELECT 1"));
            ds.setValidationQueryTimeout(5);
            ds.setDefaultQueryTimeout(5);
            ds.setTestOnBorrow(true);
            return ds;
        }catch ( Exception e ){
            e.printStackTrace();
        }

        return null;
    }

    @Autowired
    public void applicationContext ( ApplicationContext applicationContext ){
        SpringApplicationContext.setApplicationContext(applicationContext);
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
}
