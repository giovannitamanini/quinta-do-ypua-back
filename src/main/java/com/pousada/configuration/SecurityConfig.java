package com.exemplo.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Desabilita CSRF para simplificar a autenticação via API
                .authorizeRequests()
                .antMatchers("/api/auth/login").permitAll()  // Permite acesso sem autenticação para o login
                .anyRequest().authenticated()  // Requer autenticação para qualquer outra requisição
                .and()
                .httpBasic();
    }
}
