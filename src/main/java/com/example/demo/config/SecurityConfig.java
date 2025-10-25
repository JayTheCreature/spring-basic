package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 🔓 Swagger/OpenAPI 경로는 인증 없이 접근 허용
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/h2-console/**"      // (선택) H2 콘솔 허용 시
                        ).permitAll()
                        .anyRequest().permitAll()  // 임시로 전체 오픈 (나중에 authenticated() 변경)
                )
                // 🔁 CSRF 끄기 (Swagger, H2 콘솔 사용할 때 필요)
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/h2-console/**")
                        .disable()
                );
        return http.build();
    }
}