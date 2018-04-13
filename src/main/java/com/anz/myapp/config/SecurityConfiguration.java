/*
 * ANZ Project for an Interview
 * 
 * Equity Data Signal App By Mostafa Farshchi
 * Template pack-angular:src/main/java/config/SecurityConfiguration.java.p.vm
 */
package com.anz.myapp.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

import com.anz.myapp.security.AjaxAuthenticationFailureHandler;
import com.anz.myapp.security.AjaxAuthenticationSuccessHandler;
import com.anz.myapp.security.AlwaysSendUnauthorized401AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Inject
    private UserDetailsService userDetailsService;

    @Inject
    private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

    @Inject
    private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

    @Inject
    private AlwaysSendUnauthorized401AuthenticationEntryPoint alwaysSendUnauthorized401AuthenticationEntryPoint;

    @Inject
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring(). //
                antMatchers(HttpMethod.OPTIONS, "/**"). //
                antMatchers("/"). //
                antMatchers("/*.{js,html}"). //
                antMatchers("/img/**"). //
                antMatchers("/node_modules/**"). //
                antMatchers("/**/*.{js,html,css}");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http. //
                csrf().disable(). //
                formLogin(). //
                loginPage("/").//
                loginProcessingUrl("/api/login"). //
                defaultSuccessUrl("/", true). //
                successHandler(ajaxAuthenticationSuccessHandler). //
                failureHandler(ajaxAuthenticationFailureHandler). //
                usernameParameter("j_username"). //
                passwordParameter("j_password"). //
                permitAll(). //
                and(). //
                exceptionHandling(). //
                authenticationEntryPoint(alwaysSendUnauthorized401AuthenticationEntryPoint). //
                and(). //
                logout(). //
                logoutUrl("/api/logout"). //
                logoutSuccessUrl("/"). //
                deleteCookies("JSESSIONID"). //
                permitAll(). //
                and(). //
                authorizeRequests(). //
                antMatchers("/api/authenticated", "/api/equities/**", "/api/equityPeriodicals/**").permitAll().//
                antMatchers("/**").authenticated(). //
                antMatchers("/swagger-ui/index.html").hasAuthority("ROLE_ADMIN");
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
}
