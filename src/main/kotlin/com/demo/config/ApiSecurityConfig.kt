package com.demo.config

import com.demo.api.filter.JWTAuthenticationFilter
import com.demo.api.filter.JWTLoginFilter
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class ApiSecurityConfig : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
//                .antMatchers(HttpMethod.POST, "/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                // We filter the api/login requests
//                .addFilterBefore(JWTLoginFilter("/login", authenticationManager()),
//                        UsernamePasswordAuthenticationFilter::class.java)
//                // And filter other requests to check the presence of JWT in header
//                .addFilterBefore(JWTAuthenticationFilter(),
//                        UsernamePasswordAuthenticationFilter::class.java)
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder?) {
        // Create a default account
        auth!!.inMemoryAuthentication()
                .withUser("admin")
                .password("password")
                .roles("ADMIN")
    }
}