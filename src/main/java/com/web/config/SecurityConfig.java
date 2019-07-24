package com.web.config;

import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests().antMatchers("/login").permitAll();
        security.authorizeRequests().antMatchers("/join").permitAll();
        security.authorizeRequests().antMatchers("/keyword/**").authenticated();
        security.authorizeRequests().antMatchers("/book/**").authenticated();

        security.csrf().disable();
        security.formLogin().loginPage("/login").defaultSuccessUrl("/loginSuccess", true);
        security.exceptionHandling().accessDeniedPage("/accessDenied");

        security.userDetailsService(userService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
