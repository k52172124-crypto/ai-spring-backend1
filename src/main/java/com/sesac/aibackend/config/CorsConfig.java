package com.sesac.aibackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * CORS 설정 (Day 5).
 *
 * React 개발 서버(Vite 기본 포트 5173)의 호출을 허용합니다.
 *
 * 보안 주의:
 * - allowCredentials=true 와 함께 allowedHeaders="*" 사용은 비권장입니다.
 *   필요한 헤더만 화이트리스트로 명시합니다.
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(
                "http://localhost:5173",//사이트
                "http://localhost:3000" //passapi아이디
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));//메소드 거의 집합체
        config.setAllowedHeaders(List.of(
                "Authorization",//5173에서 들어온건 허용
                "Content-Type",//content에 들어온것들을 뚫어준다는
                "Accept",
                "X-Requested-With"
        ));
        config.setExposedHeaders(List.of("Authorization"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);//"/**" > 모두들 들어오게 한다!
        return source;
    }
}
