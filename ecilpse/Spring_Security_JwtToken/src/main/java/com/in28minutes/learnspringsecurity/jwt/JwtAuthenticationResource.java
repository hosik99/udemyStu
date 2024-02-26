package com.in28minutes.learnspringsecurity.jwt;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class JwtAuthenticationResource {
	
	private JwtEncoder jwtEncoder;
	
	public JwtAuthenticationResource(JwtEncoder jwtEncoder) {
		this.jwtEncoder = jwtEncoder;
	}
	///authenticate 서버와 소통?
	//security.core.Authentication
	@PostMapping("/authenticate")
	public JwtRespose authenticate(Authentication authentication) {
		return new JwtRespose(createToken(authentication));
	}
	private String createToken(Authentication authentication) {
		var claims = JwtClaimsSet.builder()
			.issuer("self")  //발행
			.issuedAt(Instant.now()) //시스템 시계에서 재의 인스턴스 획
			.expiresAt(Instant.now().plusSeconds(60 * 15))	//15분뒤 만료
			.subject(authentication.getName())	//주체자
			.claim("scope",createScope(authentication))	//특정 유저가 가진 권
			.build();
		
		return jwtEncoder.encode(JwtEncoderParameters.from(claims) ).getTokenValue();
	}
	
	private String createScope(Authentication authentication) {
		return authentication.getAuthorities().stream()
				.map(a -> a.getAuthority())	//해당 인증 주체(사용자)에게 부여된 권한을 나타내는 문자열을 반환
				.collect(Collectors.joining(" "));
	}
	
}


record JwtRespose(String token) {}
