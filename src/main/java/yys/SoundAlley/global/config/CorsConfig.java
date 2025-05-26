package yys.SoundAlley.global.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CorsConfig {
    public static CorsConfigurationSource apiConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        ArrayList<String> allowedOriginPatterns = new ArrayList<>();
        allowedOriginPatterns.add("http://localhost:8080");
        allowedOriginPatterns.add("http://localhost:3000");
        allowedOriginPatterns.add("http://localhost:5173");
        allowedOriginPatterns.add("https://soundalley.vercel.app");

        ArrayList<String> allowedHttpMethods = new ArrayList<>();
        allowedHttpMethods.add("GET");
        allowedHttpMethods.add("POST");
        allowedHttpMethods.add("PATCH");
        allowedHttpMethods.add("PUT");
        allowedHttpMethods.add("DELETE");

        configuration.setAllowedOriginPatterns(allowedOriginPatterns); // 패턴 리스트 허용
        configuration.setAllowedMethods(allowedHttpMethods);
        configuration.setAllowCredentials(true);   // 자격증명 허용(쿠키, 인증 헤더 등)
        configuration.addAllowedHeader("*");       // 모든 헤더 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
