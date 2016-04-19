package com.osiristher.webapp.config;

import com.osiristher.webapp.provider.OsiristherAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private OsiristherAuthProvider osiristherAuthProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(osiristherAuthProvider)
                .authorizeRequests()
                    .antMatchers("/", "/test", "/css/*", "/img/*", "/js/*", "/fonts/*").permitAll()
                    //.antMatchers("/student").hasRole("STUDENT")
                    //.antMatchers("/trainer").hasRole("TRAINER")
                    //.antMatchers("/secret").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/home")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();

        http.csrf()
                .csrfTokenRepository(csrfTokenRepository());
    }

    private CsrfTokenRepository csrfTokenRepository()
    {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        return repository;
    }

    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");*/
       /* auth.authenticationProvider(osiristherAuthProvider);
    }*/

}