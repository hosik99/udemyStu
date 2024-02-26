package com.in28minutes.springboot.myfirstwebapp.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		// Lamda : imput을 받아서 passwordEncoder()에 설정
		UserDetails userDetails1 = createNewUser("11", "11");
		UserDetails userDetails2 = createNewUser("in28minutes", "22");

		return new InMemoryUserDetailsManager(userDetails1, userDetails2); // 유저 정보 추가
	}

	private UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);

		UserDetails userDetails = User.builder().passwordEncoder(passwordEncoder).username(username).password(password)
				.roles("USER", "ADMIN").build();
		return userDetails;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// SecurityFilterChain -> 요청이 들어오면 가장 먼저 필터링
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated()); // 모든요청 허용
		http.formLogin(withDefaults());// 로그인 페이지
		
		http.csrf().disable();	//h2 DB사용 위해서
		http.headers().frameOptions().disable(); //h2 DB사용 위해서
		return http.build();
	}

}
