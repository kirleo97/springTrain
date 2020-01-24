package com.example.sweater.config;

import com.example.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

//Класс WebSecurityConfig содержит аннотацию @EnableWebMvcSecurity для включения поддержки безопасности Spring Security и Spring MVC интеграцию.
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/registration", "/static/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    /*
    Метод configure(HttpSecurity) определяет, какие URL пути должны быть защищены, а какие нет. В частности, "/" и "/registration" настроены без требования к
    авторизации.
    Ко всем остальным путям должна быть произведена аутентификация. Когда пользователь успешно войдет в систему, он будет перенаправлен на предыдущую
    запрашиваемую страницу, требующую авторизацию. Здесь вы определили собственную "/login" страницу в loginPage() и каждый имеет доступ к ней.
    Что касается метода configure(AuthenticationManagerBuilder), то он создает в памяти хранилище пользователей с единственным пользователем.
    Этому пользователю дано имя "u", пароль "p" и роль "ROLE".
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}