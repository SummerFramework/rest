package com.sf.rest.api.app;

import com.sf.rest.api.data.BaseJpaRepositoryImp;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by renan on 23/02/16.
 */

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(repositoryBaseClass = BaseJpaRepositoryImp.class, basePackages={"com.sf.rest.api.app.*"})
@ComponentScan(basePackages={"com.sf.rest.api.app.*"})
@EntityScan(basePackages = "com.sf.rest.api.app")
@EnableTransactionManagement
public class AppConfiguration {

    @Autowired
    private Environment env;

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

//            ComboPooledDataSource ds = new ComboPooledDataSource();

//            if ( env.getProperty("spring.datasource.driver-class-name") != null ){
//                ds.setDriverClass(env.getProperty("spring.datasource.driver-class-name"));
//            }
//
//            ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
//            ds.setUser(env.getProperty("spring.datasource.username"));
//            ds.setPassword(env.getProperty("spring.datasource.password").replace("EMPTY", "").trim());
//
//            ds.setIdleConnectionTestPeriod(60);
//            ds.setTestConnectionOnCheckout(true);
//            ds.setAcquireRetryDelay(200);
//            ds.setAcquireRetryAttempts(5);
//            ds.setCheckoutTimeout(10000);
//
//            ds.setMinPoolSize(Integer.valueOf(env.getProperty("spring.datasource.min-idle", "5")));
//            ds.setMaxPoolSize((Integer.valueOf(env.getProperty("spring.datasource.max-active", "20"))));
//            ds.setInitialPoolSize(Integer.valueOf(env.getProperty("spring.datasource.initial-size", "5")));
//            ds.setPreferredTestQuery(env.getProperty("spring.datasource.validation-query", "SELECT 1"));
//
//            ds.setAcquireIncrement(5);

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
}
