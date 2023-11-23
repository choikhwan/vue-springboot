package com.epas.login.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
	    return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}	
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	http
    	.csrf(csrf -> csrf.disable())
    	.cors(cors -> cors.disable())
        .sessionManagement(management ->
        		 management
		        .maximumSessions(1)
		        .maxSessionsPreventsLogin(false)
		        .expiredUrl("/login")
		        .sessionRegistry(sessionRegistry())
		);
    	/*
    	http.sessionManagement()
    	    .maximumSessions(1)
    	    .maxSessionsPreventsLogin(true) //true:나중에 접속한 사용자 로그인 방지, false:먼저 접속한 사용자 logout 처리
    	    .expiredUrl("/login")
    	    .sessionRegistry(sessionRegistry());
    	*/
    	return http.build();    	
    }
    
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public CookieCsrfTokenRepository getCookieCsrfTokenRepository(){
    	CookieCsrfTokenRepository cookieTokenConfig = CookieCsrfTokenRepository.withHttpOnlyFalse();
    	
    	return cookieTokenConfig;
    }    
}