package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//@Configuration
//public class BasicAuthenticationSecurityConfiguration {
//	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		
//		return
//                http
//                        .authorizeHttpRequests(
//                        		auth -> auth
//                        					.antMatchers(HttpMethod.OPTIONS,"/**").permitAll() //모든 주소에 OPTIONS요청 허용
//                        					.anyRequest().authenticated()
//                        				)
//                        .httpBasic(Customizer.withDefaults())	//기본 인증창사용
//                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))	//CSRF 비활성화 시 세션 상태
//                        .csrf(csrf -> csrf.disable())
//                        .build();
//	}
//	
//	
//	
//}

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable() // CSRF 비활성화
//            .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // 모든 주소에 OPTIONS 요청 허용
//                .anyRequest().authenticated()
//            .and()
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))	//CSRF 비활성화 시 세션 상태
//            .httpBasic(); // 기본 인증 사용
//    }
//}

