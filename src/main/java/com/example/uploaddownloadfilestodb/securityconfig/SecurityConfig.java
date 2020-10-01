package com.example.uploaddownloadfilestodb.securityconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("admin")
                //.password("$2a$10$xnQcn0Hmg.G/C63BHwFw6O7EJP3TiO2hjmI/AV4AGZxlUnwE6koia")
                .password("$2a$10$3OzF/2DKs9PalLiVvbhsjuuWlb.q8TSpNplUIz98TDhtnaBZFNV7u")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("$2a$10$dlE0iRVU/1Sh6qRHNuM/k.2tbXyCJwsPF1ULjcYjqRRtadf4h5Vie")
                .roles("USER");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/files/delete").hasRole("ADMIN")
                .antMatchers("/files").hasAnyRole("USER","ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .defaultSuccessUrl("/");

        http
                .csrf().disable()
                .cors().disable();
    }
}