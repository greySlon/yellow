package com.yellow.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/*
 * secured urls like /secured-api/**
 * url to login in: POST /login
 * test params: username=patient, password=patient_password
 * url to log out: GET /logout
 * */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(11);
  }

  @Autowired
  private DataSource dataSource;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/secured-api/**").hasAuthority("USER_ROLE")
        .antMatchers("/**").permitAll()
        .and()
        .formLogin().loginPage("/login").successForwardUrl("/login-successful").permitAll()
        .and()
        .logout().logoutUrl("/logout").logoutSuccessUrl("/logout-success").permitAll()
        .and()
        .rememberMe().alwaysRemember(true).rememberMeCookieName("rme")
        .and()
        .csrf().disable();
  }

  /*@Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .passwordEncoder(passwordEncoder)
        .withUser("patient")
        .password("$2a$11$9o0yeUUZW7f8xjL0Be2uYeAiLNJH1rZHGk.dn8yE/8lMXrshwdOBO")
        .roles("USER_ROLE");
  }*/

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery(
            "select u.login as username, u.password, u.enabled from user u "
                + "where u.login=?")
        .authoritiesByUsernameQuery(
            "select u.login as username, r.name as role from user u "
                + "join user_role ur "
                + "join role r "
                + "on r.id=ur.role_id and u.id=ur.user_id "
                + "where u.login=?");
  }
}
