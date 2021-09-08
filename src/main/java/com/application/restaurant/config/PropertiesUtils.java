package com.application.restaurant.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.Serializable;

@Component
public class PropertiesUtils extends PropertyPlaceholderConfigurer implements Serializable {

    private static CustomProperties customProps;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException {
        customProps = new CustomProperties(getDataSource());
        setProperties(customProps);
        super.postProcessBeanFactory(beanFactory);
    }


    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/restaurant");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("aa123123");
        return dataSourceBuilder.build();
    }


    public static String getProperty(String name) {
        return (null == customProps.get(name)) ? "" : customProps.get(name).toString();
    }
}
