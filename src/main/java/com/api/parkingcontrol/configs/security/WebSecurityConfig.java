package com.api.parkingcontrol.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/* modelo de autenticação V1*/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    final UserDetailsServiceImpl userDetailsService;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/parking-spot/**").permitAll()
                .antMatchers(HttpMethod.POST, "/parking-spot").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/parking-spot/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable(); /* */
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /* codifica a senha
    *  sem isso da erro 500!
    * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
