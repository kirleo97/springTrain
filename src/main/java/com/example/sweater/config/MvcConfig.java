package com.example.sweater.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// этот класс содержит конфигурацию нашего веб-слоя
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
    // Метод addViewControllers() (переопределение метода с таким же названием в WebMvcConfigurerAdapter), добавляющий контроллер представления с именеи login

}