package com.example.checkiiing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/auth").setViewName("auth");
        registry.addViewController("/admin/statistics").setViewName("statistics");
        registry.addViewController("/admin/logout").setViewName("logout");
        registry.addViewController("/add_test").setViewName("add_test");
        registry.addViewController("/add_questions").setViewName("add_questions");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
    }
}
