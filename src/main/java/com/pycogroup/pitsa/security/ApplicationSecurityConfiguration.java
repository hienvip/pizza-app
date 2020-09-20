package com.pycogroup.pitsa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@ComponentScan("com.pycogroup.pitsa")
@EnableGlobalMethodSecurity(prePostEnabled = true)
class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private PizzaCustomerDetailsService pizzaCustomerDetailsService;

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        //entry point
        http
                .authorizeRequests()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/user/signup").permitAll()
                .antMatchers("/product/**").permitAll()
                .antMatchers("/category/**").permitAll()

                //disallow anything else
                .anyRequest().authenticated();
        http.csrf().disable();
        http.cors();
        //no session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //http.addFilterBefore(new JwtTokenFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

}
