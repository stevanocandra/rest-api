package com.inventories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.Filter;

@ComponentScan(basePackages = "com.inventories")
@SpringBootApplication
public class InventoriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoriesApplication.class, args);
    }

    @Bean
    public Filter shallowEtagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }

    @Bean
    public FilterRegistrationBean shallowEtagHeaderFilterRegistration() {
        FilterRegistrationBean result = new FilterRegistrationBean();
        result.setFilter(this.shallowEtagHeaderFilter());
        result.addUrlPatterns("/api/*");
        result.setName("shallowEtagHeaderFilter");
        result.setOrder(1);
        return result;
    }
}
