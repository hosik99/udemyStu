package com.in28minutes.learnspringsecurity.jwt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

//@Configuration
public class JwtSecurityConfiguration {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests(auth -> {auth.anyRequest().authenticated();});
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.httpBasic();
		http.csrf().disable();
		http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);//JSON Web Tokens를 사용하여 리소스를 보호한다
		return http.build();
	}
	
//	 @Bean
//	    public DataSource dataSource() {
//	    	return new EmbeddedDatabaseBuilder()
//	    			.setType(EmbeddedDatabaseType.H2)
//	    			.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//	    			.build();
//	    }
	    
//	    @Bean
//	  public UserDetailsService userDetailsService(DataSource dataSource) {
//	      var user = User.withUsername("in28minutes")
//	      	.password("{noop}dummy")	//{noop} -> NoIncoding
//	      	.roles("USER")
//	      	.build();
//	  	
//	      var admin = User.withUsername("in28minutes")
//	          	.password("{noop}dummy")	//{noop} -> NoIncoding
//	          	.roles("ADMIN")	//Enum 사
//	          	.build();
//	      
//	      var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//	      jdbcUserDetailsManager.createUser(user);
//	      jdbcUserDetailsManager.createUser(admin);
//	      
//	  	return jdbcUserDetailsManager;
//	  }
	    
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	    	return new BCryptPasswordEncoder();
	    }
	    
	    
	    //JWT->
	    //RSA
	    @Bean
	    public KeyPair keyPair() {
	    	KeyPairGenerator keyPairGenerator;
			try {
				keyPairGenerator = KeyPairGenerator.getInstance("RSA");
				keyPairGenerator.initialize(2048);	//2048bits
		    	return keyPairGenerator.generateKeyPair();
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
	    }
	    
	    //RSA Key
	    @Bean
	    public RSAKey rsaKey(KeyPair keyPair) {
	    	return new RSAKey
	    			.Builder((RSAPublicKey)keyPair.getPublic())	//공개키
		    		.privateKey(keyPair.getPrivate())	//비공개키
		    		.keyID(UUID.randomUUID().toString())
		    		.build();
	    }
	    
	    @Bean
	    public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
	    	var jwkSet = new JWKSet(rsaKey);
	    	return (jwkSelector, context) -> jwkSelector.select(jwkSet);
	    }
	    
	    //JWT Decoder
	    @Bean
	    public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
	    	return NimbusJwtDecoder
	    			.withPublicKey(rsaKey.toRSAPublicKey())
	    			.build();
	    }
	    
	  //JWT Encoder
	    @Bean
	    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
	    	return new NimbusJwtEncoder(jwkSource);
	    }
	    
}
