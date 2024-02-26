package com.in28minutes.learnspringsecurity.basic;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.annotation.security.RolesAllowed;

/* CSRF 해제
 * session 미사*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
//@EnableMethodSecurity	//메소드 보
@EnableMethodSecurity(jsr250Enabled=true)	//jsr250을 이용한 메소드 보 -> @RolesAllowed({"ADMIN","USER"})
public class BasicAuthSecurityConfigeration {

	@Bean
//    @Order(1)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(auth -> auth.anyRequest().authenticated())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .httpBasic()
            .and()
            .csrf().disable();
//            .headers().frameOptions().sameOrigin();	//h2-요청이 동일한 오리진에서 오는 경우 해당 프레임 허
        return http.build();
    }

//	@Bean
//	@Order(SecurityProperties.BASIC_AUTH_ORDER)
//	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeRequests(
//					auth -> {
//						auth.anyRequest().authenticated();
//					});
//		http.sessionManagement(
//				session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				);
////		http.formLogin();
//		http.httpBasic();
//		http.csrf().disable();
//		return http.build();
//	}
    
    /*InMemoryUserDetailsManager*/
//    @Bean
//    public UserDetailsService userDetailsService() {
//        var user = User.withUsername("in28minutes")
//        	.password("{noop}dummy")	//{noop} -> NoIncoding
////        	.password("dummy")
////        	.passwordEncoder(str -> passwordEncoder().encode(str));
//        	.roles("USER")
//        	.build();
//    	
//        var admin = User.withUsername("in28minutes")
//            	.password("{noop}dummy")	//{noop} -> NoIncoding
//            	.roles("ADMIN")	//Enum 사
//            	.build();
//        
//    	return new InMemoryUserDetailsManager(user,admin);//생성자 사	
//    }
    
//    @Bean
//    public DataSource dataSource() {
//    	return new EmbeddedDatabaseBuilder()
//    			.setType(EmbeddedDatabaseType.H2)
//    			.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//    			.build();
//    }
    
//    @Bean
//  public UserDetailsService userDetailsService(DataSource dataSource) {
//      var user = User.withUsername("in28minutes")
//      	.password("{noop}dummy")	//{noop} -> NoIncoding
//      	.roles("USER")
//      	.build();
//  	
//      var admin = User.withUsername("in28minutes")
//          	.password("{noop}dummy")	//{noop} -> NoIncoding
//          	.roles("ADMIN")	//Enum 사
//          	.build();
//      
//      var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//      jdbcUserDetailsManager.createUser(user);
//      jdbcUserDetailsManager.createUser(admin);
//      
//  	return jdbcUserDetailsManager;
//  }
}

