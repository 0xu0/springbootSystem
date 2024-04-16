package com.controller;

import com.Interceptor.LoginInterceptor;
import com.Listener.MyListenter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import java.util.Arrays;

@Configuration
public class MyConfig implements WebMvcConfigurer {
    //注册拦截器
    //.addInterceptor注册拦截器
    //addPathPatterns添加路径
    //excludePathPatterns（排除路径）
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).
                addPathPatterns("/**").excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**","/js/**");

    }
    @Bean
    public ServletRegistrationBean ServletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean=
                new ServletRegistrationBean(new servlet(),"/test");
        return servletRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new filter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/demo1"));
        return filterRegistrationBean;
    }
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        ServletListenerRegistrationBean<MyListenter> servletRegistrationBean=
                new ServletListenerRegistrationBean<>(new MyListenter());
        return servletRegistrationBean;
    }
}
