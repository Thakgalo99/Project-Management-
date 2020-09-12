package com.projectmanagement.api;

import javafx.application.Application;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContext implements ApplicationContextAware {
    private static ApplicationContext Context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.Context = applicationContext;
    }

    public static Object getBean(String beanName){
        return Context.getBean(beanName);
    }
}
