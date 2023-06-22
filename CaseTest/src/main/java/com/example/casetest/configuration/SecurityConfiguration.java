package com.example.casetest.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.transaction.Transactional;

@Configuration
@EnableWebSecurity
@Transactional
public class SecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable();


        http.authorizeHttpRequests() // links start with /api/
                .antMatchers("/api/*", "/api/auth/login") // perform segregate authorize
                .permitAll();

        http.authorizeHttpRequests()
                        .antMatchers("/cart", "/addToCart/{id}", "/cart/removeItem/{index}")
                        .hasAnyRole("ADMIN" , "USER");

        // Pages require login with role: ROLE_ADMIN.
        // If not login at admin role yet, redirect to /login
        http.authorizeHttpRequests()
                .antMatchers("/api/role/**", "/api/user/**")
                .hasRole("ADMIN");

        // Pages require login with role: ROLE_USER
        // If not login at user role yet, redirect to /login
        http.authorizeHttpRequests()
                .antMatchers("/api/user/**")
                .hasRole("USER");

        // When user login with ROLE_USER, but try to
        // access pages require ROLE_ADMIN, redirect to /error-403
        http.authorizeHttpRequests().and().exceptionHandling()
                .accessDeniedPage("/access-denied");

        // Config for Login Form
        http.authorizeHttpRequests()
                .and().formLogin()// Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")
                .defaultSuccessUrl("/shop")
                .failureUrl("/login?error=true")
                .usernameParameter("account")
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true");
        return http.build();
    }
}