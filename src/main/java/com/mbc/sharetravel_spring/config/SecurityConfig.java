package com.mbc.sharetravel_spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.mbc.sharetravel_spring.fillter.JwtFilter;
import com.mbc.sharetravel_spring.security.AuthEntryPoint;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Autowired
	private AuthEntryPoint authEntryPoint;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.csrf().disable();
			http.cors();
			
			http.sessionManagement() // 세션 정책
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션안쓴다는 의미
			
			http.authorizeRequests()
				.antMatchers(HttpMethod.POST,"/login","/register","/User-Register","/travelBoard/posts","/test/img").permitAll()
				.antMatchers(HttpMethod.GET, "/travel-board", "/travelBoard/**", "/travelimg/**").permitAll()
				.anyRequest().authenticated()
				.and()
					.exceptionHandling()
					.authenticationEntryPoint(authEntryPoint)
				.and()
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
				
			
			
			return http.build();
	}
	
	
	@Bean // 여기 파트가 크로스 오리진 관련
	CorsConfigurationSource configurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("http://localhost:3000");
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		
		return source;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
		throws Exception {
		
		return authenticationConfiguration.getAuthenticationManager();
	}
}
