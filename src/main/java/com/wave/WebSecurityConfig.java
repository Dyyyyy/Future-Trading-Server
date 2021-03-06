package com.wave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * Created by Json on 2016/12/9.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    DataSource dataSource;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/test","/user/register","/test/*","/contract/item/*", "/contract/**", "/user/*","/news/**", "/trade_info").permitAll()

                .anyRequest().authenticated()

                .and().formLogin()
                .loginPage("/user/login")
                .failureForwardUrl("/user/login_fail")
                .successForwardUrl("/user/login_success")
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll()

                .and().logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/user/logout_success")
                .permitAll()
                .and().csrf().disable()

                .rememberMe()
                .alwaysRemember(true)
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(2678400);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl repository=new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select email, password, enabled from users where email = ?")
                .authoritiesByUsernameQuery("select email, password from users where email = ?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}